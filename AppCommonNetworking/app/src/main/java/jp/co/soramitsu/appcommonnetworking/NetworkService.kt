package jp.co.soramitsu.appcommonnetworking

import jp.co.soramitsu.commonnetworking.fearless.FearlessChainsBuilder
import jp.co.soramitsu.commonnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.commonnetworking.subquery.SubQueryClient

class NetworkService<T, R>(
    private val client: SoramitsuNetworkClient,
    private val fearlessChainsBuilder: FearlessChainsBuilder,
    private val subQueryClient: SubQueryClient<T, R>,
) {

    suspend fun getRequest() = client.createJsonRequest<List<Int>>("https://www.github.com")

    suspend fun getChains() = fearlessChainsBuilder.getChains("2.0.8", emptyList())

    suspend fun getApy() = subQueryClient.getSpApy()

    suspend fun getHistory(page: Long, f: (R) -> Boolean) = subQueryClient.getTransactionHistoryPaged(
        address = "cnVkoGs3rEMqLqY27c2nfVXJRGdzNJk2ns78DcqtppaSRe8qm",
//        address = "5HpLdCTNBQDjFomqpG2XWadgB4zHTuqQqNHhUyYbett7k1RR",
        page = page,
        filter = f
    )

    suspend fun getPeers(query: String) = subQueryClient.getTransactionPeers(query)

    suspend fun getRewards() = subQueryClient.getReferrerRewards(
        address = "cnVkoGs3rEMqLqY27c2nfVXJRGdzNJk2ns78DcqtppaSRe8qm",
    )
}