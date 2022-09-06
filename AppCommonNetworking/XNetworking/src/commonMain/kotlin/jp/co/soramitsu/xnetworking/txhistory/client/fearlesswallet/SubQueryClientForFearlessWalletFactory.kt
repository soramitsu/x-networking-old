package jp.co.soramitsu.xnetworking.txhistory.client.fearlesswallet

import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient

expect class SubQueryClientForFearlessWalletFactory {

    fun create(
        soramitsuNetworkClient: SoramitsuNetworkClient,
        baseUrl: String,
        pageSize: Int,
    ): SubQueryClientForFearlessWallet
}