package jp.co.soramitsu.xnetworking.txhistory

class TxHistoryResult<R>(
    val endCursor: String?,
    val endReached: Boolean,
    val page: Long,
    val items: List<R>,
    val hasError: Boolean
)