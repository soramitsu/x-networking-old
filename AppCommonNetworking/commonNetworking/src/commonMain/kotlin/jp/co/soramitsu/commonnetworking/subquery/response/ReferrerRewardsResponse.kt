package jp.co.soramitsu.commonnetworking.subquery.response

import kotlinx.serialization.Serializable

@Serializable
data class ReferrerRewardsResponse(
    val data: ReferrerRewardsResponseData
)

@Serializable
data class ReferrerRewardsResponseData(
    val referrerRewards: ReferrerRewardsData
)

@Serializable
data class ReferrerRewardsData(
    val groupedAggregates: List<ReferrerRewardsItem>,
)

@Serializable
data class ReferrerRewardsItem(
    val keys: List<String>,
    val sum: ReferrerRewardsItemAmount,
)

@Serializable
data class ReferrerRewardsItemAmount(
    val amount: String,
)
