package jp.co.soramitsu.xnetworking.txhistory.client

import android.content.Context
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

actual class SubQueryClientFactory<T, R>(private val context: Context) {
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
            baseUrl,
            pageSize,
            deserializationStrategy,
            jsonToHistoryInfo,
            historyIntoToResult,
            historyRequest,
            HistoryDatabaseProvider(DatabaseDriverFactory(context))
        )
    }
}

object SubQueryClientForFearless {
    fun build(
        context: Context,
        soramitsuNetworkClient: SoramitsuNetworkClient,
        baseUrl: String,
        pageSize: Int,
    ): SubQueryClient<FearlessSubQueryResponse, TxHistoryItem> {
        return SubQueryClientFactory<FearlessSubQueryResponse, TxHistoryItem>(
            context
        ).create(
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

object SubQueryClientForSora {
    fun build(
        context: Context,
        soramitsuNetworkClient: SoramitsuNetworkClient,
        baseUrl: String,
        pageSize: Int,
    ): SubQueryClient<SoraSubQueryResponse, TxHistoryItem> {
        return SubQueryClientFactory<SoraSubQueryResponse, TxHistoryItem>(
            context
        ).create(
            soramitsuNetworkClient = soramitsuNetworkClient,
            baseUrl = baseUrl,
            pageSize = pageSize,
            deserializationStrategy = SoraSubQueryResponse.serializer(),
            jsonToHistoryInfo = { response -> SoraWalletSubQueryHistoryMapper.map(response) },
            historyIntoToResult = { it },
            historyRequest = soraHistoryGraphQLRequest(),
        )
    }
}
