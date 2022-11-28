package jp.co.soramitsu.xnetworking.networkclient

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.okhttp.OkHttpConfig
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

actual fun HttpClientEngineConfig.configure() {
    configureFor<OkHttpConfig> {
        preconfigured = OkHttpClient.Builder()
            .pingInterval(SoramitsuNetworkClient.WEB_SOCKET_PING_INTERVAL, TimeUnit.MILLISECONDS)
            .build()
    }
}