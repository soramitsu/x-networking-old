package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case1

import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.SbApyInfo

internal fun mapSoraWalletSbApyCase1(subQuerySbApyResponse: SoraWalletSbApyCase1Response): List<SbApyInfo> {
    return subQuerySbApyResponse.data.poolXYKs.nodes.map {
        SbApyInfo(
            tokenId = it.id,
            priceUsd = it.priceUSD?.toDouble(),
            sbApy = it.strategicBonusApy?.toDouble(),
        )
    }
}
