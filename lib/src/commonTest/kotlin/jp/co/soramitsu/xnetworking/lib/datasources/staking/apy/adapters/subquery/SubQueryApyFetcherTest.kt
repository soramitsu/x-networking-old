package jp.co.soramitsu.xnetworking.lib.datasources.staking.apy.adapters.subquery

import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.eq
import io.mockative.instanceOf
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ApyFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquery.SubQueryApyFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquery.SubQueryApyRequest
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquery.SubQueryApyResponse
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquery.SubQueryLastRoundRequest
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquery.SubQueryLastRoundResponse
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLResponseDataWrapper
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class SubQueryApyFetcherTest {

    @Mock
    val chainsConfigFetcher = mock(classOf<ChainsConfigFetcher>())

    @Mock
    val restClient = mock(classOf<RestClient>())

    private val fetcher: ApyFetcher = SubQueryApyFetcher(
        chainsConfigFetcher = chainsConfigFetcher,
        restClient = restClient
    )

    @Test
    fun `TEST subqueryUnbondingFetcher_fetch EXPECT IllegalArgumentException BECAUSE staking type is null`() =
        runTest {
            coEvery { chainsConfigFetcher.loadConfigOrGetCached() }.returns(
                listOf(
                    ChainsConfig(
                        chainId = "quartz",
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
                    chainId = "quartz",
                    selectedCandidates = listOf("0xSomething")
                )
            } catch (e: IllegalArgumentException) {
                coVerify {
                    restClient.post(
                        request = any(),
                        kSerializer = instanceOf(
                            GraphQLResponseDataWrapper.serializer(
                                SubQueryLastRoundResponse.serializer()
                            )
                        )
                    )
                }.wasNotInvoked()

                coVerify {
                    restClient.post(
                        request = any(),
                        kSerializer = instanceOf(
                            GraphQLResponseDataWrapper.serializer(
                                SubQueryApyResponse.serializer()
                            )
                        )
                    )
                }.wasNotInvoked()
            }
        }

    @Test
    fun `TEST subqueryUnbondingFetcher_fetch EXPECT IllegalArgumentException BECAUSE selectedCandidates lack hex prefix`() =
        runTest {
            try {
                fetcher.fetch(
                    chainId = "quartz",
                    selectedCandidates = listOf("")
                )
            } catch (e: IllegalArgumentException) {
                coVerify {
                    restClient.post(
                        request = any(),
                        kSerializer = instanceOf(
                            GraphQLResponseDataWrapper.serializer(
                                SubQueryLastRoundResponse.serializer()
                            )
                        )
                    )
                }.wasNotInvoked()

                coVerify {
                    restClient.post(
                        request = any(),
                        kSerializer = instanceOf(
                            GraphQLResponseDataWrapper.serializer(
                                SubQueryApyResponse.serializer()
                            )
                        )
                    )
                }.wasNotInvoked()
            }
        }

    @Test
    fun `TEST subqueryUnbondingFetcher_fetch EXPECT success`() = runTest {
        // Test Data Start
        val quartzUrl = "quartz.url"

        val lastRoundId = 123

        val lastRoundRequestToMock = SubQueryLastRoundRequest(
            url = quartzUrl
        )

        val lastRoundResponseToReturn = GraphQLResponseDataWrapper(
            data = SubQueryLastRoundResponse(
                rounds = SubQueryLastRoundResponse.Rounds(
                    nodes = listOf(
                        SubQueryLastRoundResponse.Rounds.RoundIdElement(
                            id = lastRoundId.toString()
                        )
                    )
                )
            )
        )

        val selectedCandidates = listOf("0xSomethinig")

        val apyRequestToMock = SubQueryApyRequest(
            url = quartzUrl,
            collatorIds = selectedCandidates,
            roundId = lastRoundId.dec()
        )

        val apyResponseToReturn = GraphQLResponseDataWrapper(
            data = SubQueryApyResponse(
                collatorRounds = SubQueryApyResponse.CollatorRounds(
                    nodes = listOf(
                        SubQueryApyResponse.CollatorRounds.CollatorApyElement(
                            collatorId = "collatorId_123",
                            apr = "apr_123"
                        )
                    )
                )
            )
        )
        // Test Data End

        coEvery { chainsConfigFetcher.loadConfigOrGetCached() }.returns(
            listOf(
                ChainsConfig(
                    chainId = "quartz",
                    assets = emptyList(),
                    externalApi = ChainsConfig.ExternalApi(
                        history = null,
                        staking = ChainsConfig.ExternalApi.PlainSection(
                            type = ChainsConfig.ExternalApi.Type.SubQuery,
                            url = quartzUrl
                        ),
                        explorers = null
                    )
                )
            ).associateBy { it.chainId }
        )

        coEvery {
            restClient.post(
                request = eq(lastRoundRequestToMock),
                kSerializer = instanceOf(
                    GraphQLResponseDataWrapper.serializer(
                        SubQueryLastRoundResponse.serializer()
                    )
                )
            )
        }.returns(lastRoundResponseToReturn)

        coEvery {
            restClient.post(
                request = eq(apyRequestToMock),
                kSerializer = instanceOf(
                    GraphQLResponseDataWrapper.serializer(
                        SubQueryApyResponse.serializer()
                    )
                )
            )
        }.returns(apyResponseToReturn)

        val result = fetcher.fetch(
            chainId = "quartz",
            selectedCandidates = selectedCandidates
        )

        coVerify {
            restClient.post(
                request = eq(lastRoundRequestToMock),
                kSerializer = instanceOf(
                    GraphQLResponseDataWrapper.serializer(
                        SubQueryLastRoundResponse.serializer()
                    )
                )
            )
        }.wasInvoked(1)

        assertTrue {
            val keysComparison = result.keys.containsAll(
                apyResponseToReturn.data.collatorRounds.nodes.mapNotNull {
                    it.collatorId
                }
            )

            val valuesComparison = result.values.containsAll(
                apyResponseToReturn.data.collatorRounds.nodes.mapNotNull {
                    if (it.collatorId == null)
                        return@mapNotNull null

                    it.apr
                }
            )

            keysComparison && valuesComparison
        }
    }
}