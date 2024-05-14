package jp.co.soramitsu.xnetworking.lib.datasources.staking.apy

import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ApyFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.ApyFetcherFacade
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class ApyFetcherFacadeTest {

    @Mock
    val chainsConfigFetcher = mock(classOf<ChainsConfigFetcher>())

    @Mock
    val apyFetcherMock = mock(classOf<ApyFetcher>())

    @Test
    fun `TEST apyFacade_fetch EXPECT IllegalArgumentException BECAUSE staking type is null`() = runTest {
        // Test Data Start
        val facade: ApyFetcher = ApyFetcherFacade(
            chainsConfigFetcher = chainsConfigFetcher,
            apyFetcherMap = mapOf(
                ChainsConfig.ExternalApi.Type.Sora to apyFetcherMock
            )
        )
        // Test Data End

        coEvery { chainsConfigFetcher.loadConfigOrGetCached() }.returns(
            listOf(
                ChainsConfig(
                    chainId = "sora",
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
            facade.fetch(
                chainId = "sora",
                selectedCandidates = null
            )
        } catch (e: IllegalArgumentException) {
            coVerify {
                apyFetcherMock.fetch(
                    chainId = any(),
                    selectedCandidates = any()
                )
            }.wasNotInvoked()
        }
    }

    @Test
    fun `TEST apyFacade_fetch EXPECT IllegalStateException BECAUSE fetcher not found`() = runTest {
        // Test Data Start
        val facade: ApyFetcher = ApyFetcherFacade(
            chainsConfigFetcher = chainsConfigFetcher,
            apyFetcherMap = mapOf(
                ChainsConfig.ExternalApi.Type.Sora to apyFetcherMock
            )
        )
        // Test Data End

        coEvery { chainsConfigFetcher.loadConfigOrGetCached() }.returns(
            listOf(
                ChainsConfig(
                    chainId = "sora",
                    assets = emptyList(),
                    externalApi = ChainsConfig.ExternalApi(
                        history = null,
                        staking = ChainsConfig.ExternalApi.PlainSection(
                            type = ChainsConfig.ExternalApi.Type.SubQuery,
                            url = "subquery.url"
                        ),
                        explorers = null
                    )
                )
            ).associateBy { it.chainId }
        )

        try {
            facade.fetch(
                chainId = "sora",
                selectedCandidates = null
            )
        } catch (e: IllegalStateException) {
            coVerify {
                apyFetcherMock.fetch(
                    chainId = any(),
                    selectedCandidates = any()
                )
            }.wasNotInvoked()
        }
    }

    @Test
    fun `TEST apyFacade_fetch EXPECT success`() = runTest {
        // Test Data Start
        val facade: ApyFetcher = ApyFetcherFacade(
            chainsConfigFetcher = chainsConfigFetcher,
            apyFetcherMap = mapOf(
                ChainsConfig.ExternalApi.Type.Sora to apyFetcherMock
            )
        )

        val apyMapToReturn = mapOf(
            "something" to null
        )
        // Test Data End

        coEvery { chainsConfigFetcher.loadConfigOrGetCached() }.returns(
            listOf(
                ChainsConfig(
                    chainId = "sora",
                    assets = emptyList(),
                    externalApi = ChainsConfig.ExternalApi(
                        history = null,
                        staking = ChainsConfig.ExternalApi.PlainSection(
                            type = ChainsConfig.ExternalApi.Type.Sora,
                            url = "sora.url"
                        ),
                        explorers = null
                    )
                )
            ).associateBy { it.chainId }
        )
        
        coEvery {
            apyFetcherMock.fetch(
                chainId = "sora",
                selectedCandidates = null
            )
        }.returns(apyMapToReturn)

        val result = facade.fetch(
            chainId = "sora",
            selectedCandidates = null
        )
        
        coVerify {
            apyFetcherMock.fetch(
                chainId = "sora",
                selectedCandidates = null
            )
        }.wasInvoked(1)

        assertTrue {
            apyMapToReturn.keys.containsAll(result.keys)
        }

        assertTrue {
            apyMapToReturn.values.containsAll(result.values)
        }
    }

}