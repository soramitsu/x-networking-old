package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.fiat.case2

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SoraWalletFiatCase2Response(
    @SerialName("data")
    val data: SoraWalletFiatCase2ResponseData,
)

@Serializable
data class SoraWalletFiatCase2ResponseData(
    @SerialName("entities")
    val entities: SoraWalletFiatCase2ResponseDataEntities,
)

@Serializable
data class SoraWalletFiatCase2ResponseDataEntities(
    @SerialName("nodes")
    val nodes: List<SoraWalletFiatCase2ResponseDataEntitiesNode>,
    @SerialName("pageInfo")
    val pageInfo: SoraWalletFiatCase2ResponseDataEntitiesPageInfo,
)

@Serializable
data class SoraWalletFiatCase2ResponseDataEntitiesNode(
    @SerialName("id")
    val id: String,
    @SerialName("priceUSD")
    val priceUSD: String,
)

@Serializable
data class SoraWalletFiatCase2ResponseDataEntitiesPageInfo(
    @SerialName("hasNextPage")
    val hasNextPage: Boolean,
    @SerialName("endCursor")
    val endCursor: String?,
)
