package jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist

import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.eq
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.api.WhitelistRepository
import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.impl.WhitelistRepositoryImpl
import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.impl.models.InternalWhitelistedToken
import jp.co.soramitsu.xnetworking.lib.engines.utils.JsonGetRequest
import jp.co.soramitsu.xnetworking.lib.engines.utils.fieldOrNull
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.test.Test
import kotlin.test.assertContentEquals

class WhitelistRepositoryImplTest {

    private companion object {
        const val requestUrl = "sora.url"
    }

    @Mock
    val restClient = mock(classOf<RestClient>())

    private val whitelistRepository: WhitelistRepository =
        WhitelistRepositoryImpl(
            restClient = restClient,
            configRequestUrl = requestUrl
        )

    @Test
    fun `TEST getWhitelistedTokens EXPECT success`() = runTest {
        // Test Data Start
        val whitelistRequestToMock =
            JsonGetRequest(
                url = requestUrl,
                responseDeserializer = JsonArray.serializer()
            )

        val whitelistResponseToReturn =
            JsonArray(
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

        // Mocks Preparation Start
        coEvery {
            restClient.get(
                request = eq(whitelistRequestToMock)
            )
        }.returns(whitelistResponseToReturn)
        // Mocks Preparation End

        // Double running should be checked accordingly in verify block
        whitelistRepository.getWhitelistedTokens()
        val result = whitelistRepository.getWhitelistedTokens()

        // Verification & Assertion
        // Verify that implementation is caching, and network request was performed once
        coVerify {
            restClient.get(
                request = eq(whitelistRequestToMock)
            )
        }.wasInvoked(1)

        assertContentEquals(
            whitelistResponseToReturn.map {
                InternalWhitelistedToken(
                    tokenAddress = it.fieldOrNull("address").orEmpty(),
                    rawIconLink = it.fieldOrNull("icon").orEmpty(),
                )
            },
            result
        )
    }

}