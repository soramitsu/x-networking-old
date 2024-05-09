package jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.subquery

import kotlinx.serialization.Serializable

@Serializable
internal class SubQueryResponse(
    val historyElements: HistoryElements
) {
    @Serializable
    class HistoryElements(
        val pageInfo: PageInfo,
        val nodes: Array<Node>
    ) {
        @Serializable
        class PageInfo(
            val startCursor: String?,
            val endCursor: String?
        )

        @Serializable
        class Node(
            val id: String,
            val timestamp: String,
            val address: String,
            val reward: Rewards?,
            val transfer: Transfer?,
            val extrinsic: Extrinsic?
        ) {
            @Serializable
            class Rewards(
                val era: Int,
                val amount: String,
                val isReward: Boolean,
                val validator: String,
                val assetId: String?
            )

            @Serializable
            class Transfer(
                val amount: String,
                val to: String,
                val from: String,
                val fee: String,
                val block: String,
                val success: Boolean,
                val extrinsicHash: String?, // nullable since not all transfers not hash hash on SubQuery
                val assetId: String?
            )

            @Serializable
            class Extrinsic(
                val hash: String,
                val module: String,
                val call: String,
                val fee: String,
                val success: Boolean,
                val assetId: String?
            )
        }
    }
}