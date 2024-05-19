package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquid

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ConfigDAO
import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ApyFetcher
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient

class SubSquidApyFetcher(
    private val configDAO: ConfigDAO,
    private val restClient: RestClient
): ApyFetcher() {

    override suspend fun fetch(
        chainId: String,
        selectedCandidates: List<String>?
    ): Map<String, String?> {
        require(selectedCandidates?.all { it.startsWith("0x") } ?: true) {
            "Selected Collator Ids are not hex values."
        }

        val stakers = restClient.post(
            request = SubSquidApyRequest(
                url = configDAO.stakingUrl(chainId)
            )
        ).data.stakers

        return stakers.mapNotNull {
            if (it.stashId == null)
                return@mapNotNull null

            return@mapNotNull  it.stashId to it.apr24h
        }.toMap()
    }

}