package jp.co.soramitsu.commonnetworking.subquery.factory

import jp.co.soramitsu.commonnetworking.dbengine.DatabaseDriverFactory
import jp.co.soramitsu.commonnetworking.dbengine.HistoryDatabaseProvider
import jp.co.soramitsu.commonnetworking.networkclient.SoraNetworkClient
import jp.co.soramitsu.commonnetworking.subquery.SubQueryClient

actual class SubQueryClientFactory {
    actual fun create(soraNetworkClient: SoraNetworkClient, baseUrl: String): SubQueryClient {
        return SubQueryClient(
            soraNetworkClient,
            baseUrl,
            HistoryDatabaseProvider(DatabaseDriverFactory())
        )
    }
}
