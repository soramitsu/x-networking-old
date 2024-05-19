package jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.api

import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.RestClientException
import kotlin.coroutines.cancellation.CancellationException

abstract class WhitelistRepository {

    @Throws(
        RestClientException::class,
        CancellationException::class
    )
    abstract suspend fun getWhitelistedTokens(): List<AbstractWhitelistedToken>

}