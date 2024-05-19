package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.validators

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ConfigDAO
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ExternalApiType
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.StakingOption
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ValidatorsFetcher

class ValidatorsFetcherFacade(
    private val configDAO: ConfigDAO,
    private val validatorsFetcherMap: Map<ExternalApiType, ValidatorsFetcher>
): ValidatorsFetcher() {

    override suspend fun fetch(
        chainId: String,
        stashAccountAddress: String,
        historicalRange: List<String>
    ): List<String> {
        check(
            configDAO.staking(chainId) === StakingOption.RELAYCHAIN
        ) { "Fetching of Validators is only allowed in networks with relayChain type of staking" }

        val fetcher = checkNotNull(
            validatorsFetcherMap[configDAO.stakingType(chainId)]
        ) { "Remote Unbondings Loader could not have been found." }

        return fetcher.fetch(chainId, stashAccountAddress, historicalRange)
    }

}