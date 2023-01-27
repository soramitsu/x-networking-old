package jp.co.soramitsu.xnetworking.networkclient

import kotlinx.serialization.json.Json

data class NetworkClientConfig(
    val logging: Boolean,
    val requestTimeoutMillis: Long,
    val connectTimeoutMillis: Long,
    val socketTimeoutMillis: Long,
    val json: Json
)
