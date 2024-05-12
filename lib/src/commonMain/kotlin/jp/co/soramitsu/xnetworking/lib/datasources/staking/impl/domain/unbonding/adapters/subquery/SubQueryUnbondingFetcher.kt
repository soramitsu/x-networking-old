package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.unbonding.adapters.subquery

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.UnbondingFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.models.Unbonding
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLResponseDataWrapper
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient

class SubQueryUnbondingFetcher(
    private val chainsConfigFetcher: ChainsConfigFetcher,
    private val restClient: RestClient
): UnbondingFetcher {

    override suspend fun fetch(
        chainId: String,
        delegatorAddress: String,
        collatorAddress: String
    ): List<Unbonding> {
        check(delegatorAddress.startsWith("0x")) {
            "DelegatorAddress is not hex value address."
        }
        check(collatorAddress.startsWith("0x")) {
            "CollatorAddress is not hex value address."
        }

        val config = chainsConfigFetcher.loadConfigOrGetCached()[chainId]
        val requestUrl =
            requireNotNull(config?.externalApi?.history?.url) {
                "Url for SubQuery stakingExplorer on chain with id - $chainId - is null."
            }

        val rewards = restClient.post(
            request = SubQueryUnbondingRequest(
                url = requestUrl,
                delegatorAddress = delegatorAddress,
                collatorAddress = collatorAddress
            ),
            kSerializer = GraphQLResponseDataWrapper.serializer(
                SubQueryUnbondingResponse.serializer()
            )
        ).data.delegatorHistoryElements.nodes

        return rewards.map { reward ->
            Unbonding(
                amount = reward.amount ?: "0",
                timestamp = reward.timestamp ?: "0",
                type = Unbonding.DelegationAction.REWARD
            )
        }
    }

}