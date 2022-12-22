package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo

import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkException
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.fiat.FiatData
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.fiat.SoraWalletFiatCase
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.fiat.SoraWalletFiatCases
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral.ReferrerRewardsInfo
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral.SoraWalletReferralCase
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral.SoraWalletReferralCases
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.SbApyInfo
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.SoraWalletSbApyCase
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.SoraWalletSbApyCases
import kotlin.coroutines.cancellation.CancellationException

class SoraWalletBlockExplorerInfo(
    private val networkClient: SoramitsuNetworkClient,
    private val baseUrl: String,
    caseName: String,
) {

    private val referralCase: SoraWalletReferralCase = SoraWalletReferralCases.getReferralCase(caseName)
    private val sbApyCase: SoraWalletSbApyCase = SoraWalletSbApyCases.getSbApyCase(caseName)
    private val fiatCase: SoraWalletFiatCase = SoraWalletFiatCases.getFiatCase(caseName)

    @Throws(
        SoramitsuNetworkException::class,
        CancellationException::class,
        IllegalArgumentException::class
    )
    suspend fun getFiat(): List<FiatData> {
        return fiatCase.getFiat(baseUrl, networkClient)
    }

    @Throws(
        SoramitsuNetworkException::class,
        CancellationException::class,
        IllegalArgumentException::class
    )
    suspend fun getSpApy(): List<SbApyInfo> {
        return sbApyCase.getSbApy(baseUrl, networkClient)
    }

    @Throws(
        SoramitsuNetworkException::class,
        CancellationException::class,
        IllegalArgumentException::class
    )
    suspend fun getReferrerRewards(
        address: String,
    ): ReferrerRewardsInfo {
        return referralCase.getReferrerInfo(baseUrl, address, networkClient)
    }
}
