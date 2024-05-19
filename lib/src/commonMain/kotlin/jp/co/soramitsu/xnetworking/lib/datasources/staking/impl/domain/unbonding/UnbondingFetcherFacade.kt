package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.unbonding

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ConfigDAO
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ExternalApiType
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.StakingOption
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.models.Unbonding
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.UnbondingFetcher

class UnbondingFetcherFacade(
    private val configDAO: ConfigDAO,
    private val unbondingFetcherMap: Map<ExternalApiType, UnbondingFetcher>
): UnbondingFetcher() {

    override suspend fun fetch(
        chainId: String,
        delegatorAddress: String,
        collatorAddress: String
    ): List<Unbonding> {
        check(
            configDAO.staking(chainId) === StakingOption.PARACHAIN
        ) { "Fetching of Unbondings is only allowed in networks with paraChain type of staking" }

        val fetcher = checkNotNull(
            unbondingFetcherMap[configDAO.stakingType(chainId)]
        ) { "Remote Unbondings Loader could not have been found." }

        return fetcher.fetch(chainId, delegatorAddress, collatorAddress)
    }

}