package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral.case1

import kotlinx.serialization.Serializable

@Serializable
data class SoraWalletReferrerCase1Response(
    val data: SoraWalletReferrerCase1ResponseData
)

@Serializable
data class SoraWalletReferrerCase1ResponseData(
    val referrerRewards: SoraWalletReferrerCase1ResponseDataRewards
)

@Serializable
data class SoraWalletReferrerCase1ResponseDataRewards(
    val pageInfo: SoraWalletReferrerCase1ResponseDataRewardsPageInfo,
    val nodes: List<SoraWalletReferrerCase1ResponseDataRewardsNode>,
)

@Serializable
data class SoraWalletReferrerCase1ResponseDataRewardsPageInfo(
    val hasNextPage: Boolean,
    val endCursor: String?,
)

@Serializable
data class SoraWalletReferrerCase1ResponseDataRewardsNode(
    val referral: String,
    val amount: String,
)
