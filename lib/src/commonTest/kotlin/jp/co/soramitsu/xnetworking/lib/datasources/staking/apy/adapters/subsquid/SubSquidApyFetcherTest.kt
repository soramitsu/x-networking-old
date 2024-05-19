package jp.co.soramitsu.xnetworking.lib.datasources.staking.apy.adapters.subsquid

import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.eq
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ConfigDAO
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ExternalApiDAOException
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ApyFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquid.SubSquidApyFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquid.SubSquidApyRequest
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquid.SubSquidApyResponse
import jp.co.soramitsu.xnetworking.lib.engines.utils.GraphQLResponseDataWrapper
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class SubSquidApyFetcherTest {

    private companion object {
        const val chainId = "polkadot"
        const val requestUrl = "polkadot.url"
    }

    @Mock
    private val configDAO = mock(classOf<ConfigDAO>())

    @Mock
    private val restClient = mock(classOf<RestClient>())

    private val fetcher: ApyFetcher = SubSquidApyFetcher(
        configDAO = configDAO,
        restClient = restClient
    )

    @Test
    fun `TEST subSquidApyFetcher_fetch EXPECT ExternalApiDAOException_NullUrl BECAUSE staking url is null`() =
        runTest {
            // Test Data Start
            val selectedCandidates = listOf("0xSomething")

            val apyRequestToMock =
                SubSquidApyRequest(
                    url = requestUrl
                )
            // Test Data End

            // Mocks Preparation Start
            coEvery {
                configDAO.stakingUrl(
                    chainId = chainId
                )
            }.throws(ExternalApiDAOException.NullUrl(chainId))
            // Mocks Preparation End

            assertFailsWith<ExternalApiDAOException.NullUrl> {
                fetcher.fetch(
                    chainId = chainId,
                    selectedCandidates = selectedCandidates
                )
            }

            // Verification & Assertion
            coVerify {
                restClient.post(
                    request = eq(apyRequestToMock),
                )
            }.wasNotInvoked()
        }

    @Test
    fun `TEST subSquidApyFetcher_fetch EXPECT IllegalArgumentException BECAUSE selectedCandidates lack hex prefix`() =
        runTest {
            // Test Data Start
            val selectedCandidates = listOf("")

            val apyRequestToMock =
                SubSquidApyRequest(
                    url = requestUrl
                )
            // Test Data End

            // Mocks Preparation Start
            coEvery {
                configDAO.stakingUrl(
                    chainId = chainId
                )
            }.returns(requestUrl)
            // Mocks Preparation End

            assertFailsWith<IllegalArgumentException> {
                fetcher.fetch(
                    chainId = chainId,
                    selectedCandidates = selectedCandidates
                )
            }

            // Verification & Assertion
            coVerify {
                restClient.post(
                    request = eq(apyRequestToMock),
                )
            }.wasNotInvoked()
        }

    @Test
    fun `TEST subSquidApyFetcher_fetch EXPECT success`() = runTest {
        // Test Data Start
        val selectedCandidates = listOf("0xSomething")

        val apyRequestToMock =
            SubSquidApyRequest(
                url = requestUrl
            )

        val apyResponseToReturn =
            GraphQLResponseDataWrapper(
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

        // Mocks Preparation Start
        coEvery {
            configDAO.stakingUrl(
                chainId = chainId
            )
        }.returns(requestUrl)

        coEvery {
            restClient.post(
                request = eq(apyRequestToMock),
            )
        }.returns(apyResponseToReturn)
        // Mocks Preparation End

        val result = fetcher.fetch(
            chainId = chainId,
            selectedCandidates = selectedCandidates
        )

        // Verification & Assertion
        coVerify {
            restClient.post(
                request = eq(apyRequestToMock),
            )
        }.wasInvoked(1)

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