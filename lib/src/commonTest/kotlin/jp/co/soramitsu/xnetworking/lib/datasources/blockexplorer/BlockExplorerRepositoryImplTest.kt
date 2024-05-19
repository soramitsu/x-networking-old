package jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer

import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.BlockExplorerRepository
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.adapters.AssetsInfoFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.adapters.FiatDataFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.adapters.ReferrerRewardFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.adapters.SbApyFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.AssetsInfoResponse
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.FiatDataResponse
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.ReferrerRewardResponse
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.SbApyInfoResponse
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.BlockExplorerRepositoryImpl
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ConfigDAO
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ExternalApiType
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class BlockExplorerRepositoryImplTest {

    private companion object {
        const val chainId = "sora"
    }

    @Mock
    private val assetsInfoFetcher = mock(classOf<AssetsInfoFetcher>())

    @Mock
    private val fiatDataFetcher = mock(classOf<FiatDataFetcher>())

    @Mock
    private val referrerRewardFetcher = mock(classOf<ReferrerRewardFetcher>())

    @Mock
    private val sbApyFetcher = mock(classOf<SbApyFetcher>())

    @Mock
    private val configDAO = mock(classOf<ConfigDAO>())

    private val blockExplorerRepository: BlockExplorerRepository =
        BlockExplorerRepositoryImpl(
            assetsInfoFetcher = assetsInfoFetcher,
            fiatDataFetcher = fiatDataFetcher,
            referrerRewardFetcher = referrerRewardFetcher,
            sbApyFetcher = sbApyFetcher,
            configDAO = configDAO
        )

    @Test
    fun `TEST getAssetsInfo EXPECT IllegalStateException BECAUSE stakingType is not Sora`() =
        runTest {
            // Test Data Start

            val tokenIds = emptyList<String>()
            val timeStamp = 0

            // Test Data End

            // Mock Preparation Start
            coEvery {
                configDAO.stakingType(chainId)
            }.returns(ExternalApiType.ETHERSCAN)
            // Mock Preparation End

            assertFailsWith<IllegalStateException> {
                blockExplorerRepository.getAssetsInfo(
                    chainId = chainId,
                    tokenIds = tokenIds,
                    timeStamp = timeStamp
                )
            }

            // Validation & Assertion
            coVerify {
                assetsInfoFetcher.fetch(
                    chainId = chainId,
                    tokenIds = tokenIds,
                    timeStamp = timeStamp
                )
            }.wasNotInvoked()
        }

    @Test
    fun `TEST getAssetsInfo EXPECT success`() =
        runTest {
            // Test Data Start

            val tokenIds = emptyList<String>()
            val timeStamp = 0

            val resultToReturn = listOf<AssetsInfoResponse>()

            val expectedResult = listOf<AssetsInfoResponse>()
            // Test Data End

            // Mock Preparation Start
            coEvery {
                configDAO.stakingType(chainId)
            }.returns(ExternalApiType.SORA)

            coEvery {
                assetsInfoFetcher.fetch(
                    chainId = chainId,
                    tokenIds = tokenIds,
                    timeStamp = timeStamp
                )
            }.returns(resultToReturn)
            // Mock Preparation End

            val result = blockExplorerRepository.getAssetsInfo(
                chainId = chainId,
                tokenIds = tokenIds,
                timeStamp = timeStamp
            )

            // Validation & Assertion
            coVerify {
                assetsInfoFetcher.fetch(
                    chainId = chainId,
                    tokenIds = tokenIds,
                    timeStamp = timeStamp
                )
            }.wasInvoked(1)

            assertTrue { result == expectedResult }
        }

    @Test
    fun `TEST getFiat EXPECT IllegalStateException BECAUSE stakingType is not Sora`() =
        runTest {
            // Mock Preparation Start
            coEvery {
                configDAO.stakingType(chainId)
            }.returns(ExternalApiType.ETHERSCAN)
            // Mock Preparation End

            assertFailsWith<IllegalStateException> {
                blockExplorerRepository.getFiat(
                    chainId = chainId
                )
            }

            // Validation & Assertion
            coVerify {
                fiatDataFetcher.fetch(
                    chainId = chainId
                )
            }.wasNotInvoked()
        }

    @Test
    fun `TEST getFiat EXPECT success`() =
        runTest {
            // Test Data Start
            val resultToReturn = listOf<FiatDataResponse>()

            val expectedResult = listOf<FiatDataResponse>()
            // Test Data End

            // Mock Preparation Start
            coEvery {
                configDAO.stakingType(chainId)
            }.returns(ExternalApiType.SORA)

            coEvery {
                fiatDataFetcher.fetch(
                    chainId = chainId
                )
            }.returns(resultToReturn)
            // Mock Preparation End

            val result = blockExplorerRepository.getFiat(
                chainId = chainId
            )

            // Validation & Assertion
            coVerify {
                fiatDataFetcher.fetch(
                    chainId = chainId
                )
            }.wasInvoked(1)

            assertTrue { result == expectedResult }
        }

    @Test
    fun `TEST getReferrerRewards EXPECT IllegalStateException BECAUSE stakingType is not Sora`() =
        runTest {
            // Test Data Start
            val address = ""
            // Test Data End

            // Mock Preparation Start
            coEvery {
                configDAO.stakingType(chainId)
            }.returns(ExternalApiType.ETHERSCAN)
            // Mock Preparation End

            assertFailsWith<IllegalStateException> {
                blockExplorerRepository.getReferrerRewards(
                    chainId = chainId,
                    address = address
                )
            }

            // Validation & Assertion
            coVerify {
                referrerRewardFetcher.fetch(
                    chainId = chainId,
                    address = address
                )
            }.wasNotInvoked()
        }

    @Test
    fun `TEST getReferrerRewards EXPECT success`() =
        runTest {
            // Test Data Start
            val address = ""

            val resultToReturn = listOf<ReferrerRewardResponse>()

            val expectedResult = listOf<ReferrerRewardResponse>()
            // Test Data End

            // Mock Preparation Start
            coEvery {
                configDAO.stakingType(chainId)
            }.returns(ExternalApiType.SORA)

            coEvery {
                referrerRewardFetcher.fetch(
                    chainId = chainId,
                    address = address
                )
            }.returns(resultToReturn)
            // Mock Preparation End

            val result = blockExplorerRepository.getReferrerRewards(
                chainId = chainId,
                address = address
            )

            // Validation & Assertion
            coVerify {
                referrerRewardFetcher.fetch(
                    chainId = chainId,
                    address = address
                )
            }.wasInvoked(1)

            assertTrue { result == expectedResult }
        }

    @Test
    fun `TEST getSbApyInfo EXPECT IllegalStateException BECAUSE stakingType is not Sora`() =
        runTest {
            // Mock Preparation Start
            coEvery {
                configDAO.stakingType(chainId)
            }.returns(ExternalApiType.ETHERSCAN)
            // Mock Preparation End

            assertFailsWith<IllegalStateException> {
                blockExplorerRepository.getSbApyInfo(
                    chainId = chainId
                )
            }

            // Validation & Assertion
            coVerify {
                sbApyFetcher.fetch(
                    chainId = chainId
                )
            }.wasNotInvoked()
        }

    @Test
    fun `TEST getSbApyInfo EXPECT success`() =
        runTest {
            // Test Data Start
            val address = ""

            val resultToReturn = listOf<SbApyInfoResponse>()

            val expectedResult = listOf<SbApyInfoResponse>()
            // Test Data End

            // Mock Preparation Start
            coEvery {
                configDAO.stakingType(chainId)
            }.returns(ExternalApiType.SORA)

            coEvery {
                sbApyFetcher.fetch(
                    chainId = chainId
                )
            }.returns(resultToReturn)
            // Mock Preparation End

            val result = blockExplorerRepository.getSbApyInfo(
                chainId = chainId
            )

            // Validation & Assertion
            coVerify {
                sbApyFetcher.fetch(
                    chainId = chainId
                )
            }.wasInvoked(1)

            assertTrue { result == expectedResult }
        }

}