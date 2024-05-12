package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.validators.adapters.subquery

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ValidatorsFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLResponseDataWrapper
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient

class SubQueryValidatorsFetcher(
    private val chainsConfigFetcher: ChainsConfigFetcher,
    private val restClient: RestClient
): ValidatorsFetcher {

    override suspend fun fetch(
        chainId: String,
        stashAccountAddress: String,
        historicalRange: List<String>
    ): List<String> {
        val config = chainsConfigFetcher.loadConfigOrGetCached()[chainId]
        val requestUrl =
            requireNotNull(config?.externalApi?.history?.url) {
                "Url for SubQuery stakingExplorer on chain with id - $chainId - is null."
            }

        val validatorsInfoList = restClient.post(
            request = SubQueryValidatorsRequest(
                url = requestUrl,
                accountAddress = stashAccountAddress,
                eraFrom = historicalRange.first(),
                eraTo = historicalRange.last()
            ),
            kSerializer = GraphQLResponseDataWrapper.serializer(
                SubQueryValidatorsResponse.serializer()
            )
        ).data.query?.eraValidatorInfos?.nodes.orEmpty()

        return validatorsInfoList.map(
            transform = SubQueryValidatorsResponse.EraValidatorInfo.Nodes.Node::address
        ).distinct()
    }

}