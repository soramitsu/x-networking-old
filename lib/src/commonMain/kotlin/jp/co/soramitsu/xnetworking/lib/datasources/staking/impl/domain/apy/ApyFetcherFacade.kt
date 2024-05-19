package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ConfigDAO
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ExternalApiType
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.StakingOption
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ApyFetcher

class ApyFetcherFacade(
    private val configDAO: ConfigDAO,
    private val apyFetcherMap: Map<ExternalApiType, ApyFetcher>
): ApyFetcher() {

    override suspend fun fetch(
        chainId: String,
        selectedCandidates: List<String>?
    ): Map<String, String?> {
        check(
            configDAO.staking(chainId) === StakingOption.PARACHAIN
        ) { "Fetching of Apy is only allowed in networks with paraChain type of staking" }

        val fetcher = checkNotNull(
            apyFetcherMap[configDAO.stakingType(chainId)]
        ) { "Remote Apy Loader could not have been found." }

        return fetcher.fetch(chainId, selectedCandidates)
    }

}