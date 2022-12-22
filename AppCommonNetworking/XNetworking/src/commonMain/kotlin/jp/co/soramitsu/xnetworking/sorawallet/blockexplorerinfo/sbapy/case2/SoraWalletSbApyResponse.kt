package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case2

import kotlinx.serialization.Serializable

@Serializable
data class SoraWalletSbApyCase2Response(val data: SoraWalletSbApyCase2ResponseData)

@Serializable
data class SoraWalletSbApyCase2ResponseData(val poolXYKs: SoraWalletSbApyCase2ResponseDataEntities)

@Serializable
data class SoraWalletSbApyCase2ResponseDataEntities(val nodes: List<SoraWalletSbApyCase2ResponseDataEntitiesNode>)

@Serializable
data class SoraWalletSbApyCase2ResponseDataEntitiesNode(
    val id: String,
    val strategicBonusApy: String? = null
)
