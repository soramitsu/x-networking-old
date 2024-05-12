package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquid

import kotlinx.serialization.Serializable

@Serializable
internal class SubSquidApyResponse(
    val stakers: List<CollatorApyElement>
) {
    @Serializable
    class CollatorApyElement(
        val stashId: String?,
        val apr24h: String?
    )
}