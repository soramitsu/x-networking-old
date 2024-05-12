package jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.usecase

import jp.co.soramitsu.xnetworking.sorawallet.GetSbApyInfoQuery
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.SbApyInfoResponse
import jp.co.soramitsu.xnetworking.lib.engines.apollo.api.ApolloClientStore

internal class GetSbApyInfoUseCase(
    private val apolloClientStore: ApolloClientStore
) {

    suspend operator fun invoke(
        serverUrl: String
    ): List<SbApyInfoResponse> {
        val result = mutableListOf<SbApyInfoResponse>()

        var cursor = ""

        while (true) {
            val response = apolloClientStore.query(
                serverUrl,
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