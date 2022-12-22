package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case2

import jp.co.soramitsu.xnetworking.common.Utils.toDoubleNan
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.SbApyInfo

internal fun mapSoraWalletSbApyCase2(subQuerySbApyResponse: SoraWalletSbApyCase2Response): List<SbApyInfo> {
    return subQuerySbApyResponse.data.poolXYKs.nodes.map {
        SbApyInfo(
            id = it.id,
            sbApy = it.strategicBonusApy?.toDoubleNan(),
        )
    }
}
