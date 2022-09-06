package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral.case0

import kotlinx.serialization.Serializable

@Serializable
data class SoraWalletReferrerCase0Response(
    val data: SoraWalletReferrerCase0ResponseData
)

@Serializable
data class SoraWalletReferrerCase0ResponseData(
    val referrerRewards: SoraWalletReferrerCase0ResponseDataRewards
)

@Serializable
data class SoraWalletReferrerCase0ResponseDataRewards(
    val groupedAggregates: List<SoraWalletReferrerCase0ResponseDataRewardsGroup>,
)

@Serializable
data class SoraWalletReferrerCase0ResponseDataRewardsGroup(
    val keys: List<String>,
    val sum: SoraWalletReferrerCase0ResponseDataRewardsGroupSum,
)

@Serializable
data class SoraWalletReferrerCase0ResponseDataRewardsGroupSum(
    val amount: String,
)
