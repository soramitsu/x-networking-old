package jp.co.soramitsu.appxnetworking

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.co.soramitsu.xnetworking.core.datasources.chainsconfig.impl.ChainsConfigFetcherImpl
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.HistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.TxFilter
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.JsonGetRequest
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.etherscan.EtherScanHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.oklink.OkLinkHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.reef.ReefHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.sora.SoraHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.subquery.SubQueryHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.subsquid.SubSquidHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.westend.WestendHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.zeta.ZetaHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.core.engines.apollo.impl.ApolloClientStoreImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DepBuilder.build(applicationContext)
        setContent {
            XNetworkingComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(
                        cursor = null,
                        address = "5Gh8uB1ZgMw75gUHUspfzY6jxqqNcAwBvHkmA6CK3A2C7j7n",
                        chainId = "7834781d38e4798d548e34ec947d19deea29df148a7bf32484b7b24dacf8d4b7",
                        assetId = "55697eb0-ca77-47e3-a436-b05460ab1ead",
                        historyInfoRemoteLoader = ReefHistoryInfoRemoteLoader(
                            chainsConfigFetcher = ChainsConfigFetcherImpl(
                                restClient = DepBuilder.restClient,
                                chainsRequestBuilder = {
                                    JsonGetRequest(url = "https://raw.githubusercontent.com/soramitsu/shared-features-utils/develop-free/chains/v9/chains_dev.json")
                                }
                            ),
                            restClient = DepBuilder.restClient
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun MainScreen(
    historyInfoRemoteLoader: HistoryInfoRemoteLoader,
    cursor: String?,
    address: String,
    chainId: String,
    assetId: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Button(
            onClick = {
                GlobalScope.launch {
                    try {
                        Log.e("foxxx", "r start btn 1")
                        val r = historyInfoRemoteLoader.loadHistoryInfo(
                            pageCount = 1,
                            cursor = cursor,
                            signAddress = address,
                            chainId = chainId,
                            assetId = assetId,
                            filters = setOf(TxFilter.TRANSFER)
                        )
                        Log.e("foxxx", "r = ${r}")
                    } catch (t: Throwable) {
                        Log.e("foxxx", "t= ${t.localizedMessage}")
                        t.printStackTrace()
                    }
                }
            },
            content = {
                Text(text = "Custom BlockExplorer #2")
            },
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(
            onClick = {
//                GlobalScope.launch {
//                    Log.e("foxxx", "r start btn 2")
//                    try {
//                        val r = DepBuilder.networkService.getHistoryFearless(1)
//                        Log.e(
//                            "foxxx",
//                            "r = ${r.endReached} ${r.page} ${r.items.size} ${r.errorMessage}"
//                        )
//                    } catch (t: Throwable) {
//                        Log.e("foxxx", "t= ${t.localizedMessage}")
//                        t.printStackTrace()
//                    }
//                }
            },
            content = {
                Text(text = "Get TxHistoryItems")
            },
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(
            onClick = {
//                GlobalScope.launch {
//                    Log.e("foxxx", "r start btn 2")
//                    try {
//                        val r = DepBuilder.networkService.getPeers("")
//                        Log.e(
//                            "foxxx",
//                            "r = ${r.size}"
//                        )
//                    } catch (t: Throwable) {
//                        Log.e("foxxx", "t= ${t.localizedMessage}")
//                        t.printStackTrace()
//                    }
//                }
            },
            content = {
                Text(text = "Get Peers")
            },
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(
            onClick = {
//                GlobalScope.launch {
//                    Log.e("foxxx", "r start btn 3")
//                    try {
//                        val r = DepBuilder.networkService.getSoraConfig()
//                        Log.e("foxxx", "r = $r")
//                    } catch (t: Throwable) {
//                        Log.e("foxxx", "t = ${t.localizedMessage}")
//                        t.printStackTrace()
//                    }
//                }
            },
            content = {
                Text(text = "btn3")
            },
        )
    }
}
