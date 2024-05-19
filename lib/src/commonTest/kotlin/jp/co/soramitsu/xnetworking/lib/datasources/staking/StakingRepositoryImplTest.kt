package jp.co.soramitsu.xnetworking.lib.datasources.staking

import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.StakingRepository
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ApyFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.UnbondingFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ValidatorsFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.StakingRepositoryImpl
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class StakingRepositoryImplTest {

    @Mock
    private val apyFetcherMock = mock(classOf<ApyFetcher>())

    @Mock
    private val unbondingFetcherMock = mock(classOf<UnbondingFetcher>())

    @Mock
    private val validatorsFetcherMock = mock(classOf<ValidatorsFetcher>())

    private val stakingRepository: StakingRepository =
        StakingRepositoryImpl(
            apyFetcher = apyFetcherMock,
            unbondingFetcher = unbondingFetcherMock,
            validatorsFetcher = validatorsFetcherMock
        )

    @Test
    fun `TEST getApy EXPECT success`() = runTest {
        val chainId = "sora"
        val selectedCandidates = null

        coEvery {
            apyFetcherMock.fetch(
                chainId = chainId,
                selectedCandidates = selectedCandidates
            )
        }.returns(emptyMap())

        stakingRepository.getApy(
            chainId = chainId,
            selectedCandidates = selectedCandidates
        )

        coVerify {
            apyFetcherMock.fetch(
                chainId = chainId,
                selectedCandidates = selectedCandidates
            )
        }.wasInvoked(1)
    }

    @Test
    fun `TEST getUnbondingsList EXPECT success`() = runTest {
        val chainId = "sora"
        val delegatorAddress = ""
        val collatorAddress = ""

        coEvery {
            unbondingFetcherMock.fetch(
                chainId = chainId,
                delegatorAddress = delegatorAddress,
                collatorAddress = collatorAddress
            )
        }.returns(emptyList())

        stakingRepository.getUnbondingsList(
            chainId = chainId,
            delegatorAddress = delegatorAddress,
            collatorAddress = collatorAddress
        )

        coVerify {
            unbondingFetcherMock.fetch(
                chainId = chainId,
                delegatorAddress = delegatorAddress,
                collatorAddress = collatorAddress
            )
        }.wasInvoked(1)
    }

    @Test
    fun `TEST getValidatorsList EXPECT success`() = runTest {
        val chainId = "sora"
        val stashAccountAddress = ""
        val historicalRange = emptyList<String>()

        coEvery {
            validatorsFetcherMock.fetch(
                chainId = chainId,
                stashAccountAddress = stashAccountAddress,
                historicalRange = historicalRange
            )
        }.returns(emptyList())

        stakingRepository.getValidatorsList(
            chainId = chainId,
            stashAccountAddress = stashAccountAddress,
            historicalRange = historicalRange
        )

        coVerify {
            validatorsFetcherMock.fetch(
                chainId = chainId,
                stashAccountAddress = stashAccountAddress,
                historicalRange = historicalRange
            )
        }.wasInvoked(1)
    }

}