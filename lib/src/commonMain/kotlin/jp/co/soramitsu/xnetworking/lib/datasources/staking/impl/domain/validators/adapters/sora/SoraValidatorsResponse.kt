package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.validators.adapters.sora

import kotlinx.serialization.Serializable

@Serializable
internal class SoraValidatorsResponse(
    val stakingEraNominators: List<Nominator>
) {
    @Serializable
    class Nominator(
        val nominations: List<Nomination>
    ) {
        @Serializable
        class Nomination(
            val validator: Validator
        ) {
            @Serializable
            class Validator(
                val id: String? = null
            )
        }
    }
}