package jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.impl.models

import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.api.AbstractWhitelistedToken

internal data class InternalWhitelistedToken(
    private val tokenAddress: String,
    private val rawIconLink: String
): AbstractWhitelistedToken() {

    override fun getAddress(): String {
        return tokenAddress
    }

    override fun getRawIcon(): String {
        return rawIconLink
    }

    override fun getIconExtension(): String? {
        return when {
            rawIconLink.startsWith("data:image/svg") -> "svg"
            rawIconLink.startsWith("data:image/png") -> "png"
            else -> null
        }
    }

}
