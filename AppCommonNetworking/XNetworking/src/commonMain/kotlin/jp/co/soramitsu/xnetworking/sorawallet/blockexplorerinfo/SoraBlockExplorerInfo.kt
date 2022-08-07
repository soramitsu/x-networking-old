package jp.co.soramitsu.xnetworking.sorawallet

import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.toBigInteger
import io.ktor.http.*
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkException
import jp.co.soramitsu.xnetworking.txhistory.subquery.graphqlrequest.SubQueryRequest
import kotlin.coroutines.cancellation.CancellationException

class SoraBlockExplorerInfo(
    private val networkClient: SoramitsuNetworkClient,
    private var baseUrl: String,
) {

    @Throws(SoramitsuNetworkException::class, CancellationException::class)
    suspend fun getSpApy(
        url: String = baseUrl,
    ): List<SbApyInfo> {
        val response = networkClient.createJsonRequest<SubQuerySbApyResponse>(
            url,
            HttpMethod.Post,
            SubQueryRequest(sbApyGraphQLRequest())
        )
        return SubQueryMapper.map(response)
    }

    @Throws(SoramitsuNetworkException::class, CancellationException::class)
    suspend fun getReferrerRewards(
        address: String,
        url: String = baseUrl,
    ): ReferrerRewardsInfo {
        var cursor = ""
        val list = mutableMapOf<String, BigInteger>()
        while (true) {
            val response = networkClient.createJsonRequest<ReferrerRewardsResponse>(
                url,
                HttpMethod.Post,
                SubQueryRequest(referrerRewardsGraphQLRequest(address, cursor))
            )
            response.data.referrerRewards.nodes.forEach {
                val cur = list[it.referral] ?: 0L.toBigInteger()
                list[it.referral] = it.amount.toBigInteger() + cur
            }
            if (response.data.referrerRewards.pageInfo.hasNextPage && response.data.referrerRewards.pageInfo.endCursor != null) {
                cursor = response.data.referrerRewards.pageInfo.endCursor
            } else {
                break
            }
        }
        return ReferrerRewardsInfo(list.map {
            ReferrerReward(it.key, it.value.toString())
        })
    }
}