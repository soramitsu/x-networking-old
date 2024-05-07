package jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.impl.usecase

import com.apollographql.apollo3.ApolloClient
import jp.co.soramitsu.xnetworking.sorawallet.GetFiatDataQuery
import jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.api.models.FiatDataResponse
import jp.co.soramitsu.xnetworking.core.engines.apollo.api.ApolloClientStore

internal class GetFiatDataUseCase(
    private val apolloClientStore: ApolloClientStore
) {

    suspend operator fun invoke(
        serverUrl: String
    ): List<FiatDataResponse> {
        val result = mutableListOf<FiatDataResponse>()

        var cursor = ""

        while (true) {
            val response = apolloClientStore.query(
                serverUrl,
                GetFiatDataQuery(
                    pageCount = 100,
                    cursor = cursor
                )
            ).entities ?: return emptyList()

            response.nodes.filterNotNull().forEach { node ->
                result.add(node.mapToFiatDataResponse())
            }

            val (hasNextPage, endCursor) = response.pageInfo.run {
                hasNextPage to endCursor
            }

            if (!hasNextPage || endCursor == null)
                break

            cursor = endCursor
        }

        return result
    }

    private fun GetFiatDataQuery.Node.mapToFiatDataResponse() =
        FiatDataResponse(
            id = id,
            priceUSD = priceUSD
        )

}