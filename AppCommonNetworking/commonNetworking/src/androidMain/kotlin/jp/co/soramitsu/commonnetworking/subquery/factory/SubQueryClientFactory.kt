package jp.co.soramitsu.commonnetworking.subquery.factory

import android.content.Context
import jp.co.soramitsu.commonnetworking.dbengine.DatabaseDriverFactory
import jp.co.soramitsu.commonnetworking.dbengine.HistoryDatabaseProvider
import jp.co.soramitsu.commonnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.commonnetworking.subquery.SubQueryClient

actual class SubQueryClientFactory(private val context: Context) {
    actual fun create(soramitsuNetworkClient: SoramitsuNetworkClient, baseUrl: String, pageSize: Int): SubQueryClient {
        return SubQueryClient(
            soramitsuNetworkClient,
            baseUrl,
            pageSize,
            HistoryDatabaseProvider(DatabaseDriverFactory(context))
        )
    }
}
