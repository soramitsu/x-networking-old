package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquery

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ConfigDAO
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ApyFetcher
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient

class SubQueryApyFetcher(
    private val configDAO: ConfigDAO,
    private val restClient: RestClient
): ApyFetcher() {

    override suspend fun fetch(
        chainId: String,
        selectedCandidates: List<String>?
    ): Map<String, String?> {
        require(selectedCandidates?.all { it.startsWith("0x") } ?: true) {
            "Selected Collator Ids are not hex values."
        }

        val roundId = restClient.post(
            request = SubQueryLastRoundRequest(
                url = configDAO.stakingUrl(chainId)
            ),
        ).data.rounds.nodes.firstOrNull()
            ?.id?.toIntOrNull()

        val prevRoundId = roundId?.dec()

        val collatorsInfo = restClient.post(
            request = SubQueryApyRequest(
                url = configDAO.stakingUrl(chainId),
                collatorIds = selectedCandidates,
                roundId = prevRoundId
            )
        ).data.collatorRounds.nodes

        return collatorsInfo.mapNotNull {
            if (it.collatorId == null)
                return@mapNotNull null

            return@mapNotNull  it.collatorId to it.apr
        }.toMap()
    }

}