package jp.co.soramitsu.appxnetworking

import android.content.Context
import jp.co.soramitsu.xnetworking.fearless.FearlessChainsBuilder
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.SoraWalletBlockExplorerInfo
import jp.co.soramitsu.xnetworking.sorawallet.mainconfig.SoraRemoteConfigBuilder
import jp.co.soramitsu.xnetworking.sorawallet.tokenwhitelist.SoraTokensWhitelistManager
import jp.co.soramitsu.xnetworking.txhistory.client.fearlesswallet.SubQueryClientForFearlessWallet
import jp.co.soramitsu.xnetworking.txhistory.client.fearlesswallet.SubQueryClientForFearlessWalletFactory
import jp.co.soramitsu.xnetworking.txhistory.client.sorawallet.SubQueryClientForSoraWallet
import jp.co.soramitsu.xnetworking.txhistory.client.sorawallet.SubQueryClientForSoraWalletFactory

object DepBuilder {

    private val soraNetworkClient = SoramitsuNetworkClient(logging = true)
    private val fearlessChainsBuilder = FearlessChainsBuilder(
        soraNetworkClient,
        "https://raw.githubusercontent.com/arvifox/arvifoxandroid/develop/felete/",
        "chains/index_android.json"
    )

    private val soraRemoteConfigBuilder = SoraRemoteConfigBuilder(
        soraNetworkClient,
        "https://raw.githubusercontent.com/soramitsu/sora2-config/dev/common.json",
        "https://raw.githubusercontent.com/soramitsu/sora2-config/dev/mobile.json",
    )

    lateinit var subQueryClientForSoraWallet: SubQueryClientForSoraWallet
    lateinit var subQueryClientForFearlessWallet: SubQueryClientForFearlessWallet
    lateinit var networkService: NetworkService

    fun build(ctx: Context) {
        subQueryClientForSoraWallet =
            SubQueryClientForSoraWalletFactory(ctx).create(
                soraNetworkClient,
                30,
                soraRemoteConfigBuilder,
            )
        subQueryClientForFearlessWallet =
            SubQueryClientForFearlessWalletFactory(ctx).create(
                soraNetworkClient,
                30,
            )
        networkService = NetworkService(
            soraNetworkClient,
            fearlessChainsBuilder,
            soraRemoteConfigBuilder,
            subQueryClientForFearlessWallet,
            subQueryClientForSoraWallet,
            SoraWalletBlockExplorerInfo(
                soraNetworkClient,
                soraRemoteConfigBuilder,
            ),
            SoraTokensWhitelistManager(soraNetworkClient),
        )
    }
}
