package jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.adapters

import com.apollographql.apollo3.api.Optional
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.adapters.AssetsInfoFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.utils.Utils.toDoubleNan
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.AssetsInfoResponse
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ConfigDAO
import jp.co.soramitsu.xnetworking.lib.engines.apollo.api.ApolloClientStore
import jp.co.soramitsu.xnetworking.lib.engines.utils.fieldOrNull
import jp.co.soramitsu.xnetworking.sorawallet.GetAssetsInfoQuery

class SoraAssetsInfoFetcher(
    private val apolloClientStore: ApolloClientStore,
    private val configDAO: ConfigDAO
): AssetsInfoFetcher() {

    override suspend fun fetch(
        chainId: String,
        tokenIds: List<String>,
        timeStamp: Int
    ): List<AssetsInfoResponse> {
        val result = mutableListOf<AssetsInfoResponse>()

        var cursor = ""

        while (true) {
            val response = apolloClientStore.query(
                configDAO.stakingUrl(chainId),
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
            previousPrice = hourSnapshots.nodes.lastOrNull()?.priceUSD.fieldOrNull("open")?.toDoubleNan()
        )

}