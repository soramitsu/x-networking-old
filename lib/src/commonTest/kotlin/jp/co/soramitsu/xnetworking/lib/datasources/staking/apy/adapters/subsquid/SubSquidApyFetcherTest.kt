package jp.co.soramitsu.xnetworking.lib.datasources.staking.apy.adapters.subsquid

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
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquid.SubSquidApyFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquid.SubSquidApyRequest
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquid.SubSquidApyResponse
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLResponseDataWrapper
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class SubSquidApyFetcherTest {

    @Mock
    val chainsConfigFetcher = mock(classOf<ChainsConfigFetcher>())

    @Mock
    val restClient = mock(classOf<RestClient>())

    private val fetcher: ApyFetcher = SubSquidApyFetcher(
        chainsConfigFetcher = chainsConfigFetcher,
        restClient = restClient
    )

    @Test
    fun `TEST subquerySquidFetcher_fetch EXPECT IllegalArgumentException BECAUSE staking type is null`() =
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
                    selectedCandidates = listOf("0xSomething")
                )
            } catch (e: IllegalArgumentException) {
                coVerify {
                    restClient.post(
                        request = any(),
                        kSerializer = instanceOf(
                            GraphQLResponseDataWrapper.serializer(
                                SubSquidApyResponse.serializer()
                            )
                        )
                    )
                }.wasNotInvoked()
            }
        }

    @Test
    fun `TEST subquerySquidFetcher_fetch EXPECT IllegalArgumentException BECAUSE selectedCandidates lack hex prefix`() =
        runTest {
            try {
                fetcher.fetch(
                    chainId = "polkadot",
                    selectedCandidates = listOf("")
                )
            } catch (e: IllegalArgumentException) {
                coVerify {
                    restClient.post(
                        request = any(),
                        kSerializer = instanceOf(
                            GraphQLResponseDataWrapper.serializer(
                                SubSquidApyResponse.serializer()
                            )
                        )
                    )
                }.wasNotInvoked()
            }
        }

    @Test
    fun `TEST subquerySquidFetcher_fetch EXPECT success`() = runTest {
        // Test Data Start
        val polkadotUrl = "polkadot.url"

        val selectedCandidates = listOf("0xSomethinig")

        val apyRequestToMock = SubSquidApyRequest(
            url = polkadotUrl
        )

        val apyResponseToReturn = GraphQLResponseDataWrapper(
            data = SubSquidApyResponse(
                stakers = listOf(
                    SubSquidApyResponse.CollatorApyElement(
                        stashId = "stashId_123",
                        apr24h = "apr_123"
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
                            url = polkadotUrl
                        ),
                        explorers = null
                    )
                )
            ).associateBy { it.chainId }
        )

        coEvery {
            restClient.post(
                request = eq(apyRequestToMock),
                kSerializer = instanceOf(
                    GraphQLResponseDataWrapper.serializer(
                        SubSquidApyResponse.serializer()
                    )
                )
            )
        }.returns(apyResponseToReturn)

        val result = fetcher.fetch(
            chainId = "polkadot",
            selectedCandidates = selectedCandidates
        )

        assertTrue {
            val keysComparison = result.keys.containsAll(
                apyResponseToReturn.data.stakers.mapNotNull {
                    it.stashId
                }
            )

            val valuesComparison = result.values.containsAll(
                apyResponseToReturn.data.stakers.mapNotNull {
                    if (it.stashId == null)
                        return@mapNotNull null

                    it.apr24h
                }
            )

            keysComparison && valuesComparison
        }
    }
}