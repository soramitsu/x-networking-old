package jp.co.soramitsu.commonnetworking.subquery

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
}
