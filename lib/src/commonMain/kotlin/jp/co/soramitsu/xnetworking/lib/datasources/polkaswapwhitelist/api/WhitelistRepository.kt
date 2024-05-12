package jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.api

import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.RestClientException
import kotlin.coroutines.cancellation.CancellationException

interface WhitelistRepository {

    @Throws(
        RestClientException::class,
        CancellationException::class
    )
    suspend fun getWhitelistedTokens(requestUrl: String): List<AbstractWhitelistedToken>

    companion object {
        const val requestUrl = "https://whitelist.polkaswap2.io/whitelist.json"
    }

}