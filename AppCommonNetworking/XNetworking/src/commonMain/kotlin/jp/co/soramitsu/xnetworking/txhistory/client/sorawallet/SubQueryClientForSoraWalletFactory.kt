package jp.co.soramitsu.xnetworking.txhistory.client.sorawallet

import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient

expect class SubQueryClientForSoraWalletFactory {

    fun create(
        soramitsuNetworkClient: SoramitsuNetworkClient,
        baseUrl: String,
        pageSize: Int,
    ): SubQueryClientForSoraWallet
}
