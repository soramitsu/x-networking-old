package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.fiat.case2

import kotlinx.serialization.Serializable

@Serializable
data class SoraWalletFiatCase2Response(
    val data: SoraWalletFiatCase2ResponseData,
)

@Serializable
data class SoraWalletFiatCase2ResponseData(
    val entities: SoraWalletFiatCase2ResponseDataEntities,
)

@Serializable
data class SoraWalletFiatCase2ResponseDataEntities(
    val nodes: List<SoraWalletFiatCase2ResponseDataEntitiesNode>,
    val pageInfo: SoraWalletFiatCase2ResponseDataEntitiesPageInfo,
)

@Serializable
data class SoraWalletFiatCase2ResponseDataEntitiesNode(
    val id: String,
    val priceUSD: String,
)

@Serializable
data class SoraWalletFiatCase2ResponseDataEntitiesPageInfo(
    val hasNextPage: Boolean,
    val endCursor: String?,
)
