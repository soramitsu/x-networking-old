package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ApyFetcher

class ApyFetcherFacade(
    private val chainsConfigFetcher: ChainsConfigFetcher,
    private val apyFetcherMap: Map<ChainsConfig.ExternalApi.Type, ApyFetcher>
): ApyFetcher {

    override suspend fun fetch(
        chainId: String,
        selectedCandidates: List<String>?
    ): Map<String, String?> {
        val config = chainsConfigFetcher.loadConfigOrGetCached()[chainId]
        val type = requireNotNull(config?.externalApi?.staking?.type) {
            "Type of staking explorer for chain with id - $chainId - is null."
        }

        val fetcher = apyFetcherMap[type]
            ?: error("Remote Apy Loader could not have been found.")

        return fetcher.fetch(chainId, selectedCandidates)
    }

}