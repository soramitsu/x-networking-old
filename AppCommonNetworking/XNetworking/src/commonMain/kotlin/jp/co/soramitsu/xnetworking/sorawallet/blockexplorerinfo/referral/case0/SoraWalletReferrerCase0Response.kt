package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral.case0

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SoraWalletReferrerCase0Response(
    @SerialName("data")
    val data: SoraWalletReferrerCase0ResponseData
)

@Serializable
data class SoraWalletReferrerCase0ResponseData(
    @SerialName("referrerRewards")
    val referrerRewards: SoraWalletReferrerCase0ResponseDataRewards
)

@Serializable
data class SoraWalletReferrerCase0ResponseDataRewards(
    @SerialName("groupedAggregates")
    val groupedAggregates: List<SoraWalletReferrerCase0ResponseDataRewardsGroup>,
)

@Serializable
data class SoraWalletReferrerCase0ResponseDataRewardsGroup(
    @SerialName("keys")
    val keys: List<String>,
    @SerialName("sum")
    val sum: SoraWalletReferrerCase0ResponseDataRewardsGroupSum,
)

@Serializable
data class SoraWalletReferrerCase0ResponseDataRewardsGroupSum(
    @SerialName("amount")
    val amount: String,
)
