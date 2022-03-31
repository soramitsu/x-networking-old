package jp.co.soramitsu.appcommonnetworking

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import jp.co.soramitsu.commonnetworking.FearlessChainsBuilder
import jp.co.soramitsu.commonnetworking.HistoryReader
import jp.co.soramitsu.commonnetworking.SoraNetworkClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)

        val c = SoraNetworkClient(logging = true)
        val f = FearlessChainsBuilder(
            c,
            "https://raw.githubusercontent.com/arvifox/arvifoxandroid/felete/felete/"
        )
        val hi = HistoryReader(c, "https://api.subquery.network/sq/sora-xor/sora-dev")

        btn1.setOnClickListener {
            GlobalScope.launch {
                try {
                    val r = f.getChains("2.0.8")
                    Log.e("foxxx", "r = ${r.size}")
                } catch (t: Throwable) {
                    Log.e("foxxx", "t= ${t.localizedMessage}")
                }
            }
        }

        btn2.setOnClickListener {
            GlobalScope.launch {
                try {
                    val r = hi.getSoraSubQuery(
                        10, "cnVkoGs3rEMqLqY27c2nfVXJRGdzNJk2ns78DcqtppaSRe8qm"
                    )
                    Log.e("foxxx", "r = $r")
                } catch (t: Throwable) {
                    Log.e("foxxx", "t= ${t.localizedMessage}")
                }
            }
        }
    }
}