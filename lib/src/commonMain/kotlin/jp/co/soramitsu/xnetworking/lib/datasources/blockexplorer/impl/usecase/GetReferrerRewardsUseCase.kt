package jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.usecase

import jp.co.soramitsu.xnetworking.sorawallet.GetReferrerRewardsQuery
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.ReferrerRewardResponse
import jp.co.soramitsu.xnetworking.lib.engines.apollo.api.ApolloClientStore

internal class GetReferrerRewardsUseCase(
    private val apolloClientStore: ApolloClientStore
) {

    suspend operator fun invoke(
        serverUrl: String,
        address: String
    ): List<ReferrerRewardResponse> {
        val result = mutableListOf<ReferrerRewardResponse>()

        var cursor = ""

        while (true) {
            val response = apolloClientStore.query(
                serverUrl,
                GetReferrerRewardsQuery(
                    pageCount = 100,
                    cursor = cursor,
                    address = address
                )
            ).entities ?: return emptyList()

            response.nodes.filterNotNull().forEach { node ->
                result.add(node.mapReferrerRewardsResponse())
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

    private fun GetReferrerRewardsQuery.Node.mapReferrerRewardsResponse() =
        ReferrerRewardResponse(
            referral = referral,
            amount = amount.toString()
        )

}