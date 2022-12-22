package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case0

import jp.co.soramitsu.xnetworking.common.Utils.toDoubleNan
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.SbApyInfo

internal fun mapSoraWalletSbApyCase0(subQuerySbApyResponse: SoraWalletSbApyCase0Response): List<SbApyInfo> {
    return subQuerySbApyResponse.data.poolXYKEntities.nodes.firstOrNull()?.pools?.edges?.map {
        SbApyInfo(
            id = it.node.targetAssetId,
            priceUsd = it.node.priceUSD?.toDoubleNan(),
            sbApy = it.node.strategicBonusApy?.toDoubleNan(),
        )
    } ?: emptyList()
}
