package jp.co.soramitsu.xnetworking.basic.engines.rest.impl.builder.impl

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

internal actual class ExpectActualHttpClientEngineFactory actual constructor() {
    actual fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig> {
        return Darwin
    }
}