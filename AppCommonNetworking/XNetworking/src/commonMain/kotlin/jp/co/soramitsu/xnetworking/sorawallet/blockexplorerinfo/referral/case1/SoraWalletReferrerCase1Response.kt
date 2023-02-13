package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral.case1

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SoraWalletReferrerCase1Response(
    @SerialName("data")
    val data: SoraWalletReferrerCase1ResponseData
)

@Serializable
data class SoraWalletReferrerCase1ResponseData(
    @SerialName("referrerRewards")
    val referrerRewards: SoraWalletReferrerCase1ResponseDataRewards
)

@Serializable
data class SoraWalletReferrerCase1ResponseDataRewards(
    @SerialName("pageInfo")
    val pageInfo: SoraWalletReferrerCase1ResponseDataRewardsPageInfo,
    @SerialName("nodes")
    val nodes: List<SoraWalletReferrerCase1ResponseDataRewardsNode>,
)

@Serializable
data class SoraWalletReferrerCase1ResponseDataRewardsPageInfo(
    @SerialName("hasNextPage")
    val hasNextPage: Boolean,
    @SerialName("endCursor")
    val endCursor: String?,
)

@Serializable
data class SoraWalletReferrerCase1ResponseDataRewardsNode(
    @SerialName("referral")
    val referral: String,
    @SerialName("amount")
    val amount: String,
)
