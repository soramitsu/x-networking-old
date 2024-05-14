package jp.co.soramitsu.xnetworking.lib.datasources.staking.validators.adapters.sora

import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.instanceOf
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ValidatorsFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.validators.adapters.sora.SoraValidatorsFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.validators.adapters.sora.SoraValidatorsResponse
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLResponseDataWrapper
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class SoraValidatorsFetcherTest {

    @Mock
    val chainsConfigFetcher = mock(classOf<ChainsConfigFetcher>())

    @Mock
    val restClient = mock(classOf<RestClient>())

    private val fetcher: ValidatorsFetcher = SoraValidatorsFetcher(
        chainsConfigFetcher = chainsConfigFetcher,
        restClient = restClient
    )

    @Test
    fun `TEST soraValidatorsFetcher_fetch EXPECT IllegalArgumentException BECAUSE staking type is null`() =
        runTest {
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
                fetcher.fetch(
                    chainId = "sora",
                    stashAccountAddress = "",
                    historicalRange = emptyList()
                )
            } catch (e: IllegalArgumentException) {
                coVerify {
                    restClient.post(
                        request = any(),
                        kSerializer = instanceOf(
                            GraphQLResponseDataWrapper.serializer(
                                SoraValidatorsResponse.serializer()
                            )
                        )
                    )
                }.wasNotInvoked()
            }
        }

    @Test
    fun `TEST soraValidatorsFetcher_fetch EXPECT IllegalArgumentException BECAUSE historical range is empty`() =
        runTest {
            try {
                fetcher.fetch(
                    chainId = "sora",
                    stashAccountAddress = "",
                    historicalRange = emptyList()
                )
            } catch (e: IllegalArgumentException) {
                coVerify {
                    restClient.post(
                        request = any(),
                        kSerializer = instanceOf(
                            GraphQLResponseDataWrapper.serializer(
                                SoraValidatorsResponse.serializer()
                            )
                        )
                    )
                }.wasNotInvoked()
            }
        }

    @Test
    fun `TEST soraValidatorsFetcher_fetch EXPECT success`() = runTest {
        // Test Data Start
        val responseToReturn = GraphQLResponseDataWrapper(
            data = SoraValidatorsResponse(
                stakingEraNominators = listOf(
                    SoraValidatorsResponse.Nominator(
                        nominations = listOf(
                            SoraValidatorsResponse.Nominator.Nomination(
                                validator = SoraValidatorsResponse.Nominator.Nomination.Validator(
                                    id = "123"
                                )
                            )
                        )
                    )
                )
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
            restClient.post(
                request = any(),
                kSerializer = instanceOf(
                    GraphQLResponseDataWrapper.serializer(
                        SoraValidatorsResponse.serializer()
                    )
                )
            )
        }.returns(responseToReturn)

        val result = fetcher.fetch(
            chainId = "sora",
            stashAccountAddress = "",
            historicalRange = listOf(
                "from", "to"
            )
        )

        assertContentEquals(
            responseToReturn.data.stakingEraNominators.flatMap { it.nominations }.mapNotNull { it.validator.id }.distinct(),
            result
        )
    }
}