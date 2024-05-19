package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.validators.adapters.sora

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ConfigDAO
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ValidatorsFetcher
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient

class SoraValidatorsFetcher(
    private val configDAO: ConfigDAO,
    private val restClient: RestClient
): ValidatorsFetcher() {

    override suspend fun fetch(
        chainId: String,
        stashAccountAddress: String,
        historicalRange: List<String>
    ): List<String> {
        require(historicalRange.isNotEmpty()) {
            "HistoricalRange provided is empty."
        }

        val validatorsInfoList = restClient.post(
            request = SoraValidatorsRequest(
                url = configDAO.stakingUrl(chainId),
                accountAddress = stashAccountAddress,
                eraFrom = historicalRange.first(),
                eraTo = historicalRange.last()
            )
        ).data.stakingEraNominators.flatMap { it.nominations }

        return validatorsInfoList.mapNotNull { validatorInfo ->
            validatorInfo.validator.id
        }.distinct()
    }

}