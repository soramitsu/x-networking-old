package jp.co.soramitsu.appxnetworking

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import jp.co.soramitsu.xnetworking.fearless.FearlessChainsBuilder
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.SoraWalletBlockExplorerInfo
import jp.co.soramitsu.xnetworking.sorawallet.envbuilder.SoraEnvBuilder
import jp.co.soramitsu.xnetworking.txhistory.client.fearlesswallet.SubQueryClientForFearlessWalletFactory
import jp.co.soramitsu.xnetworking.txhistory.client.sorawallet.SubQueryClientForSoraWalletFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)

        val soraNetworkClient = SoramitsuNetworkClient(logging = true)
        val fearlessChainsBuilder = FearlessChainsBuilder(
            soraNetworkClient,
            "https://raw.githubusercontent.com/arvifox/arvifoxandroid/develop/felete/",
            "chains/index_android.json"
        )
        val subQueryClientForSoraWallet =
            SubQueryClientForSoraWalletFactory(applicationContext).create(
                soraNetworkClient,
                "https://api.subquery.network/sq/sora-xor/sora-staging",
                20,
            )

        val subQueryClientForFearlessWallet =
            SubQueryClientForFearlessWalletFactory(applicationContext).create(
                soraNetworkClient,
                "https://api.subquery.network/sq/soramitsu/fearless-wallet-westend",
                20,
            )

        val soraEnvBuilder = SoraEnvBuilder(
            soraNetworkClient,
            baseUrl = "https://raw.githubusercontent.com/sora-xor/polkaswap-exchange-web/master/env.json"
        )

        val blockExplorerInfoUrl = "https://api.subquery.network/sq/sora-xor/sora-dev"
        val networkService = NetworkService(
            soraNetworkClient,
            fearlessChainsBuilder,
            soraEnvBuilder,
            subQueryClientForFearlessWallet,
            subQueryClientForSoraWallet,
            SoraWalletBlockExplorerInfo(soraNetworkClient, blockExplorerInfoUrl)
        )

        btn1.setOnClickListener {
            GlobalScope.launch {
                try {
                    Log.e("foxxx", "r start")
                    val r = networkService.getRewards()
                    Log.e("foxxx", "r = ${r}")
                } catch (t: Throwable) {
                    Log.e("foxxx", "t= ${t.localizedMessage}")
                }
            }
        }

        btn2.setOnClickListener {
            GlobalScope.launch {
                Log.e("foxxx", "button 2")
                try {
                    val r = networkService.getHistorySora(1) {
                        true
                    }
                    Log.e("foxxx", "r = ${r.endReached} ${r.page} ${r.items.size}")
                } catch (t: Throwable) {
                    Log.e("foxxx", "t= ${t.localizedMessage}")
                }
            }
        }

        btn3.setOnClickListener {
            GlobalScope.launch {
                Log.e("foxxx", "button 3")
                try {
                    val r = networkService.getApy()
                    Log.e("foxxx", "r = $r")
                } catch (t: Throwable) {
                    Log.e("foxxx", "t = ${t.localizedMessage}")
                }
            }
        }
    }
}
