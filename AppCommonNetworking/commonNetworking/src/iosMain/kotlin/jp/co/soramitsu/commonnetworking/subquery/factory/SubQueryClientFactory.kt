package jp.co.soramitsu.commonnetworking.subquery.factory

import jp.co.soramitsu.commonnetworking.dbengine.DatabaseDriverFactory
import jp.co.soramitsu.commonnetworking.dbengine.HistoryDatabaseProvider
import jp.co.soramitsu.commonnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.commonnetworking.subquery.SubQueryClient
import jp.co.soramitsu.commonnetworking.subquery.history.SubQueryHistoryInfo
import kotlinx.serialization.DeserializationStrategy

actual class SubQueryClientFactory<T> {
    actual fun create(
        soramitsuNetworkClient: SoramitsuNetworkClient,
        baseUrl: String,
        pageSize: Int,
        str: DeserializationStrategy<T>,
        to: (T) -> SubQueryHistoryInfo,
    ): SubQueryClient<T> {
        return SubQueryClient(
            soramitsuNetworkClient,
            baseUrl,
            pageSize,
            str,
            to,
            HistoryDatabaseProvider(DatabaseDriverFactory())
        )
    }
}
