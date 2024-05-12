package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.unbonding.adapters.subsquid

import kotlinx.serialization.Serializable

@Serializable
internal class SubSquidUnbondingResponse(
    val rewards: List<Reward>
) {
    @Serializable
    class Reward(
        val id: String,
        val amount: String?,
        val blockNumber: Int?,
        val round: Int?,
        val timestamp: String?,
        val extrinsicHash: String?
    )
}