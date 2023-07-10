package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.assets.case0

import jp.co.soramitsu.xnetworking.common.ResponsePageInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SoraWalletAssetsCase0Response(
    @SerialName("data")
    val data: SoraWalletAssetsCase0ResponseData,
)

@Serializable
data class SoraWalletAssetsCase0ResponseData(
    @SerialName("entities")
    val entities: SoraWalletAssetsCase0ResponseDataEntities,
)

@Serializable
data class SoraWalletAssetsCase0ResponseDataEntities(
    @SerialName("nodes")
    val nodes: List<SoraWalletAssetsCase0ResponseDataEntitiesNode>,
    @SerialName("pageInfo")
    val pageInfo: ResponsePageInfo,
)

@Serializable
data class SoraWalletAssetsCase0ResponseDataEntitiesNode(
    @SerialName("id")
    val id: String,
    @SerialName("liquidity")
    val liquidity: String,
)
