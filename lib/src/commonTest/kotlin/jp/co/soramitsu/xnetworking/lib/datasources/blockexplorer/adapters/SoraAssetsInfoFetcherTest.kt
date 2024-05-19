package jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.adapters

import com.apollographql.apollo3.api.Optional
import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.fake.valueOf
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.adapters.AssetsInfoFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.AssetsInfoResponse
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.adapters.SoraAssetsInfoFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ConfigDAO
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ExternalApiDAOException
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ExternalApiType
import jp.co.soramitsu.xnetworking.lib.engines.apollo.api.ApolloClientStore
import jp.co.soramitsu.xnetworking.sorawallet.GetAssetsInfoQuery
import jp.co.soramitsu.xnetworking.sorawallet.type.buildAsset
import jp.co.soramitsu.xnetworking.sorawallet.type.buildAssetsConnection
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class SoraAssetsInfoFetcherTest {

    private companion object {
        const val chainId = ""
        const val requestUrl = ""

        const val pageCount = 0
        const val cursor = ""

        val tokenIds = listOf<String>()
        const val timeStamp = 0
    }

    @Mock
    private val apolloClientStore = mock(classOf<ApolloClientStore>())

    @Mock
    private val configDAO = mock(classOf<ConfigDAO>())

    private val assetsInfoFetcher: AssetsInfoFetcher =
        SoraAssetsInfoFetcher(
            apolloClientStore = apolloClientStore,
            configDAO = configDAO
        )

    @Test
    fun `TEST soraAssetsInfoFetcher_fetch EXPECT ExternalApiDAOException_NullUrl BECAUSE stakingUrl is null`() =
        runTest {
            // Mock Preparation Start
            coEvery {
                configDAO.stakingUrl(
                    chainId = chainId
                )
            }.throws(ExternalApiDAOException.NullUrl(chainId))
            // Mock Preparation End

            assertFailsWith<ExternalApiDAOException.NullUrl> {
                assetsInfoFetcher.fetch(
                    chainId = chainId,
                    tokenIds = tokenIds,
                    timeStamp = timeStamp
                )
            }
        }

//    @Test
//    fun `TEST soraAssetsInfoFetcher_fetch EXPECT success`() =
//        runTest {
//            // Test Data Start
//            val assetsInfoRequestToMock =
//                GetAssetsInfoQuery(
//                    pageCount = pageCount,
//                    cursor = cursor,
//                    tokenIds = Optional.present(tokenIds),
//                    timestamp = timeStamp
//                )
//
//            val assetsInfoResponseToReturn =
//                GetAssetsInfoQuery.Data {
//                    this.assets = buildAssetsConnection {
//                        this.nodes = listOf(
//                            buildAsset {
//                                this.id = "123"
//                                this.liquidity = "123"
//                                this.priceUSD = ""
//                            }
//                        )
//                    }
//                }
//
//            val expectedResult = listOf(
//                AssetsInfoResponse(
//                    id = "id_123",
//                    liquidity = "liquidity_123",
//                    previousPrice = 123.0
//                )
//            )
//            // Test Data End
//
//            // Mock Preparation Start
//            coEvery {
//                configDAO.stakingUrl(
//                    chainId = chainId
//                )
//            }.returns(requestUrl)
//
//            coEvery {
//                apolloClientStore.query(
//                    serverUrl = requestUrl,
//                    query = assetsInfoRequestToMock
//                )
//            }.returns(assetsInfoResponseToReturn)
//            // Mock Preparation End
//
//            val result = assetsInfoFetcher.fetch(
//                chainId = chainId,
//                tokenIds = tokenIds,
//                timeStamp = timeStamp
//            )
//
//            // Validation & Assertion
//            coVerify {
//                apolloClientStore.query(
//                    serverUrl = requestUrl,
//                    query = assetsInfoRequestToMock
//                )
//            }.wasNotInvoked()
//
//            assertTrue { result == expectedResult }
//        }

}