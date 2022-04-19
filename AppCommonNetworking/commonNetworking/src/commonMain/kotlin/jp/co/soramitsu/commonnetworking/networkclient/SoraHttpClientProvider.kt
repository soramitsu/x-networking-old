package jp.co.soramitsu.commonnetworking.networkclient

import io.ktor.client.HttpClient
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json

interface SoraHttpClientProvider {
    fun provide(logging: Boolean, timeout: Long): HttpClient
}

class SoraHttpClientProviderImpl : SoraHttpClientProvider {
    override fun provide(logging: Boolean, timeout: Long): HttpClient {
        return HttpClient(HttpEngineFactory().createEngine()) {
            if (logging) {
                install(Logging) {
                    level = LogLevel.ALL
                    logger = Logger.SIMPLE
                }
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                })
                acceptContentTypes = acceptContentTypes + ContentType.Any
            }
            install(HttpTimeout) {
                requestTimeoutMillis = timeout
                connectTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }
        }
    }
}