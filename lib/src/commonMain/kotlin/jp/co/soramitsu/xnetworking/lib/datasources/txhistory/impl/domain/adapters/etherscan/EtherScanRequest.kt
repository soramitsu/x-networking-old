package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.etherscan

import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.JsonGetRequest

@Suppress("FunctionName")
internal inline fun NormalEtherScanRequest(
    url: String,
    address: String,
    apiKey: String
) = JsonGetRequest(
    url = url,
    queryParams = mapOf(
        "module" to "account",
        "action" to "txlist",
        "address" to address,
        "page" to "1",
        "offset" to "1000",
        "sort" to "desc",
        "apiKey" to apiKey
    )
)

@Suppress("FunctionName")
internal inline fun ErcBepEtherScanRequest(
    url: String,
    contractAddress: String,
    address: String,
    apiKey: String
) = JsonGetRequest(
    url = url,
    queryParams = mapOf(
        "module" to "account",
        "action" to "tokentx",
        "contractAddress" to contractAddress,
        "address" to address,
        "page" to "1",
        "offset" to "1000",
        "sort" to "desc",
        "apiKey" to apiKey
    )
)