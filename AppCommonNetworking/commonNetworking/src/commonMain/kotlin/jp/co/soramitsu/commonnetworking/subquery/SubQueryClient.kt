package jp.co.soramitsu.commonnetworking.subquery

import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.toBigInteger
import io.ktor.http.HttpMethod
import jp.co.soramitsu.commonnetworking.networkclient.SoraNetworkClient
import jp.co.soramitsu.commonnetworking.networkclient.SoraNetworkException
import kotlin.coroutines.cancellation.CancellationException

class SubQueryClient(
    private val networkClient: SoraNetworkClient,
    private val baseUrl: String,
) {

    @Throws(SoraNetworkException::class, CancellationException::class)
    suspend fun getSpApy(): List<SbApyInfo> {
        val response = networkClient.createJsonRequest<SubQuerySbApyResponse>(
            baseUrl,
            HttpMethod.Post,
            SubqueryRequest(sbApyGraphQLRequest())
        )
        return SubQueryMapper.map(response)
    }

    @Throws(SoraNetworkException::class, CancellationException::class)
    suspend fun getTransactionHistory(
        amount: Int,
        address: String,
        cursor: String = "",
        assetId: String = ""
    ): SoraHistoryInfo {
        val response = networkClient.createJsonRequest<SoraSubqueryResponse>(
            baseUrl,
            HttpMethod.Post,
            SubqueryRequest(soraHistoryGraphQLRequest(amount, address, cursor, assetId))
        )
        return SubQueryMapper.map(response)
    }

    @Throws(SoraNetworkException::class, CancellationException::class)
    suspend fun getReferrerRewards(
        address: String,
        blockHeight: Long = 0,
        base: Int = 18,
    ): ReferrerRewardsInfo {
        val tempStorage = mutableMapOf<String, BigInteger>()
        val pageSize = 40
        var cursor: String? = ""
        var blockNumber = 0L
        var exit = false
        while (!exit && cursor != null) {
            val response = networkClient.createJsonRequest<ReferrerRewardsResponse>(
                baseUrl,
                HttpMethod.Post,
                SubqueryRequest(referrerRewardsGraphQLRequest(pageSize, address, cursor))
            )
            val rewardsCount = response.data.referrerRewards.nodes.size
            if (rewardsCount == 0) {
                exit = true
            } else {
                if (blockNumber == 0L) {
                    blockNumber = response.data.referrerRewards.nodes.first().blockHeight.toLong()
                }
                cursor = response.data.referrerRewards.pageInfo.endCursor
                exit = (rewardsCount < pageSize
                    || response.data.referrerRewards.nodes.last().blockHeight.toLong() <= blockHeight
                    || response.data.referrerRewards.pageInfo.hasNextPage.not()
                )
                response.data.referrerRewards.nodes
                    .takeWhile { it.blockHeight.toLong() > blockHeight }
                    .groupBy { it.referrer }
                    .forEach {
                        val sum = it.value.fold(BigInteger.ZERO) { acc, item ->
                            acc + item.amount.toBigInteger(base)
                        }
                        val cur = tempStorage[it.key] ?: BigInteger.ZERO
                        tempStorage[it.key] = sum + cur
                    }
            }
        }
        return ReferrerRewardsInfo(
            blockHeight = blockNumber,
            tempStorage.map { ReferrerRewards(it.key, it.value) })
    }
}
