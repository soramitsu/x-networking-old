package jp.co.soramitsu.appcommonnetworking

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import jp.co.soramitsu.commonnetworking.fearless.FearlessChainsBuilder
import jp.co.soramitsu.commonnetworking.networkclient.SoraNetworkClient
import jp.co.soramitsu.commonnetworking.subquery.factory.SubQueryClientFactory
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

        val soraNetworkClient = SoraNetworkClient(logging = true)
        val f = FearlessChainsBuilder(
            soraNetworkClient,
            "https://raw.githubusercontent.com/arvifox/arvifoxandroid/develop/felete/"
        )
        val sfa = SubQueryClientFactory(applicationContext)
        val hi = sfa.create(soraNetworkClient, "https://api.subquery.network/sq/sora-xor/sora-dev", 20)
        val networkService = NetworkService(soraNetworkClient, f, hi)

        btn1.setOnClickListener {
            GlobalScope.launch {
                try {
                    val r = networkService.getChains()
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
                    val r = networkService.getHistory(1)
                    Log.e("foxxx", "r = ${r.endReached} ${r.items.size}")
                } catch (t: Throwable) {
                    Log.e("foxxx", "t= ${t.localizedMessage}")
                }
            }
        }

        btn3.setOnClickListener {
            GlobalScope.launch {
                Log.e("foxxx", "button 3")
                try {
                    val r = networkService.getPeers()
                    Log.e("foxxx", "r = $r")
                } catch (t: Throwable) {
                    Log.e("foxxx", "t = ${t.localizedMessage}")
                }
            }
        }
    }
}