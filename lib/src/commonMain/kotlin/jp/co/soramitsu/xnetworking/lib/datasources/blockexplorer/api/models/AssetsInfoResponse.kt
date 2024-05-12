package jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models

data class AssetsInfoResponse(
    val id: String,
    val liquidity: String,
    val previousPrice: Double?
)