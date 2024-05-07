package jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.subsquid

import kotlinx.serialization.Serializable

@Serializable
internal class SubSquidResponse(
    val historyElementsConnection: HistoryElementsConnection
) {
    @Serializable
    class HistoryElementsConnection(
        val pageInfo: PageInfo,
        val edges: List<Node>
    ) {
        @Serializable
        class PageInfo(
            val hasNextPage: Boolean?,
            val endCursor: String
        )

        @Serializable
        class Node(
            val node: HistoryElement
        ) {
            @Serializable
            class HistoryElement(
                val timestamp: Long,
                val id: String,
                val extrinsicIdx: String?,
                val extrinsicHash: String?,
                val address: String,
                val success: Boolean,
                val transfer: Transfer?,
                val reward: Reward?,
            ) {
                @Serializable
                class Reward(
                    val amount: String,
                    val era: Int?,
                    val stash: String?
                )

                @Serializable
                class Transfer(
                    val amount: String,
                    val fee: String?,
                    val from: String,
                    val to: String,
                )
            }
        }
    }
}