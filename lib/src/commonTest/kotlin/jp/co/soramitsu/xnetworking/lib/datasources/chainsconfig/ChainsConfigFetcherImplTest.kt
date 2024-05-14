package jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig

import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.instanceOf
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.impl.ChainsConfigFetcherImpl
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.AbstractRestServerRequest
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.builtins.ListSerializer
import kotlin.test.Test
import kotlin.test.assertContentEquals

class ChainsConfigFetcherImplTest {

    @Mock
    val restClient = mock(classOf<RestClient>())

    @Mock
    val chainsRequest = mock(classOf<AbstractRestServerRequest>())

    private val chainsConfigFetcher: ChainsConfigFetcher =
        ChainsConfigFetcherImpl(
            restClient = restClient,
            chainsRequest = chainsRequest
        )

    @Test
    fun `TEST loadConfigOrGetCached EXPECT success`() = runTest {
        // Test Data Start
        val chainsConfigListToReturn = listOf(
            ChainsConfig(
                chainId = "sora",
                assets = emptyList(),
                externalApi = ChainsConfig.ExternalApi(
                    staking = null,
                    history = ChainsConfig.ExternalApi.PlainSection(
                        type = ChainsConfig.ExternalApi.Type.Sora,
                        url = "sora.url"
                    ),
                    explorers = null
                )
            )
        )
        // Test Data End

        coEvery {
            restClient.get(
                request = any(),
                kSerializer = instanceOf(ListSerializer(ChainsConfig.serializer()))
            )
        }.returns(chainsConfigListToReturn)

        // Double running should be checked accordingly in verify block
        chainsConfigFetcher.loadConfigOrGetCached()
        val result = chainsConfigFetcher.loadConfigOrGetCached()

        // Verify that implementation is caching, and network request was performed once
        coVerify {
            restClient.get(
                request = any(),
                kSerializer = instanceOf(ListSerializer(ChainsConfig.serializer()))
            )
        }.wasInvoked(1)

        assertContentEquals(
            chainsConfigListToReturn,
            result.values
        )
    }

}