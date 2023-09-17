package jp.co.soramitsu.xnetworking.sorawallet.core.datasources.blockexplorer.impl.graphql.usecase

import com.apollographql.apollo3.ApolloClient
import jp.co.soramitsu.xnetworking.GetSbApyInfoQuery
import jp.co.soramitsu.xnetworking.sorawallet.core.datasources.blockexplorer.api.models.SbApyInfoResponse

internal class GetSbApyInfoUseCase {

    suspend operator fun invoke(
        apolloClient: ApolloClient
    ): List<SbApyInfoResponse> {
        val result = mutableListOf<SbApyInfoResponse>()

        var cursor = ""

        while (true) {
            val response = apolloClient.query(
                GetSbApyInfoQuery(
                    pageCount = 100,
                    cursor = cursor
                )
            ).execute().data?.entities?.firstOrNull() ?: return emptyList()

            response.nodes.forEach { node ->
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