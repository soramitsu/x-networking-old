package jp.co.soramitsu.commonnetworking.networkclient

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface SoramitsuHttpClientProvider {
    fun provide(logging: Boolean, timeout: Long): HttpClient
}

class SoramitsuHttpClientProviderImpl : SoramitsuHttpClientProvider {
    override fun provide(logging: Boolean, timeout: Long): HttpClient {
        return HttpClient(HttpEngineFactory().createEngine()) {
            if (logging) {
                install(Logging) {
                    level = LogLevel.ALL
                    logger = Logger.SIMPLE
                }
            }
            expectSuccess = true
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    },
                    contentType = ContentType.Any
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = timeout
                connectTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }
        }
    }
}