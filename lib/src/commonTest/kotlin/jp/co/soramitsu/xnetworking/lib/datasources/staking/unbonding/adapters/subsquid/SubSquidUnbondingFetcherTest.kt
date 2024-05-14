package jp.co.soramitsu.xnetworking.lib.datasources.staking.unbonding.adapters.subsquid

import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.instanceOf
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.UnbondingFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.models.Unbonding
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.unbonding.adapters.subsquid.SubSquidUnbondingFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.unbonding.adapters.subsquid.SubSquidUnbondingResponse
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLResponseDataWrapper
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class SubSquidUnbondingFetcherTest {

    @Mock
    val chainsConfigFetcher = mock(classOf<ChainsConfigFetcher>())

    @Mock
    val restClient = mock(classOf<RestClient>())

    private val fetcher: UnbondingFetcher = SubSquidUnbondingFetcher(
        chainsConfigFetcher = chainsConfigFetcher,
        restClient = restClient
    )

    @Test
    fun `TEST subqueryUnbondingFetcher_fetch EXPECT IllegalArgumentException BECAUSE staking type is null`() =
        runTest {
            coEvery { chainsConfigFetcher.loadConfigOrGetCached() }.returns(
                listOf(
                    ChainsConfig(
                        chainId = "polkadot",
                        assets = emptyList(),
                        externalApi = ChainsConfig.ExternalApi(
                            history = null,
                            staking = null,
                            explorers = null
                        )
                    )
                ).associateBy { it.chainId }
            )

            try {
                fetcher.fetch(
                    chainId = "polkadot",
                    delegatorAddress = "0xSomething",
                    collatorAddress = "0xSomething"
                )
            } catch (e: IllegalArgumentException) {
                coVerify {
                    restClient.post(
                        request = any(),
                        kSerializer = instanceOf(
                            GraphQLResponseDataWrapper.serializer(
                                SubSquidUnbondingResponse.serializer()
                            )
                        )
                    )
                }.wasNotInvoked()
            }
        }

    @Test
    fun `TEST subqueryUnbondingFetcher_fetch EXPECT IllegalArgumentException BECAUSE delegatorAddress has no hex prefix`() =
        runTest {
            try {
                fetcher.fetch(
                    chainId = "polkadot",
                    delegatorAddress = "",
                    collatorAddress = "0xSomething"
                )
            } catch (e: IllegalArgumentException) {
                coVerify {
                    restClient.post(
                        request = any(),
                        kSerializer = instanceOf(
                            GraphQLResponseDataWrapper.serializer(
                                SubSquidUnbondingResponse.serializer()
                            )
                        )
                    )
                }.wasNotInvoked()
            }
        }

    @Test
    fun `TEST subqueryUnbondingFetcher_fetch EXPECT IllegalArgumentException BECAUSE collatorAddress has no hex prefix`() =
        runTest {
            try {
                fetcher.fetch(
                    chainId = "polkadot",
                    delegatorAddress = "0xSomething",
                    collatorAddress = ""
                )
            } catch (e: IllegalArgumentException) {
                coVerify {
                    restClient.post(
                        request = any(),
                        kSerializer = instanceOf(
                            GraphQLResponseDataWrapper.serializer(
                                SubSquidUnbondingResponse.serializer()
                            )
                        )
                    )
                }.wasNotInvoked()
            }
        }

    @Test
    fun `TEST subqueryUnbondingFetcher_fetch EXPECT success`() = runTest {
        // Test Data Start
        val responseToReturn = GraphQLResponseDataWrapper(
            data = SubSquidUnbondingResponse(
                rewards = listOf(
                    SubSquidUnbondingResponse.Reward(
                        id = "id_123",
                        amount = "123",
                        blockNumber = 1,
                        round = 12,
                        timestamp = "timestamp_123",
                        extrinsicHash = "extrinsicHash_123"
                    )
                )
            )
        )
        // Test Data End

        coEvery { chainsConfigFetcher.loadConfigOrGetCached() }.returns(
            listOf(
                ChainsConfig(
                    chainId = "polkadot",
                    assets = emptyList(),
                    externalApi = ChainsConfig.ExternalApi(
                        history = null,
                        staking = ChainsConfig.ExternalApi.PlainSection(
                            type = ChainsConfig.ExternalApi.Type.SubSquid,
                            url = "polkadot.url"
                        ),
                        explorers = null
                    )
                )
            ).associateBy { it.chainId }
        )

        coEvery {
            restClient.post(
                request = any(),
                kSerializer = instanceOf(
                    GraphQLResponseDataWrapper.serializer(
                        SubSquidUnbondingResponse.serializer()
                    )
                )
            )
        }.returns(responseToReturn)

        val result = fetcher.fetch(
            chainId = "polkadot",
            delegatorAddress = "0xSomething",
            collatorAddress = "0xSomething"
        )

        assertTrue {
            result.foldRightIndexed(true) { index, unbonding, acc ->
                val expectedUnbonding =
                    responseToReturn.data.rewards[index].run {
                        Unbonding(
                            amount = amount ?: "0",
                            timestamp = timestamp ?: "0",
                            type = Unbonding.DelegationAction.REWARD
                        )
                    }

                val areElementsTheSame =
                    unbonding.amount == expectedUnbonding.amount &&
                            unbonding.timestamp == expectedUnbonding.timestamp &&
                            unbonding.type == expectedUnbonding.type

                acc &&areElementsTheSame
            }
        }
    }
}