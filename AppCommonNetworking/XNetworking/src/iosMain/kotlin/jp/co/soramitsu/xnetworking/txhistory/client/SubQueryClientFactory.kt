package jp.co.soramitsu.xnetworking.txhistory.client

import jp.co.soramitsu.xnetworking.dbengine.DatabaseDriverFactory
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.txhistory.subquery.graphqlrequest.fearlessHistoryGraphQLRequest
import jp.co.soramitsu.xnetworking.txhistory.subquery.graphqlrequest.soraHistoryGraphQLRequest
import jp.co.soramitsu.xnetworking.txhistory.TxHistoryInfo
import jp.co.soramitsu.xnetworking.txhistory.TxHistoryItem
import jp.co.soramitsu.xnetworking.txhistory.subquery.response.fearless.FearlessWalletSubQueryHistoryMapper
import jp.co.soramitsu.xnetworking.txhistory.subquery.response.fearless.FearlessSubQueryResponse
import jp.co.soramitsu.xnetworking.txhistory.subquery.response.sora.SoraWalletSubQueryHistoryMapper
import jp.co.soramitsu.xnetworking.txhistory.subquery.response.sora.SoraSubQueryResponse
import jp.co.soramitsu.xnetworking.txhistory.HistoryDatabaseProvider
import kotlinx.serialization.DeserializationStrategy

actual class SubQueryClientFactory<T, R> {
    actual fun create(
        soramitsuNetworkClient: SoramitsuNetworkClient,
        baseUrl: String,
        pageSize: Int,
        deserializationStrategy: DeserializationStrategy<T>,
        jsonToHistoryInfo: (T) -> TxHistoryInfo,
        historyIntoToResult: (TxHistoryItem) -> R,
        historyRequest: String,
    ): SubQueryClient<T, R> {
        return SubQueryClient(
            soramitsuNetworkClient,
            pageSize,
            deserializationStrategy,
            jsonToHistoryInfo,
            historyIntoToResult,
            historyRequest,
            HistoryDatabaseProvider(DatabaseDriverFactory())
        )
    }
}

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
