package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.etherscan

import kotlinx.serialization.Serializable

@Serializable
internal class EtherScanResponse(
    val status: Int,
    val message: String,
    val result: List<HistoryElement> = emptyList()
) {
    @Serializable
    class HistoryElement(
        val blockNumber: String,
        val timeStamp: Long,
        val hash: String,
        val nonce: String,
        val blockHash: String,
        val from: String,
        val to: String,
        val contractAddress: String,
        val value: String,
        val gas: String,
        val gasPrice: String,
        val gasUsed: String,
        val isError: Int
    )
}