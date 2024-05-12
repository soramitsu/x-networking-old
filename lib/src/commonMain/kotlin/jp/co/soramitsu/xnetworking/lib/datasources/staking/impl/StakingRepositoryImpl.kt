package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl

import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.StakingRepository
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ApyFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.models.Unbonding
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.UnbondingFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ValidatorsFetcher

class StakingRepositoryImpl(
    private val apyFetcher: ApyFetcher,
    private val validatorsFetcher: ValidatorsFetcher,
    private val unbondingFetcher: UnbondingFetcher,
): StakingRepository {

    override suspend fun getUnbondingsList(
        chainId: String,
        delegatorAddress: String,
        collatorAddress: String
    ): List<Unbonding> {
        return unbondingFetcher.fetch(chainId, delegatorAddress, collatorAddress)
    }

    override suspend fun getValidatorsList(
        chainId: String,
        stashAccountAddress: String,
        historicalRange: List<String>
    ): List<String> {
        return validatorsFetcher.fetch(chainId, stashAccountAddress, historicalRange)
    }

    override suspend fun getApy(
        chainId: String,
        selectedCandidates: List<String>?
    ): Map<String, String?> {
        return apyFetcher.fetch(chainId, selectedCandidates)
    }

}