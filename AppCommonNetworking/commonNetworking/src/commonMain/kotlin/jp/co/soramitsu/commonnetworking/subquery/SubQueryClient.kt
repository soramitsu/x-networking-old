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
    ): ReferrerRewardsInfo {
        val response = networkClient.createJsonRequest<ReferrerRewardsResponse>(
            baseUrl,
            HttpMethod.Post,
            SubqueryRequest(referrerRewardsGraphQLRequest(address))
        )
        return if (response.data.referrerRewards.groupedAggregates.isEmpty()) {
            ReferrerRewardsInfo(rewards = emptyList())
        } else {
            ReferrerRewardsInfo(rewards = response.data.referrerRewards.groupedAggregates.map {
                ReferrerRewards(
                    referral = it.keys.firstOrNull().orEmpty(),
                    amount = it.sum.amount
                )
            })
        }
    }
}
