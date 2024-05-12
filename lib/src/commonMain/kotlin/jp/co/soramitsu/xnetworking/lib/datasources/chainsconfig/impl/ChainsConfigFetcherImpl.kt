package jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.impl

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.AbstractRestServerRequest
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.builtins.ListSerializer

class ChainsConfigFetcherImpl(
    private val restClient: RestClient,
    private val chainsRequest: AbstractRestServerRequest
): ChainsConfigFetcher {

    private val cachedValueReadWriteMutex: Mutex = Mutex()
    private var cachedValue: Map<String, ChainsConfig>? = null

    override suspend fun loadConfigOrGetCached(): Map<String, ChainsConfig> {
        // Pattern: double-checked locking for singletons
        if (cachedValue == null) {
            cachedValueReadWriteMutex.withLock {
                if (cachedValue == null)
                    cachedValue = restClient.get(
                        request = chainsRequest,
                        kSerializer = ListSerializer(ChainsConfig.serializer())
                    ).associateBy { it.chainId }
            }
        }
        return cachedValue!!
    }
}