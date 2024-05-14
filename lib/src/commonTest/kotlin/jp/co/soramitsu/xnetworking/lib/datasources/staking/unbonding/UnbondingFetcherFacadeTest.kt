package jp.co.soramitsu.xnetworking.lib.datasources.staking.unbonding

import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.UnbondingFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.models.Unbonding
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.unbonding.UnbondingFetcherFacade
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class UnbondingFetcherFacadeTest {

    @Mock
    val chainsConfigFetcher = mock(classOf<ChainsConfigFetcher>())

    @Mock
    val unbondingFetcherMock = mock(classOf<UnbondingFetcher>())

    @Test
    fun `TEST unbondingFacade_fetch EXPECT IllegalArgumentException BECAUSE staking type is null`() = runTest {
        // Test Data Start
        val facade: UnbondingFetcher = UnbondingFetcherFacade(
            chainsConfigFetcher = chainsConfigFetcher,
            unbondingFetcherMap = mapOf(
                ChainsConfig.ExternalApi.Type.Sora to unbondingFetcherMock
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
                delegatorAddress = "",
                collatorAddress = ""
            )
        } catch (e: IllegalArgumentException) {
            coVerify {
                unbondingFetcherMock.fetch(
                    chainId = any(),
                    delegatorAddress = any(),
                    collatorAddress = any()
                )
            }.wasNotInvoked()
        }
    }

    @Test
    fun `TEST unbondingFacade_fetch EXPECT IllegalStateException BECAUSE fetcher not found`() = runTest {
        // Test Data Start
        val facade: UnbondingFetcher = UnbondingFetcherFacade(
            chainsConfigFetcher = chainsConfigFetcher,
            unbondingFetcherMap = mapOf(
                ChainsConfig.ExternalApi.Type.Sora to unbondingFetcherMock
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
                delegatorAddress = "",
                collatorAddress = ""
            )
        } catch (e: IllegalStateException) {
            coVerify {
                unbondingFetcherMock.fetch(
                    chainId = any(),
                    delegatorAddress = any(),
                    collatorAddress = any()
                )
            }.wasNotInvoked()
        }
    }

    @Test
    fun `TEST unbondingFacade_fetch EXPECT success`() = runTest {
        // Test Data Start
        val facade: UnbondingFetcher = UnbondingFetcherFacade(
            chainsConfigFetcher = chainsConfigFetcher,
            unbondingFetcherMap = mapOf(
                ChainsConfig.ExternalApi.Type.Sora to unbondingFetcherMock
            )
        )

        val unbondingListToReturn = listOf(
            Unbonding(
                amount = "123",
                timestamp = "123",
                type = Unbonding.DelegationAction.STAKE
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
                            type = ChainsConfig.ExternalApi.Type.Sora,
                            url = "sora.url"
                        ),
                        explorers = null
                    )
                )
            ).associateBy { it.chainId }
        )

        coEvery {
            unbondingFetcherMock.fetch(
                chainId = "sora",
                delegatorAddress = "",
                collatorAddress = ""
            )
        }.returns(unbondingListToReturn)

        val result = facade.fetch(
            chainId = "sora",
            delegatorAddress = "",
            collatorAddress = ""
        )

        coVerify {
            unbondingFetcherMock.fetch(
                chainId = "sora",
                delegatorAddress = "",
                collatorAddress = ""
            )
        }.wasInvoked(1)

        assertContentEquals(
            unbondingListToReturn,
            result
        )
    }
}