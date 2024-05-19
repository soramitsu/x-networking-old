package jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.adapters

import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.adapters.SbApyFetcher
import jp.co.soramitsu.xnetworking.sorawallet.GetSbApyInfoQuery
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.SbApyInfoResponse
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ConfigDAO
import jp.co.soramitsu.xnetworking.lib.engines.apollo.api.ApolloClientStore

class SoraSbApyFetcher(
    private val apolloClientStore: ApolloClientStore,
    private val configDAO: ConfigDAO
): SbApyFetcher() {

    override suspend fun fetch(
        chainId: String
    ): List<SbApyInfoResponse> {
        val result = mutableListOf<SbApyInfoResponse>()

        var cursor = ""

        while (true) {
            val response = apolloClientStore.query(
                configDAO.stakingUrl(chainId),
                GetSbApyInfoQuery(
                    pageCount = 100,
                    cursor = cursor
                )
            ).entities ?: return emptyList()

            response.nodes.filterNotNull().forEach { node ->
                result.add(node.mapSbApyInfoResponse())
            }

            val endCursor = response.pageInfo.endCursor ?: break

            cursor = endCursor
        }

        return result
    }

    private fun GetSbApyInfoQuery.Node.mapSbApyInfoResponse() =
        SbApyInfoResponse(
            id = id,
            strategicBonusApy = strategicBonusApy
        )

}