package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.validators

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ValidatorsFetcher

class ValidatorsFetcherFacade(
    private val chainsConfigFetcher: ChainsConfigFetcher,
    private val validatorsFetcherMap: Map<ChainsConfig.ExternalApi.Type, ValidatorsFetcher>
): ValidatorsFetcher {

    override suspend fun fetch(
        chainId: String,
        stashAccountAddress: String,
        historicalRange: List<String>
    ): List<String> {
        val config = chainsConfigFetcher.loadConfigOrGetCached()[chainId]
        val type = requireNotNull(config?.externalApi?.staking?.type) {
            "Type of staking explorer for chain with id - $chainId - is null."
        }

        val fetcher = validatorsFetcherMap[type]
            ?: error("Remote Unbondings Loader could not have been found.")

        return fetcher.fetch(chainId, stashAccountAddress, historicalRange)
    }

}