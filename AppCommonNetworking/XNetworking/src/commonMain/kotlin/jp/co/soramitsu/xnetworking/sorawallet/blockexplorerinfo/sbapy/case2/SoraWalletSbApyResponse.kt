package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case2

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SoraWalletSbApyCase2Response(
    @SerialName("data")
    val data: SoraWalletSbApyCase2ResponseData,
)

@Serializable
data class SoraWalletSbApyCase2ResponseData(
    @SerialName("poolXYKs")
    val poolXYKs: SoraWalletSbApyCase2ResponseDataEntities,
)

@Serializable
data class SoraWalletSbApyCase2ResponseDataEntities(
    @SerialName("nodes")
    val nodes: List<SoraWalletSbApyCase2ResponseDataEntitiesNode>,
)

@Serializable
data class SoraWalletSbApyCase2ResponseDataEntitiesNode(
    @SerialName("id")
    val id: String,
    @SerialName("strategicBonusApy")
    val strategicBonusApy: String? = null,
)
