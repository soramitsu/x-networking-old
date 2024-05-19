package jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.impl

import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.api.AbstractWhitelistedToken
import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.api.WhitelistRepository
import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.impl.models.InternalWhitelistedToken
import jp.co.soramitsu.xnetworking.lib.engines.utils.JsonGetRequest
import jp.co.soramitsu.xnetworking.lib.engines.utils.fieldOrNull
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.JsonArray

class WhitelistRepositoryImpl(
    private val restClient: RestClient,
    private val configRequestUrl: String
): WhitelistRepository() {

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
            request = JsonGetRequest(
                url = configRequestUrl,
                responseDeserializer = JsonArray.serializer()
            )
        ).map {
            InternalWhitelistedToken(
                tokenAddress = it.fieldOrNull("address").orEmpty(),
                rawIconLink = it.fieldOrNull("icon").orEmpty(),
            )
        }
    }

}