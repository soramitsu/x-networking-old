package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquery

import kotlinx.serialization.Serializable

@Serializable
internal class SubQueryApyResponse(
    val collatorRounds: CollatorRounds
) {
    @Serializable
    class CollatorRounds(
        val nodes: List<CollatorApyElement>
    ) {
        @Serializable
        class CollatorApyElement(
            val collatorId: String?,
            val apr: String?
        )
    }
}

@Serializable
internal class SubQueryLastRoundResponse(
    val rounds: Rounds
) {
    @Serializable
    class Rounds(
        val nodes: List<RoundIdElement>
    ) {
        @Serializable
        class RoundIdElement(
            val id: String?
        )
    }
}