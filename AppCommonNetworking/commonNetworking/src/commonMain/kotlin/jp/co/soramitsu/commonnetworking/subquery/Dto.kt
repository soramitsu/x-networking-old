package jp.co.soramitsu.commonnetworking.subquery

data class SoraHistoryInfo(
    val endCursor: String?,
    val hasNextPage: Boolean,
    val items: List<SoraHistoryItem>
)

data class SoraHistoryItem(
    val id: String,
    val blockHash: String,
    val module: String,
    val method: String,
    val timestamp: String,
    val networkFee: String,
    val success: Boolean,
    val data: List<SoraHistoryItemParam>?,
    val nestedData: List<SoraHistoryItemNested>?
)

data class SoraHistoryItemParam(
    val paramName: String,
    val paramValue: String,
)

data class SoraHistoryItemNested(
    val module: String,
    val method: String,
    val data: List<SoraHistoryItemParam>
)

data class SbApyInfo(
    val tokenId: String,
    val priceUsd: Double? = null,
    val sbApy: Double? = null,
)

data class ReferrerRewardsInfo(
    val rewards: List<ReferrerRewards>,
)

data class ReferrerRewards(
    val referral: String,
    val amount: String,
)
