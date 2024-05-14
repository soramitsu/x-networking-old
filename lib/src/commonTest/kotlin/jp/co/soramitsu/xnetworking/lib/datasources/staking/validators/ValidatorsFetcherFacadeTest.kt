package jp.co.soramitsu.xnetworking.lib.datasources.staking.validators

import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ValidatorsFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.validators.ValidatorsFetcherFacade
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class ValidatorsFetcherFacadeTest {

    @Mock
    val chainsConfigFetcher = mock(classOf<ChainsConfigFetcher>())

    @Mock
    val validatorsFetcherMock = mock(classOf<ValidatorsFetcher>())

    @Test
    fun `TEST validatorsFacade_fetch EXPECT IllegalArgumentException BECAUSE staking type is null`() = runTest {
        // Test Data Start
        val facade: ValidatorsFetcher = ValidatorsFetcherFacade(
            chainsConfigFetcher = chainsConfigFetcher,
            validatorsFetcherMap = mapOf(
                ChainsConfig.ExternalApi.Type.Sora to validatorsFetcherMock
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
                stashAccountAddress = "",
                historicalRange = emptyList()
            )
        } catch (e: IllegalArgumentException) {
            coVerify {
                validatorsFetcherMock.fetch(
                    chainId = any(),
                    stashAccountAddress = any(),
                    historicalRange = any()
                )
            }.wasNotInvoked()
        }
    }

    @Test
    fun `TEST validatorsFacade_fetch EXPECT IllegalStateException BECAUSE fetcher not found`() = runTest {
        // Test Data Start
        val facade: ValidatorsFetcher = ValidatorsFetcherFacade(
            chainsConfigFetcher = chainsConfigFetcher,
            validatorsFetcherMap = mapOf(
                ChainsConfig.ExternalApi.Type.Sora to validatorsFetcherMock
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
                stashAccountAddress = "",
                historicalRange = emptyList()
            )
        } catch (e: IllegalStateException) {
            coVerify {
                validatorsFetcherMock.fetch(
                    chainId = any(),
                    stashAccountAddress = any(),
                    historicalRange = any()
                )
            }.wasNotInvoked()
        }
    }

    @Test
    fun `TEST validatorsFacade_fetch EXPECT success`() = runTest {
        // Test Data Start
        val facade: ValidatorsFetcher = ValidatorsFetcherFacade(
            chainsConfigFetcher = chainsConfigFetcher,
            validatorsFetcherMap = mapOf(
                ChainsConfig.ExternalApi.Type.Sora to validatorsFetcherMock
            )
        )

        val validatorsListToReturn = listOf(
            "something"
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
            validatorsFetcherMock.fetch(
                chainId = "sora",
                stashAccountAddress = "",
                historicalRange = emptyList()
            )
        }.returns(validatorsListToReturn)

        val result = facade.fetch(
            chainId = "sora",
            stashAccountAddress = "",
            historicalRange = emptyList()
        )

        coVerify {
            validatorsFetcherMock.fetch(
                chainId = "sora",
                stashAccountAddress = "",
                historicalRange = emptyList()
            )
        }.wasInvoked(1)

        assertContentEquals(
            validatorsListToReturn,
            result
        )
    }
}