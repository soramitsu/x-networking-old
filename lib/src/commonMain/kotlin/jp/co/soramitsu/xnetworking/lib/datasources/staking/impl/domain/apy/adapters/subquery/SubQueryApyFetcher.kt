package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquery

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ApyFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLResponseDataWrapper
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient

class SubQueryApyFetcher(
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
                "Url for SubQuery stakingExplorer on chain with id - $chainId - is null."
            }

        val roundId = restClient.post(
            request = SubQueryLastRoundRequest(
                url = requestUrl
            ),
            kSerializer = GraphQLResponseDataWrapper.serializer(
                SubQueryLastRoundResponse.serializer()
            )
        ).data.rounds.nodes.firstOrNull()
            ?.id?.toIntOrNull()

        val prevRoundId = roundId?.dec()

        val collatorsInfo = restClient.post(
            request = SubQueryApyRequest(
                url = requestUrl,
                collatorIds = selectedCandidates,
                roundId = prevRoundId
            ),
            kSerializer = GraphQLResponseDataWrapper.serializer(
                SubQueryApyResponse.serializer()
            )
        ).data.collatorRounds.nodes

        return collatorsInfo.mapNotNull {
            if (it.collatorId == null)
                return@mapNotNull null

            return@mapNotNull  it.collatorId to it.apr
        }.toMap()
    }

}