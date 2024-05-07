package jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.models

data class TxHistoryItemNested(
    val module: String,
    val method: String,
    val hash: String,
    val data: List<TxHistoryItemParam>
)