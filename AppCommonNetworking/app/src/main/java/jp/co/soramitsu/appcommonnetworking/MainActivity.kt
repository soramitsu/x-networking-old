package jp.co.soramitsu.appcommonnetworking

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import jp.co.soramitsu.commonnetworking.fearless.FearlessChainsBuilder
import jp.co.soramitsu.commonnetworking.networkclient.SoraNetworkClient
import jp.co.soramitsu.commonnetworking.subquery.SubQueryClient
import jp.co.soramitsu.commonnetworking.networkclient.SoraNetworkClientImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)

        val soraNetworkClient: SoraNetworkClient = SoraNetworkClientImpl(logging = true)
        val f = FearlessChainsBuilder(
            soraNetworkClient,
            "https://raw.githubusercontent.com/arvifox/arvifoxandroid/develop/felete/"
        )
        val hi = SubQueryClient(soraNetworkClient, "https://api.subquery.network/sq/sora-xor/sora-dev")

        btn1.setOnClickListener {
            GlobalScope.launch {
                try {
                    val r = f.getChains("2.0.8", emptyList())
                    Log.e("foxxx", "r = ${r}")
                } catch (t: Throwable) {
                    Log.e("foxxx", "t= ${t.localizedMessage}")
                }
            }
        }

        btn2.setOnClickListener {
            GlobalScope.launch {
                try {
                    val r = hi.getTransactionHistory(
                        10, "cnWP7dPbnWKYuE8zCJ7hQLywnef7U6UKPx1AznjKEXDxedBGv"
                    )
                    Log.e("foxxx", "r = $r")
                } catch (t: Throwable) {
                    Log.e("foxxx", "t= ${t.localizedMessage}")
                }
            }
        }

        btn3.setOnClickListener {
            GlobalScope.launch {
                try {
                    val r = hi.getSpApy()
                    Log.e("foxxx", "r = $r")
                } catch (t: Throwable) {
                    Log.e("foxxx", "t = ${t.localizedMessage}")
                }
            }
        }
    }
}