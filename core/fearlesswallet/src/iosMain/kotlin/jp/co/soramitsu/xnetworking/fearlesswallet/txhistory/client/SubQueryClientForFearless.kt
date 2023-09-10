package jp.co.soramitsu.xnetworking.fearlesswallet.txhistory.client

import jp.co.soramitsu.xnetworking.basic.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.basic.txhistory.TxHistoryItem
import jp.co.soramitsu.xnetworking.basic.txhistory.client.SubQueryClient
import jp.co.soramitsu.xnetworking.basic.txhistory.client.SubQueryClientFactory
import jp.co.soramitsu.xnetworking.fearlesswallet.txhistory.subquery.fearlessHistoryGraphQLRequest
import jp.co.soramitsu.xnetworking.fearlesswallet.txhistory.subquery.models.FearlessSubQueryResponse
import jp.co.soramitsu.xnetworking.fearlesswallet.txhistory.subquery.models.FearlessWalletSubQueryHistoryMapper

object SubQueryClientForFearless {
    fun build(
        soramitsuNetworkClient: SoramitsuNetworkClient,
        baseUrl: String,
        pageSize: Int,
    ): SubQueryClient<FearlessSubQueryResponse, TxHistoryItem> {
        return SubQueryClientFactory<FearlessSubQueryResponse, TxHistoryItem>()
            .create(
            soramitsuNetworkClient = soramitsuNetworkClient,
            baseUrl = baseUrl,
            pageSize = pageSize,
            deserializationStrategy = FearlessSubQueryResponse.serializer(),
            historyIntoToResult = { it },
            historyRequest = fearlessHistoryGraphQLRequest(),
            jsonToHistoryInfo = { response -> FearlessWalletSubQueryHistoryMapper.map(response) },
        )
    }
}