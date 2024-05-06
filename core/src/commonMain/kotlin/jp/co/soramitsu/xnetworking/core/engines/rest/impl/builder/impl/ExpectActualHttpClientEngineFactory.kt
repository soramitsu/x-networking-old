package jp.co.soramitsu.xnetworking.core.engines.rest.impl.builder.impl

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

internal expect class ExpectActualHttpClientEngineFactory constructor() {
    fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
}