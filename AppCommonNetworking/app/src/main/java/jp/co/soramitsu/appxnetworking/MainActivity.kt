package jp.co.soramitsu.appxnetworking

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import jp.co.soramitsu.xnetworking.encrypt.EncryptionType
import jp.co.soramitsu.xnetworking.encrypt.keypair.substrate.SubstrateKeypairFactory
import jp.co.soramitsu.xnetworking.extensions.fromHex
import jp.co.soramitsu.xnetworking.extensions.toHexString
import jp.co.soramitsu.xnetworking.fearless.FearlessChainsBuilder
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.SoraWalletBlockExplorerInfo
import jp.co.soramitsu.xnetworking.sorawallet.envbuilder.SoraEnvBuilder
import jp.co.soramitsu.xnetworking.sorawallet.tokenwhitelist.SoraTokensWhitelistManager
import jp.co.soramitsu.xnetworking.txhistory.client.fearlesswallet.SubQueryClientForFearlessWalletFactory
import jp.co.soramitsu.xnetworking.txhistory.client.sorawallet.SubQueryClientForSoraWalletFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var toast: Toast? = null
    private val lifecycleScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)

        val soraNetworkClient = SoramitsuNetworkClient(logging = true)
        val fearlessChainsBuilder = FearlessChainsBuilder(
            soraNetworkClient,
            "https://raw.githubusercontent.com/arvifox/arvifoxandroid/develop/felete/",
            "chains/index_android.json"
        )
        val subQueryClientForSoraWallet =
            SubQueryClientForSoraWalletFactory(applicationContext).create(
                soraNetworkClient,
                "https://api.subquery.network/sq/sora-xor/sora-dev",
                30,
            )

        val subQueryClientForFearlessWallet =
            SubQueryClientForFearlessWalletFactory(applicationContext).create(
                soraNetworkClient,
                "https://api.subquery.network/sq/soramitsu/fearless-wallet-westend",
                30,
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
            SoraWalletBlockExplorerInfo(soraNetworkClient, blockExplorerInfoUrl),
            SoraTokensWhitelistManager(soraNetworkClient),
        )

        btn1.setOnClickListener {
            lifecycleScope.launch {
                try {
                    Log.e("foxxx", "r start")
                    val r = networkService.getApy()
                    Log.e("foxxx", "r = ${r}")
                } catch (t: Throwable) {
                    Log.e("foxxx", "t= ${t.localizedMessage}")
                }
            }
        }

        var page = 0L
        btn2.setOnClickListener {
            page++
            lifecycleScope.launch {
                Log.e("foxxx", "button 2")
                try {
                    val r = networkService.getHistorySora(page) {
                        true
                    }
                    Log.e("foxxx", "r = ${r.endReached} ${r.page} ${r.items.size} ${r.errorMessage}")
                } catch (t: Throwable) {
                    Log.e("foxxx", "t= ${t.localizedMessage}")
                }
            }
        }

        btn3.setOnClickListener {
            lifecycleScope.launch {
                Log.e("foxxx", "button 3")
                try {
                    val r = networkService.getAssets()
                    Log.e("foxxx", "r = $r")
                } catch (t: Throwable) {
                    Log.e("foxxx", "t = ${t.localizedMessage}")
                }
            }
        }

        btn4.setOnClickListener {
            val seed = "3132333435363738393031323334353637383930313233343536373839303132".fromHex()
            val result = SubstrateKeypairFactory.generate(
                encryptionType = EncryptionType.SR25519,
                seed = seed
            )

            val expectedPublicKeyHex = "741c08a06f41c596608f6774259bd9043304adfa5d3eea62760bd9be97634d63"
            val expectedPrivateKeyHex = "1ec20c6cb85bf4c7423b95752b70c312e6ae9e5701ffb310f0a9019d9c041e0a"

            val isCorrectKeypair = listOf(expectedPublicKeyHex, expectedPrivateKeyHex) ==
                        listOf(result.publicKey.toHexString(), result.privateKey.toHexString())

            showToast("is correct keypair: $isCorrectKeypair")
        }
    }

    private fun showToast(text: String) {
        toast?.cancel()
        toast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
        toast?.show()
    }
}
