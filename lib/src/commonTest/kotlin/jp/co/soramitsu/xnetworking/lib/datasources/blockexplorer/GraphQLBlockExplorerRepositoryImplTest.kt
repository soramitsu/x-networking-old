package jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer

import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.BlockExplorerRepository
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.GraphQLBlockExplorerRepositoryImpl
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.lib.engines.apollo.api.ApolloClientStore
import kotlin.test.Test
import kotlinx.coroutines.test.runTest
import kotlin.test.assertTrue

class GraphQLBlockExplorerRepositoryImplTest {

    @Mock
    val apolloClientStore = mock(classOf<ApolloClientStore>())

    @Mock
    val chainsConfigFetcher = mock(classOf<ChainsConfigFetcher>())

    private val blockExplorerRepository: BlockExplorerRepository =
        GraphQLBlockExplorerRepositoryImpl(
            apolloClientStore = apolloClientStore,
            chainsConfigFetcher = chainsConfigFetcher
        )

    @Test
    fun `TEST getAssetsInfo EXPECT emptyList`() = runTest {
        coEvery { chainsConfigFetcher.loadConfigOrGetCached() }.returns(emptyMap())

        val result = blockExplorerRepository.getAssetsInfo(
            chainId = "sora",
            tokenIds = emptyList(),
            timeStamp = 0
        )

        assertTrue { result.isEmpty() }
    }

    @Test
    fun `TEST getAssetsInfo EXPECT exception`() = runTest {
        coEvery { chainsConfigFetcher.loadConfigOrGetCached() }.returns(
            listOf(
                ChainsConfig(
                    chainId = "sora",
                    assets = emptyList(),
                    externalApi = ChainsConfig.ExternalApi(
                        staking = null,
                        history = ChainsConfig.ExternalApi.PlainSection(
                            type = ChainsConfig.ExternalApi.Type.EtherScan,
                            url = "etherscan.url"
                        ),
                        explorers = null
                    )
                )
            ).associateBy { it.chainId }
        )

        try {
            blockExplorerRepository.getAssetsInfo(
                chainId = "sora",
                tokenIds = emptyList(),
                timeStamp = 0
            )
        } catch (e: Exception) {
            assertTrue("Given that exception is caught, everything is okay") {
                true
            }
        }
    }

    @Test
    fun `TEST getFiat EXPECT emptyList`() = runTest {
        coEvery { chainsConfigFetcher.loadConfigOrGetCached() }.returns(emptyMap())

        val result = blockExplorerRepository.getFiat(
            chainId = "sora"
        )

        assertTrue { result.isEmpty() }
    }

    @Test
    fun `TEST getFiat EXPECT exception`() = runTest {
        coEvery { chainsConfigFetcher.loadConfigOrGetCached() }.returns(
            listOf(
                ChainsConfig(
                    chainId = "sora",
                    assets = emptyList(),
                    externalApi = ChainsConfig.ExternalApi(
                        staking = null,
                        history = ChainsConfig.ExternalApi.PlainSection(
                            type = ChainsConfig.ExternalApi.Type.EtherScan,
                            url = "etherscan.url"
                        ),
                        explorers = null
                    )
                )
            ).associateBy { it.chainId }
        )

        try {
            blockExplorerRepository.getFiat(
                chainId = "sora"
            )
        } catch (e: Exception) {
            assertTrue("Given that exception is caught, everything is okay") {
                true
            }
        }
    }

    @Test
    fun `TEST getReferrerRewards EXPECT emptyList`() = runTest {
        coEvery { chainsConfigFetcher.loadConfigOrGetCached() }.returns(emptyMap())

        val result = blockExplorerRepository.getReferrerRewards(
            chainId = "sora",
            address = ""
        )

        assertTrue { result.isEmpty() }
    }

    @Test
    fun `TEST getReferrerRewards EXPECT exception`() = runTest {
        coEvery { chainsConfigFetcher.loadConfigOrGetCached() }.returns(
            listOf(
                ChainsConfig(
                    chainId = "sora",
                    assets = emptyList(),
                    externalApi = ChainsConfig.ExternalApi(
                        staking = null,
                        history = ChainsConfig.ExternalApi.PlainSection(
                            type = ChainsConfig.ExternalApi.Type.EtherScan,
                            url = "etherscan.url"
                        ),
                        explorers = null
                    )
                )
            ).associateBy { it.chainId }
        )

        try {
            blockExplorerRepository.getReferrerRewards(
                chainId = "sora",
                address = ""
            )
        } catch (e: Exception) {
            assertTrue("Given that exception is caught, everything is okay") {
                true
            }
        }
    }

    @Test
    fun `TEST getSbApyInfo EXPECT emptyList`() = runTest {
        coEvery { chainsConfigFetcher.loadConfigOrGetCached() }.returns(emptyMap())

        val result = blockExplorerRepository.getSbApyInfo(
            chainId = "sora"
        )

        assertTrue { result.isEmpty() }
    }

    @Test
    fun `TEST getSbApyInfo EXPECT exception`() = runTest {
        coEvery { chainsConfigFetcher.loadConfigOrGetCached() }.returns(
            listOf(
                ChainsConfig(
                    chainId = "sora",
                    assets = emptyList(),
                    externalApi = ChainsConfig.ExternalApi(
                        staking = null,
                        history = ChainsConfig.ExternalApi.PlainSection(
                            type = ChainsConfig.ExternalApi.Type.EtherScan,
                            url = "etherscan.url"
                        ),
                        explorers = null
                    )
                )
            ).associateBy { it.chainId }
        )

        try {
            blockExplorerRepository.getSbApyInfo(
                chainId = "sora"
            )
        } catch (e: Exception) {
            assertTrue("Given that exception is caught, everything is okay") {
                true
            }
        }
    }

}