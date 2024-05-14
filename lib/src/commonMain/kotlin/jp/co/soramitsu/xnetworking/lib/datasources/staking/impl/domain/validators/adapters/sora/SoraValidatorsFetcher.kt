package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.validators.adapters.sora

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ValidatorsFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLResponseDataWrapper
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient

class SoraValidatorsFetcher(
    private val chainsConfigFetcher: ChainsConfigFetcher,
    private val restClient: RestClient
): ValidatorsFetcher {

    override suspend fun fetch(
        chainId: String,
        stashAccountAddress: String,
        historicalRange: List<String>
    ): List<String> {
        require(historicalRange.isNotEmpty()) {
            "HistoricalRange provided is empty."
        }

        val config = chainsConfigFetcher.loadConfigOrGetCached()[chainId]
        val requestUrl =
            requireNotNull(config?.externalApi?.staking?.url) {
                "Url for Sora stakingExplorer on chain with id - $chainId - is null."
            }

        val validatorsInfoList = restClient.post(
            request = SoraValidatorsRequest(
                url = requestUrl,
                accountAddress = stashAccountAddress,
                eraFrom = historicalRange.first(),
                eraTo = historicalRange.last()
            ),
            kSerializer = GraphQLResponseDataWrapper.serializer(
                SoraValidatorsResponse.serializer()
            )
        ).data.stakingEraNominators.flatMap { it.nominations }

        return validatorsInfoList.mapNotNull { validatorInfo ->
            validatorInfo.validator.id
        }.distinct()
    }

}