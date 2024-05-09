package jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.oklink

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class OkLinkResponse(
    val code: Int,
    val msg: String,
    val data: List<HistoryPage> = emptyList()
) {
    @Serializable
    class HistoryPage(
        val page: Int,
        val limit: Int,
        val totalPage: String,
        @SerialName("transactionLists")
        val items: List<HistoryItem> = emptyList()
    ) {
        @Serializable
        class HistoryItem(
            val txId: String,
            val methodId: String,
            val blockHash: String,
            val height: String,
            val transactionTime: Long,
            val from: String,
            val to: String,
            val isFromContract: Boolean,
            val isToContract: Boolean,
            val amount: String,
            val transactionSymbol: String,
            val txFee: String,
            val state: String,
            val tokenId: String,
            val tokenContractAddress: String,
            val challengeStatus: String,
            val l1OriginHash: String
        )
    }
}