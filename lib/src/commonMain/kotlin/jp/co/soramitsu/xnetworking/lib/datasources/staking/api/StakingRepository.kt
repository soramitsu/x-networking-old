package jp.co.soramitsu.xnetworking.lib.datasources.staking.api

import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.models.Unbonding

interface StakingRepository {

    suspend fun getUnbondingsList(
        chainId: String,
        delegatorAddress: String,
        collatorAddress: String
    ): List<Unbonding>

    suspend fun getValidatorsList(
        chainId: String,
        stashAccountAddress: String,
        historicalRange: List<String>
    ): List<String>

    suspend fun getApy(
        chainId: String,
        selectedCandidates: List<String>? = null
    ): Map<String, String?>

}