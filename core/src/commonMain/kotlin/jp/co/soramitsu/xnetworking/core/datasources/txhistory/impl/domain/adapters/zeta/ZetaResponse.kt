package jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.zeta

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class ZetaResponse(
    val items: List<HistoryItem>,
    @SerialName("next_page_params")
    val nextPageParams: NextPageParams? = null
) {
    @Serializable
    class HistoryItem(
        val timestamp: String,
        val fee: Fee? = null,
        val status: String,
        val to: Address,
        val from: Address,
        val value: String? = null,
        val total: Total? = null,
        val hash: String? = null,
        @SerialName("tx_hash")
        val txHash: String? = null,
    ) {
        @Serializable
        class Fee(
            val type: String,
            val value: String
        )

        @Serializable
        class Address(
            val hash: String
        )

        @Serializable
        class Total(
            val value: String,
            val decimals: Int
        )
    }

    @Serializable
    class NextPageParams(
        @SerialName("block_number")
        val blockNumber: String,
        val index: Long,
        @SerialName("items_count")
        val itemsCount: Long
    )
}