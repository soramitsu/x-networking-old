package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.validators.adapters.subsquid

import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ValidatorsFetcher

class SubSquidValidatorsFetcher: ValidatorsFetcher() {

    override suspend fun fetch(
        chainId: String,
        stashAccountAddress: String,
        historicalRange: List<String>
    ): List<String> {
        return emptyList()
    }

}