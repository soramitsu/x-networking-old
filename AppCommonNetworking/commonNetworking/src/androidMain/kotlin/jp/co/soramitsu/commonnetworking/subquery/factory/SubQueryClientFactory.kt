package jp.co.soramitsu.commonnetworking.subquery.factory

import android.content.Context
import jp.co.soramitsu.commonnetworking.dbengine.DatabaseDriverFactory
import jp.co.soramitsu.commonnetworking.dbengine.HistoryDatabaseProvider
import jp.co.soramitsu.commonnetworking.networkclient.SoraNetworkClient
import jp.co.soramitsu.commonnetworking.subquery.SubQueryClient

actual class SubQueryClientFactory(private val context: Context) {
    actual fun create(soraNetworkClient: SoraNetworkClient, baseUrl: String, pageSize: Int): SubQueryClient {
        return SubQueryClient(
            soraNetworkClient,
            baseUrl,
            pageSize,
            HistoryDatabaseProvider(DatabaseDriverFactory(context))
        )
    }
}
