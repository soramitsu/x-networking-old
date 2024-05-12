package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.unbonding

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.models.Unbonding
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.UnbondingFetcher

class UnbondingFetcherFacade(
    private val chainsConfigFetcher: ChainsConfigFetcher,
    private val unbondingFetcherMap: Map<ChainsConfig.ExternalApi.Type, UnbondingFetcher>
): UnbondingFetcher {

    override suspend fun fetch(
        chainId: String,
        delegatorAddress: String,
        collatorAddress: String
    ): List<Unbonding> {
        val config = chainsConfigFetcher.loadConfigOrGetCached()[chainId]
        val type = requireNotNull(config?.externalApi?.staking?.type) {
            "Type of staking explorer for chain with id - $chainId - is null."
        }

        val fetcher = unbondingFetcherMap[type]
            ?: error("Remote Unbondings Loader could not have been found.")

        return fetcher.fetch(chainId, delegatorAddress, collatorAddress)
    }

}