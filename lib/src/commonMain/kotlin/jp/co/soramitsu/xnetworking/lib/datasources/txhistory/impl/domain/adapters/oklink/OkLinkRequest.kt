package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.oklink

import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.JsonGetRequest

@Suppress("FunctionName")
internal inline fun OkLinkRequest(
    url: String,
    address: String,
    symbol: String,
    apiKey: String
) = JsonGetRequest(
    url = url,
    headers = mapOf(
        "Ok-Access-Key" to apiKey
    ),
    queryParams = mapOf(
        "address" to address,
        "symbol" to symbol
    )
)