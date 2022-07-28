package jp.co.soramitsu.xnetworking.sora.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SoraEnv(
    @SerialName("DEFAULT_NETWORKS")
    val nodes: List<NodeInfo>
)
