package jp.co.soramitsu.xnetworking.lib.datasources.staking.validators.adapters.subquery

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
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.validators.adapters.subquery.SubQueryValidatorsFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.validators.adapters.subquery.SubQueryValidatorsResponse
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLResponseDataWrapper
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class SubQueryValidatorsFetcherTest {

    @Mock
    val chainsConfigFetcher = mock(classOf<ChainsConfigFetcher>())

    @Mock
    val restClient = mock(classOf<RestClient>())

    private val fetcher: ValidatorsFetcher = SubQueryValidatorsFetcher(
        chainsConfigFetcher = chainsConfigFetcher,
        restClient = restClient
    )

    @Test
    fun `TEST subqueryValidatorsFetcher_fetch EXPECT IllegalArgumentException BECAUSE staking type is null`() =
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
                    stashAccountAddress = "",
                    historicalRange = emptyList()
                )
            } catch (e: IllegalArgumentException) {
                coVerify {
                    restClient.post(
                        request = any(),
                        kSerializer = instanceOf(
                            GraphQLResponseDataWrapper.serializer(
                                SubQueryValidatorsResponse.serializer()
                            )
                        )
                    )
                }.wasNotInvoked()
            }
        }

    @Test
    fun `TEST subqueryValidatorsFetcher_fetch EXPECT IllegalArgumentException BECAUSE historical range is empty`() =
        runTest {
            try {
                fetcher.fetch(
                    chainId = "quartz",
                    stashAccountAddress = "",
                    historicalRange = emptyList()
                )
            } catch (e: IllegalArgumentException) {
                coVerify {
                    restClient.post(
                        request = any(),
                        kSerializer = instanceOf(
                            GraphQLResponseDataWrapper.serializer(
                                SubQueryValidatorsResponse.serializer()
                            )
                        )
                    )
                }.wasNotInvoked()
            }
        }

    @Test
    fun `TEST subqueryValidatorsFetcher_fetch EXPECT success`() = runTest {
        // Test Data Start
        val responseToReturn = GraphQLResponseDataWrapper(
            data = SubQueryValidatorsResponse(
                query = SubQueryValidatorsResponse.EraValidatorInfo(
                    eraValidatorInfos = SubQueryValidatorsResponse.EraValidatorInfo.Nodes(
                        nodes = listOf(
                            SubQueryValidatorsResponse.EraValidatorInfo.Nodes.Node(
                                id = "123",
                                address = "address",
                                era = "123",
                                total = "123",
                                own = "own"
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
                    chainId = "quartz",
                    assets = emptyList(),
                    externalApi = ChainsConfig.ExternalApi(
                        history = null,
                        staking = ChainsConfig.ExternalApi.PlainSection(
                            type = ChainsConfig.ExternalApi.Type.SubQuery,
                            url = "quartz.url"
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
                        SubQueryValidatorsResponse.serializer()
                    )
                )
            )
        }.returns(responseToReturn)

        val result = fetcher.fetch(
            chainId = "quartz",
            stashAccountAddress = "",
            historicalRange = listOf(
                "from", "to"
            )
        )

        assertContentEquals(
            responseToReturn.data.query?.eraValidatorInfos?.nodes?.map { it.address }.orEmpty(),
            result
        )
    }
}