package jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.giantsquid

import kotlinx.serialization.Serializable

@Serializable
internal class GiantSquidResponse(
    val transfers: List<TransferIdWrapper>?,
    val rewards: List<Reward>?,
    val bonds: List<Bond>?,
    val slashes: List<Slash>?
) {
    @Serializable
    class GiantSquidAccount(
        val id: String
    )

    @Serializable
    class TransferIdWrapper(
        val id: String,
        val transfer: Transfer
    ) {
        @Serializable
        class Transfer(
            val amount: String,
            val blockNumber: String,
            val extrinsicHash: String?,
            val from: GiantSquidAccount?,
            val to: GiantSquidAccount?,
            val timestamp: String,
            val success: Boolean,
            val id: String,
        )
    }

    @Serializable
    class Reward(
        val id: String,
        val timestamp: String,
        val blockNumber: Int,
        val extrinsicHash: String?,
        val amount: String,
        val era: String?,
        val validatorId: String?,
        val account: GiantSquidAccount?
    )

    @Serializable
    class Bond(
        val id: String,
        val accountId: String,
        val amount: String,
        val blockNumber: String,
        val extrinsicHash: String?,
        val success: Boolean?,
        val timestamp: String,
        val type: String?
    )

    @Serializable
    class Slash(
        val id: String,
        val accountId: String,
        val amount: String,
        val blockNumber: String,
        val era: String,
        val timestamp: String
    )
}