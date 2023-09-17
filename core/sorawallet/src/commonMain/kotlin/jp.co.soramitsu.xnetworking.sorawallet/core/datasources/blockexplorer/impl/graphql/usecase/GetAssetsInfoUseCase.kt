package jp.co.soramitsu.xnetworking.sorawallet.core.datasources.blockexplorer.impl.graphql.usecase

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import jp.co.soramitsu.xnetworking.AssetsQuery
import jp.co.soramitsu.xnetworking.basic.common.Utils.toDoubleNan
import jp.co.soramitsu.xnetworking.sorawallet.core.datasources.blockexplorer.api.models.AssetsInfoResponse

internal class GetAssetsInfoUseCase {

    suspend operator fun invoke(
        apolloClient: ApolloClient,
        tokenIds: List<String>,
        timeStamp: String
    ): List<AssetsInfoResponse> {
        val result = mutableListOf<AssetsInfoResponse>()

        var cursor = ""

        while (true) {
            val response = apolloClient.query(
                AssetsQuery(
                    pageCount = 100,
                    cursor = cursor,
                    tokenIds = Optional.present(tokenIds),
                    timestamp = timeStamp
                )
            ).execute().data?.entities?.firstOrNull() ?: return emptyList()

            response.nodes.forEach { node ->
                result.add(node.mapToAssetsInfoResponse())
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

    private fun AssetsQuery.Node.mapToAssetsInfoResponse() =
        AssetsInfoResponse(
            id = id,
            liquidity = liquidity,
            previousPrice = hourSnapshots?.lastOrNull()?.nodes?.lastOrNull()?.priceUSD?.toDoubleNan()
        )

}