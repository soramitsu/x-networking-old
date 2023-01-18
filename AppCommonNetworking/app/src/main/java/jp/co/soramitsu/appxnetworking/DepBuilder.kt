package jp.co.soramitsu.appxnetworking

import android.content.Context
import jp.co.soramitsu.xnetworking.fearless.FearlessChainsBuilder
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.SoraWalletBlockExplorerInfo
import jp.co.soramitsu.xnetworking.sorawallet.envbuilder.SoraEnvBuilder
import jp.co.soramitsu.xnetworking.sorawallet.tokenwhitelist.SoraTokensWhitelistManager
import jp.co.soramitsu.xnetworking.txhistory.client.fearlesswallet.SubQueryClientForFearlessWallet
import jp.co.soramitsu.xnetworking.txhistory.client.fearlesswallet.SubQueryClientForFearlessWalletFactory
import jp.co.soramitsu.xnetworking.txhistory.client.sorawallet.SubQueryClientForSoraWallet
import jp.co.soramitsu.xnetworking.txhistory.client.sorawallet.SubQueryClientForSoraWalletFactory

object DepBuilder {

    val soraNetworkClient = SoramitsuNetworkClient(logging = true)
    val fearlessChainsBuilder = FearlessChainsBuilder(
        soraNetworkClient,
        "https://raw.githubusercontent.com/arvifox/arvifoxandroid/develop/felete/",
        "chains/index_android.json"
    )

    lateinit var subQueryClientForSoraWallet: SubQueryClientForSoraWallet
    lateinit var subQueryClientForFearlessWallet: SubQueryClientForFearlessWallet
    lateinit var networkService: NetworkService

    private val soraUrl = "https://api.subquery.network/sq/sora-xor/sora-staging"
//    private val soraUrl = "https://api.subquery.network/sq/sora-xor/sora-dev"

    fun build(ctx: Context) {
        subQueryClientForSoraWallet =
            SubQueryClientForSoraWalletFactory(ctx).create(
                soraNetworkClient,
                soraUrl,
                30,
            )
        subQueryClientForFearlessWallet =
            SubQueryClientForFearlessWalletFactory(ctx).create(
                soraNetworkClient,
                "https://api.subquery.network/sq/soramitsu/fearless-wallet-westend",
                30,
            )
        networkService = NetworkService(
            soraNetworkClient,
            fearlessChainsBuilder,
            soraEnvBuilder,
            subQueryClientForFearlessWallet,
            subQueryClientForSoraWallet,
            SoraWalletBlockExplorerInfo(
                soraNetworkClient,
                soraUrl,
            ),
            SoraTokensWhitelistManager(soraNetworkClient),
        )
    }

    val soraEnvBuilder = SoraEnvBuilder(
        soraNetworkClient,
        baseUrl = "https://raw.githubusercontent.com/sora-xor/polkaswap-exchange-web/master/env.json"
    )
}
