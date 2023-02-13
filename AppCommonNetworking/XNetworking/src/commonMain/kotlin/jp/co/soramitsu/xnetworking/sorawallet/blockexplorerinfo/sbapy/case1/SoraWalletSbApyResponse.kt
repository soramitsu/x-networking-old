package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case1

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SoraWalletSbApyCase1Response(
    @SerialName("data")
    val data: SoraWalletSbApyCase1ResponseData,
)

@Serializable
data class SoraWalletSbApyCase1ResponseData(
    @SerialName("poolXYKs")
    val poolXYKs: SoraWalletSbApyCase1ResponseDataEntities,
)

@Serializable
data class SoraWalletSbApyCase1ResponseDataEntities(
    @SerialName("nodes")
    val nodes: List<SoraWalletSbApyCase1ResponseDataEntitiesNode>,
)

@Serializable
data class SoraWalletSbApyCase1ResponseDataEntitiesNode(
    @SerialName("id")
    val id: String,
    @SerialName("priceUSD")
    val priceUSD: String? = null,
    @SerialName("strategicBonusApy")
    val strategicBonusApy: String? = null,
)
