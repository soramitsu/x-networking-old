package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case0

import kotlinx.serialization.Serializable

@Serializable
data class SoraWalletSbApyCase0Response(val data: SoraWalletSbApyCase0ResponseData)

@Serializable
data class SoraWalletSbApyCase0ResponseData(val poolXYKEntities: SoraWalletSbApyCase0ResponseDataEntities)

@Serializable
data class SoraWalletSbApyCase0ResponseDataEntities(val nodes: List<SoraWalletSbApyCase0ResponseDataEntitiesNode>)

@Serializable
data class SoraWalletSbApyCase0ResponseDataEntitiesNode(val pools: SoraWalletSbApyCase0ResponseDataEntitiesNodesPools)

@Serializable
data class SoraWalletSbApyCase0ResponseDataEntitiesNodesPools(val edges: List<SoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdge>)

@Serializable
data class SoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdge(val node: SoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNode)

@Serializable
data class SoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNode(
    val targetAssetId: String,
    val priceUSD: String? = null,
    val strategicBonusApy: String? = null
)