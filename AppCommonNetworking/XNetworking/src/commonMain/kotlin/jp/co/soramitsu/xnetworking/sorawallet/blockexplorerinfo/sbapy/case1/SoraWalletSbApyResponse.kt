package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case1

import kotlinx.serialization.Serializable

@Serializable
data class SoraWalletSbApyCase1Response(val data: SoraWalletSbApyCase1ResponseData)

@Serializable
data class SoraWalletSbApyCase1ResponseData(val poolXYKs: SoraWalletSbApyCase1ResponseDataEntities)

@Serializable
data class SoraWalletSbApyCase1ResponseDataEntities(val nodes: List<SoraWalletSbApyCase1ResponseDataEntitiesNode>)

@Serializable
data class SoraWalletSbApyCase1ResponseDataEntitiesNode(
    val id: String,
    val priceUSD: String? = null,
    val strategicBonusApy: String? = null
)
