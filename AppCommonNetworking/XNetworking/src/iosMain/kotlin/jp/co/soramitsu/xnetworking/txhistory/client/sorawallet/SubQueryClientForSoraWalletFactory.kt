package jp.co.soramitsu.xnetworking.txhistory.client.sorawallet

import jp.co.soramitsu.xnetworking.dbengine.DatabaseDriverFactory
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.txhistory.HistoryDatabaseProvider

actual class SubQueryClientForSoraWalletFactory {
    actual fun create(
        soramitsuNetworkClient: SoramitsuNetworkClient,
        baseUrl: String,
        pageSize: Int,
    ): SubQueryClientForSoraWallet {
        return SubQueryClientForSoraWallet(
            networkClient = soramitsuNetworkClient,
            baseUrl = baseUrl,
            pageSize = pageSize,
            historyDatabaseProvider = HistoryDatabaseProvider(DatabaseDriverFactory()),
        )
    }
}