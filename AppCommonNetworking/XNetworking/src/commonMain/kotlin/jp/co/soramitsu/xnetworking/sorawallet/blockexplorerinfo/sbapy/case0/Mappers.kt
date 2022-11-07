package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case0

import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.SbApyInfo

internal fun mapSoraWalletSbApyCase0(subQuerySbApyResponse: SoraWalletSbApyCase0Response): List<SbApyInfo> {
    return subQuerySbApyResponse.data.poolXYKEntities.nodes.firstOrNull()?.pools?.edges?.map {
        SbApyInfo(
            tokenId = it.node.targetAssetId,
            priceUsd = it.node.priceUSD?.toDoubleOrNull(),
            sbApy = it.node.strategicBonusApy?.toDoubleOrNull(),
        )
    } ?: emptyList()
}
