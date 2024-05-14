package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquid

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ApyFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLResponseDataWrapper
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient

class SubSquidApyFetcher(
    private val chainsConfigFetcher: ChainsConfigFetcher,
    private val restClient: RestClient
): ApyFetcher {

    override suspend fun fetch(
        chainId: String,
        selectedCandidates: List<String>?
    ): Map<String, String?> {
        require(selectedCandidates?.all { it.startsWith("0x") } ?: true) {
            "Selected Collator Ids are not hex values."
        }

        val config = chainsConfigFetcher.loadConfigOrGetCached()[chainId]
        val requestUrl =
            requireNotNull(config?.externalApi?.staking?.url) {
                "Url for SubSquid stakingExplorer on chain with id - $chainId - is null."
            }

        val stakers = restClient.post(
            request = SubSquidApyRequest(
                url = requestUrl
            ),
            kSerializer = GraphQLResponseDataWrapper.serializer(
                SubSquidApyResponse.serializer()
            )
        ).data.stakers

        return stakers.mapNotNull {
            if (it.stashId == null)
                return@mapNotNull null

            return@mapNotNull  it.stashId to it.apr24h
        }.toMap()
    }

}