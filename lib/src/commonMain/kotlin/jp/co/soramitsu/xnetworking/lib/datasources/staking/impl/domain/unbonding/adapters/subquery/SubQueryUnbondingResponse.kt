package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.unbonding.adapters.subquery

import kotlinx.serialization.Serializable

@Serializable
internal class SubQueryUnbondingResponse(
    val delegatorHistoryElements: DelegatorHistoryElements
) {
    @Serializable
    class DelegatorHistoryElements(
        val nodes: List<HistoryElement>
    ) {
        @Serializable
        class HistoryElement(
            val id: String?,
            val blockNumber: String?,
            val delegatorId: String?,
            val collatorId: String?,
            val timestamp: String?,
            val type: String?,
            val roundId: String?,
            val amount: String?
        )
    }
}