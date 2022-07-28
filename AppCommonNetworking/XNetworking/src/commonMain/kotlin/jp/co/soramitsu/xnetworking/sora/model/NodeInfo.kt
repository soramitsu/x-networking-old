package jp.co.soramitsu.xnetworking.sora.model

import kotlinx.serialization.Serializable

@Serializable
data class NodeInfo(
    val chain: String,
    val name: String,
    val address: String
)
