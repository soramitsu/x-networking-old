package jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist

import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.instanceOf
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.api.WhitelistRepository
import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.impl.WhitelistRepositoryImpl
import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.impl.models.InternalWhitelistedToken
import jp.co.soramitsu.xnetworking.lib.engines.apollo.impl.utils.asJsonObjectNullable
import jp.co.soramitsu.xnetworking.lib.engines.apollo.impl.utils.getPrimitiveContentOrEmpty
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.AbstractRestServerRequest
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.test.Test
import kotlin.test.assertContentEquals

class WhitelistRepositoryImplTest {

    @Mock
    val restClient = mock(classOf<RestClient>())

    @Mock
    val configRequest = mock(classOf<AbstractRestServerRequest>())

    private val whitelistRepository: WhitelistRepository =
        WhitelistRepositoryImpl(
            restClient = restClient,
            configRequest = configRequest
        )

    @Test
    fun `TEST getWhitelistedTokens EXPECT success`() = runTest {
        // Test Data Start
        val configArrayToReturn = JsonArray(
            content = listOf(
                JsonObject(
                    content = mapOf(
                        "address" to JsonPrimitive("address"),
                        "icon" to JsonPrimitive("icon")
                    )
                )
            )
        )
        // Test Data End

        coEvery {
            restClient.get(
                request = any(),
                kSerializer = instanceOf(JsonArray.serializer())
            )
        }.returns(configArrayToReturn)

        // Double running should be checked accordingly in verify block
        whitelistRepository.getWhitelistedTokens()
        val result = whitelistRepository.getWhitelistedTokens()

        // Verify that implementation is caching, and network request was performed once
        coVerify {
            restClient.get(
                request = any(),
                kSerializer = instanceOf(JsonArray.serializer())
            )
        }.wasInvoked(1)

        assertContentEquals(
            configArrayToReturn.map {
                InternalWhitelistedToken(
                    tokenAddress = it.asJsonObjectNullable.getPrimitiveContentOrEmpty("address"),
                    rawIconLink = it.asJsonObjectNullable.getPrimitiveContentOrEmpty("icon"),
                )
            },
            result
        )
    }

}