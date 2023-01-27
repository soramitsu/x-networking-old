package jp.co.soramitsu.xnetworking.networkclient

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

interface SoramitsuHttpClientProvider {
    fun provide(config: NetworkClientConfig): HttpClient
}

class SoramitsuHttpClientProviderImpl : SoramitsuHttpClientProvider {
    override fun provide(config: NetworkClientConfig): HttpClient {
        return HttpClient(HttpEngineFactory().createEngine()) {
            if (config.logging) {
                install(Logging) {
                    level = LogLevel.ALL
                    logger = Logger.SIMPLE
                }
            }
            expectSuccess = true
            install(ContentNegotiation) {
                json(
                    config.json,
                    contentType = ContentType.Any
                )
            }
            install(HttpTimeout) {
                this.requestTimeoutMillis = config.requestTimeoutMillis
                this.connectTimeoutMillis = config.connectTimeoutMillis
                this.socketTimeoutMillis = config.socketTimeoutMillis
            }
        }
    }
}