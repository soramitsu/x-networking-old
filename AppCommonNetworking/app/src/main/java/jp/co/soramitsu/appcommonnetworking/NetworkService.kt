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

    suspend fun getHistory() = subQueryClient.initTransactionHistory(20, "cnVkoGs3rEMqLqY27c2nfVXJRGdzNJk2ns78DcqtppaSRe8qm")
    suspend fun getHistory2() = subQueryClient.getNextPageOfTransactionHistory()

    suspend fun getRewards() = subQueryClient.getReferrerRewards(
        address = "cnVkoGs3rEMqLqY27c2nfVXJRGdzNJk2ns78DcqtppaSRe8qm",
    )
}