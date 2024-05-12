package jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.usecase

import com.apollographql.apollo3.api.Optional
import jp.co.soramitsu.xnetworking.basic.common.Utils.toDoubleNan
import jp.co.soramitsu.xnetworking.lib.engines.apollo.impl.utils.getPrimitiveContentOrEmpty
import jp.co.soramitsu.xnetworking.lib.engines.apollo.impl.utils.asJsonObjectNullable
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.AssetsInfoResponse
import jp.co.soramitsu.xnetworking.lib.engines.apollo.api.ApolloClientStore
import jp.co.soramitsu.xnetworking.sorawallet.GetAssetsInfoQuery

internal class GetAssetsInfoUseCase(
    private val apolloClientStore: ApolloClientStore
) {

    suspend operator fun invoke(
        serverUrl: String,
        tokenIds: List<String>,
        timeStamp: Int
    ): List<AssetsInfoResponse> {
        val result = mutableListOf<AssetsInfoResponse>()

        var cursor = ""

        while (true) {
            val response = apolloClientStore.query(
                serverUrl,
                GetAssetsInfoQuery(
                    pageCount = 100,
                    cursor = cursor,
                    tokenIds = Optional.present(tokenIds),
                    timestamp = timeStamp
                )
            ).entities ?: return emptyList()

            response.nodes.filterNotNull().forEach { node ->
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

    private fun GetAssetsInfoQuery.Node.mapToAssetsInfoResponse() =
        AssetsInfoResponse(
            id = id,
            liquidity = liquidity,
            previousPrice = hourSnapshots.nodes.lastOrNull()?.priceUSD
                ?.asJsonObjectNullable?.getPrimitiveContentOrEmpty("open")?.toDoubleNan()
        )

}