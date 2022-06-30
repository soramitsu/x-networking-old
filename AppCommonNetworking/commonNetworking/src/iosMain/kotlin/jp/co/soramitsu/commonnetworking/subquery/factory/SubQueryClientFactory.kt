package jp.co.soramitsu.commonnetworking.subquery.factory

import jp.co.soramitsu.commonnetworking.dbengine.DatabaseDriverFactory
import jp.co.soramitsu.commonnetworking.dbengine.HistoryDatabaseProvider
import jp.co.soramitsu.commonnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.commonnetworking.subquery.SubQueryClient
import jp.co.soramitsu.commonnetworking.subquery.history.SubQueryHistoryInfo
import kotlinx.serialization.DeserializationStrategy

actual class SubQueryClientFactory<T, R> {
    actual fun create(
        soramitsuNetworkClient: SoramitsuNetworkClient,
        baseUrl: String,
        pageSize: Int,
        deserializationStrategy: DeserializationStrategy<T>,
        jsonToHistoryInfo: (T) -> SubQueryHistoryInfo,
        historyIntoToResult: (SubQueryHistoryInfo) -> R,
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
            HistoryDatabaseProvider(DatabaseDriverFactory())
        )
    }
}
