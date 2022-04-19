package jp.co.soramitsu.appcommonnetworking

import jp.co.soramitsu.commonnetworking.fearless.FearlessChainsBuilder
import jp.co.soramitsu.commonnetworking.networkclient.SoraNetworkClient
import jp.co.soramitsu.commonnetworking.subquery.SubQueryClient

class NetworkService(
    private val client: SoraNetworkClient,
    private val fearlessChainsBuilder: FearlessChainsBuilder,
    private val subQueryClient: SubQueryClient,
) {

    suspend fun getRequest() = client.createJsonRequest<List<Int>>("https://www.github.com")

    suspend fun getChains() = fearlessChainsBuilder.getChains("2.0.8", emptyList())

    suspend fun getApy() = subQueryClient.getSpApy()

    suspend fun getHistory() = subQueryClient.getTransactionHistory(
        10, "cnWP7dPbnWKYuE8zCJ7hQLywnef7U6UKPx1AznjKEXDxedBGv"
    )
}