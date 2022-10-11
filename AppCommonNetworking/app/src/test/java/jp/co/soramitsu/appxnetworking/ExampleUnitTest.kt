package jp.co.soramitsu.appxnetworking

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import jp.co.soramitsu.xnetworking.fearless.FearlessChainsBuilder
import jp.co.soramitsu.xnetworking.fearless.ResultChainInfo
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuHttpClientProvider
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.SoraWalletBlockExplorerInfo
import jp.co.soramitsu.xnetworking.sorawallet.envbuilder.SoraEnv
import jp.co.soramitsu.xnetworking.sorawallet.envbuilder.SoraEnvBuilder
import jp.co.soramitsu.xnetworking.sorawallet.tokenwhitelist.SoraTokensWhitelistManager
import jp.co.soramitsu.xnetworking.txhistory.client.fearlesswallet.SubQueryClientForFearlessWallet
import jp.co.soramitsu.xnetworking.txhistory.client.sorawallet.SubQueryClientForSoraWallet
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
class ExampleUnitTest {

    @MockK
    lateinit var soramitsuNetworkClient: SoramitsuNetworkClient

    @MockK
    lateinit var fearlessChainsBuilder: FearlessChainsBuilder

    @MockK
    lateinit var soraEnvBuilder: SoraEnvBuilder

    @MockK
    lateinit var soraWalletBlockExplorerInfo: SoraWalletBlockExplorerInfo

    @MockK
    lateinit var subQuerySora: SubQueryClientForSoraWallet

    @MockK
    lateinit var subQueryFearless: SubQueryClientForFearlessWallet

    @MockK
    lateinit var whitelistManager: SoraTokensWhitelistManager

    lateinit var networkService: NetworkService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        networkService =
            NetworkService(
                soramitsuNetworkClient,
                fearlessChainsBuilder,
                soraEnvBuilder,
                subQueryFearless,
                subQuerySora,
                soraWalletBlockExplorerInfo,
                whitelistManager,
            )
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getChains() = runTest {
        coEvery {
            fearlessChainsBuilder.getChains(
                any(),
                any()
            )
        } returns ResultChainInfo(emptyList(), emptyList(), emptyList())
        val chains = networkService.getChains()
        assertEquals(0, chains.newChains.size)
    }

    @Test
    fun getSoraEnv() = runTest {
        coEvery {
            soraEnvBuilder.getSoraEnv()
        } returns SoraEnv(emptyList())
        val soraEnv = networkService.getSoraEnv()
        assertEquals(0, soraEnv.nodes.size)
    }

    @Serializable
    data class Reply(
        val ip: String
    )

    @Test
    fun testClientMock() = runTest {
        val mockEngine = MockEngine { request ->
            respond(
                content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val client = SoramitsuNetworkClient(
            provider = object : SoramitsuHttpClientProvider {
                override fun provide(logging: Boolean, timeout: Long, json: Json): HttpClient {
                    return HttpClient(mockEngine) {
                        install(ContentNegotiation) {
                            json(
                                Json {
                                    prettyPrint = true
                                    isLenient = true
                                    ignoreUnknownKeys = true
                                },
                                contentType = ContentType.Any
                            )
                        }
                    }
                }
            }
        )
        val s: Reply = client.createJsonRequest("https://www.sora.org")
        assertTrue(s.ip.contains("127."))
    }
}
