package jp.co.soramitsu.xnetworking.lib.datasources.staking.unbonding

import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ConfigDAO
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ExternalApiDAOException
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ExternalApiType
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.StakingOption
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.UnbondingFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.models.Unbonding
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.unbonding.UnbondingFetcherFacade
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFailsWith

class UnbondingFetcherFacadeTest {

    private companion object {
        const val chainId = "sora"
        const val delegatorAddress = ""
        const val collatorAddress = ""
    }

    @Mock
    private val configDAO = mock(classOf<ConfigDAO>())

    @Mock
    private val unbondingFetcherMock = mock(classOf<UnbondingFetcher>())

    @Test
    fun `TEST unbondingFacade_fetch EXPECT IllegalStateException BECAUSE network staking type is not paraChain`() =
        runTest {
            // Test Data Start
            val facade: UnbondingFetcher =
                UnbondingFetcherFacade(
                    configDAO = configDAO,
                    unbondingFetcherMap = mapOf(
                        ExternalApiType.SORA to unbondingFetcherMock
                    )
                )
            // Test Data End

            // Mocks Preparation Start
            coEvery {
                configDAO.staking(
                    chainId = chainId
                )
            }.returns(StakingOption.RELAYCHAIN)
            // Mocks Preparation End

            assertFailsWith<IllegalStateException> {
                facade.fetch(
                    chainId = chainId,
                    delegatorAddress = delegatorAddress,
                    collatorAddress = collatorAddress
                )
            }

            // Verification & Assertion
            coVerify {
                unbondingFetcherMock.fetch(
                    chainId = chainId,
                    delegatorAddress = delegatorAddress,
                    collatorAddress = collatorAddress
                )
            }.wasNotInvoked()
        }

    @Test
    fun `TEST unbondingFacade_fetch EXPECT ExternalApiDAOException_NullType BECAUSE staking type is null`() =
        runTest {
            // Test Data Start
            val facade: UnbondingFetcher =
                UnbondingFetcherFacade(
                    configDAO = configDAO,
                    unbondingFetcherMap = mapOf(
                        ExternalApiType.SORA to unbondingFetcherMock
                    )
                )
            // Test Data End

            // Mocks Preparation Start
            coEvery {
                configDAO.stakingType(
                    chainId = chainId
                )
            }.throws(ExternalApiDAOException.NullType(chainId))

            coEvery {
                configDAO.staking(
                    chainId = chainId
                )
            }.returns(StakingOption.PARACHAIN)
            // Mocks Preparation End

            assertFailsWith<ExternalApiDAOException.NullType> {
                facade.fetch(
                    chainId = chainId,
                    delegatorAddress = delegatorAddress,
                    collatorAddress = collatorAddress
                )
            }

            // Verification & Assertion
            coVerify {
                unbondingFetcherMock.fetch(
                    chainId = chainId,
                    delegatorAddress = delegatorAddress,
                    collatorAddress = collatorAddress
                )
            }.wasNotInvoked()
        }

    @Test
    fun `TEST unbondingFacade_fetch EXPECT IllegalStateException BECAUSE fetcher not found`() =
        runTest {
            // Test Data Start
            val facade: UnbondingFetcher =
                UnbondingFetcherFacade(
                    configDAO = configDAO,
                    unbondingFetcherMap = mapOf(
                        ExternalApiType.SORA to unbondingFetcherMock
                    )
                )
            // Test Data End

            // Mocks Preparation Start
            coEvery {
                configDAO.stakingType(
                    chainId = chainId
                )
            }.returns(ExternalApiType.SUBQUERY)

            coEvery {
                configDAO.staking(
                    chainId = chainId
                )
            }.returns(StakingOption.PARACHAIN)
            // Mocks Preparation End

            assertFailsWith<IllegalStateException> {
                facade.fetch(
                    chainId = chainId,
                    delegatorAddress = delegatorAddress,
                    collatorAddress = collatorAddress
                )
            }

            // Verification & Assertion
            coVerify {
                unbondingFetcherMock.fetch(
                    chainId = chainId,
                    delegatorAddress = delegatorAddress,
                    collatorAddress = collatorAddress
                )
            }.wasNotInvoked()
        }

    @Test
    fun `TEST unbondingFacade_fetch EXPECT success`() = runTest {
        // Test Data Start
        val facade: UnbondingFetcher =
            UnbondingFetcherFacade(
                configDAO = configDAO,
                unbondingFetcherMap = mapOf(
                    ExternalApiType.SORA to unbondingFetcherMock
                )
            )

        val unbondingListToReturn =
            listOf(
                Unbonding(
                    amount = "123",
                    timestamp = "123",
                    type = Unbonding.DelegationAction.STAKE
                )
            )
        // Test Data End

        // Mocks Preparation Start
        coEvery {
            configDAO.stakingType(
                chainId = chainId
            )
        }.returns(ExternalApiType.SORA)

        coEvery {
            configDAO.staking(
                chainId = chainId
            )
        }.returns(StakingOption.PARACHAIN)

        coEvery {
            unbondingFetcherMock.fetch(
                chainId = chainId,
                delegatorAddress = delegatorAddress,
                collatorAddress = collatorAddress
            )
        }.returns(unbondingListToReturn)
        // Mocks Preparation End

        val result = facade.fetch(
            chainId = chainId,
            delegatorAddress = delegatorAddress,
            collatorAddress = collatorAddress
        )

        // Verification & Assertion
        coVerify {
            unbondingFetcherMock.fetch(
                chainId = chainId,
                delegatorAddress = delegatorAddress,
                collatorAddress = collatorAddress
            )
        }.wasInvoked(1)

        assertContentEquals(
            unbondingListToReturn,
            result
        )
    }
}