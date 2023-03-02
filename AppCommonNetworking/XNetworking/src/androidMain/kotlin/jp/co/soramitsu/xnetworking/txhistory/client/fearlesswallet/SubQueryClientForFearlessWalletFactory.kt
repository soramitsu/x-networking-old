package jp.co.soramitsu.xnetworking.txhistory.client.fearlesswallet

import android.content.Context
import jp.co.soramitsu.xnetworking.dbengine.DatabaseDriverFactory
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.txhistory.HistoryDatabaseProvider

actual class SubQueryClientForFearlessWalletFactory(
    private val context: Context,
) {
    actual fun create(
        soramitsuNetworkClient: SoramitsuNetworkClient,
        pageSize: Int,
    ): SubQueryClientForFearlessWallet {
        return SubQueryClientForFearlessWallet(
            networkClient = soramitsuNetworkClient,
            pageSize = pageSize,
            historyDatabaseProvider = HistoryDatabaseProvider(DatabaseDriverFactory(context)),
        )
    }
}