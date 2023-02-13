package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case0

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SoraWalletSbApyCase0Response(
    @SerialName("data")
    val data: SoraWalletSbApyCase0ResponseData,
)

@Serializable
data class SoraWalletSbApyCase0ResponseData(
    @SerialName("poolXYKEntities")
    val poolXYKEntities: SoraWalletSbApyCase0ResponseDataEntities,
)

@Serializable
data class SoraWalletSbApyCase0ResponseDataEntities(
    @SerialName("nodes")
    val nodes: List<SoraWalletSbApyCase0ResponseDataEntitiesNode>,
)

@Serializable
data class SoraWalletSbApyCase0ResponseDataEntitiesNode(
    @SerialName("pools")
    val pools: SoraWalletSbApyCase0ResponseDataEntitiesNodesPools,
)

@Serializable
data class SoraWalletSbApyCase0ResponseDataEntitiesNodesPools(
    @SerialName("edges")
    val edges: List<SoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdge>,
)

@Serializable
data class SoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdge(
    @SerialName("node")
    val node: SoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNode,
)

@Serializable
data class SoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNode(
    @SerialName("targetAssetId")
    val targetAssetId: String,
    @SerialName("priceUSD")
    val priceUSD: String? = null,
    @SerialName("strategicBonusApy")
    val strategicBonusApy: String? = null,
)
