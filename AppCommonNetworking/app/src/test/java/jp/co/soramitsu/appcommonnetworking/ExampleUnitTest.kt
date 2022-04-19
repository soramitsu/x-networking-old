package jp.co.soramitsu.appcommonnetworking

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import jp.co.soramitsu.commonnetworking.fearless.FearlessChainsBuilder
import jp.co.soramitsu.commonnetworking.fearless.ResultChainInfo
import jp.co.soramitsu.commonnetworking.networkclient.SoraNetworkClient
import jp.co.soramitsu.commonnetworking.subquery.SubQueryClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
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
    lateinit var soraNetworkClient: SoraNetworkClient

    @MockK
    lateinit var fearlessChainsBuilder: FearlessChainsBuilder

    @MockK
    lateinit var subQueryClient: SubQueryClient

    lateinit var networkService: NetworkService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        networkService =
            NetworkService(soraNetworkClient, fearlessChainsBuilder, subQueryClient)
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
}
