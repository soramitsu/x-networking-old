package jp.co.soramitsu.appxnetworking

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import jp.co.soramitsu.xnetworking.fearless.FearlessChainsBuilder
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.subquery.factory.SubQueryClientForFearless
import jp.co.soramitsu.xnetworking.subquery.factory.SubQueryClientForSora
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)

        val soraNetworkClient = SoramitsuNetworkClient(logging = true)
        val f = FearlessChainsBuilder(
            soraNetworkClient,
            "https://raw.githubusercontent.com/arvifox/arvifoxandroid/develop/felete/"
        )
//        val subQueryClient = SubQueryClientForSora.build(
//            applicationContext,
//            soraNetworkClient,
//            "https://api.subquery.network/sq/sora-xor/sora-dev",
//            20
//        )
        val subQueryClient = SubQueryClientForFearless.build(
            applicationContext,
            soraNetworkClient,
            "https://api.subquery.network/sq/soramitsu/fearless-wallet-westend",
            20
        )
        val networkService = NetworkService(soraNetworkClient, f, subQueryClient)

        btn1.setOnClickListener {
            GlobalScope.launch {
                try {
                    val r = networkService.getAssets()
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
                    val r = networkService.getHistory(1) {
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
                    val r = networkService.getPeers("vko")
                    Log.e("foxxx", "r = $r")
                } catch (t: Throwable) {
                    Log.e("foxxx", "t = ${t.localizedMessage}")
                }
            }
        }
    }
}