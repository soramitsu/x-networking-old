package jp.co.soramitsu.xnetworking.core.engines.rest.impl.builder.impl

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

internal actual class ExpectActualHttpClientEngineFactory actual constructor() {
    actual fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig> {
        return OkHttp
    }
}