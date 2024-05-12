package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.zeta

import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.JsonGetRequest

@Suppress("FunctionName")
internal inline fun TransactionsZetaRequest(
    url: String,
    address: String
) = JsonGetRequest(
    url = StringBuilder().apply {
        append(url)

        if (lastOrNull() != '/') {
            append("/")
        }

        if (address.isNotBlank()) {
            append(address)
            append("/")
        }

        append("transactions")
    }.toString(),
    queryParams = null
)

@Suppress("FunctionName")
internal inline fun TokenTransfersZetaRequest(
    url: String,
    address: String,
    assetId: String
) = JsonGetRequest(
    url = StringBuilder().apply {
        append(url)

        if (lastOrNull() != '/') {
            append("/")
        }

        if (address.isNotBlank()) {
            append(address)
            append("/")
        }

        append("token-transfers")
    }.toString(),
    queryParams = mapOf(
        "token" to assetId
    )
)