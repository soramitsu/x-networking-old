package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.validators.adapters.subquery

import kotlinx.serialization.Serializable

@Serializable
internal class SubQueryValidatorsResponse(
    val query: EraValidatorInfo?
) {
    @Serializable
    class EraValidatorInfo(
        val eraValidatorInfos: Nodes?
    ) {
        @Serializable
        class Nodes(
            val nodes: List<Node>?
        ) {
            @Serializable
            class Node(
                val id: String,
                val address: String,
                val era: String,
                val total: String,
                val own: String
            )
        }
    }
}