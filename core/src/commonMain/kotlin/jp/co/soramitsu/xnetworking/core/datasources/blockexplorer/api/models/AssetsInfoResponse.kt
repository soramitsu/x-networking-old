package jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.api.models

data class AssetsInfoResponse(
    val id: String,
    val liquidity: String,
    val previousPrice: Double?
)