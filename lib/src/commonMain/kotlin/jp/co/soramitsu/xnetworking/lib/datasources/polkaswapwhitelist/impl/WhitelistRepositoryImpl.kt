package jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.impl

import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.api.AbstractWhitelistedToken
import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.api.WhitelistRepository
import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.impl.models.InternalWhitelistedToken
import jp.co.soramitsu.xnetworking.lib.engines.apollo.impl.utils.asJsonObjectNullable
import jp.co.soramitsu.xnetworking.lib.engines.apollo.impl.utils.getPrimitiveContentOrEmpty
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.AbstractRestServerRequest
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.JsonArray

class WhitelistRepositoryImpl(
    private val restClient: RestClient,
    private val configRequest: AbstractRestServerRequest
): WhitelistRepository {

    private val cachedValueReadWriteMutex: Mutex = Mutex()
    private var cachedValue: List<AbstractWhitelistedToken>? = null

    override suspend fun getWhitelistedTokens(): List<AbstractWhitelistedToken> {
        // Pattern: double-checked locking for singletons
        if (cachedValue == null) {
            cachedValueReadWriteMutex.withLock {
                if (cachedValue == null)
                    cachedValue = fetchConfig()
            }
        }
        return cachedValue!!
    }

    private suspend fun fetchConfig(): List<AbstractWhitelistedToken> {
        return restClient.get(
            request = configRequest,
            kSerializer = JsonArray.serializer()
        ).map {
            InternalWhitelistedToken(
                tokenAddress = it.asJsonObjectNullable.getPrimitiveContentOrEmpty("address"),
                rawIconLink = it.asJsonObjectNullable.getPrimitiveContentOrEmpty("icon"),
            )
        }
    }

}