package jp.co.soramitsu.xnetworking.networkclient

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.darwin.DarwinClientEngineConfig

actual fun HttpClientEngineConfig.configure() {
    configureFor<DarwinClientEngineConfig> {
        /* empty */
    }
}