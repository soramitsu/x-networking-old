package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy

import io.ktor.http.HttpMethod
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.BasicCases
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case0.SoraWalletSbApyCase0Response
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case0.graphQLRequestSoraWalletSbApyCase0
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case0.mapSoraWalletSbApyCase0
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case1.SoraWalletSbApyCase1Response
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case1.graphQLRequestSoraWalletSbApyCase1
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case1.mapSoraWalletSbApyCase1
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case2.SoraWalletSbApyCase2Response
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case2.graphQLRequestSoraWalletSbApyCase2
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case2.mapSoraWalletSbApyCase2
import jp.co.soramitsu.xnetworking.txhistory.subquery.graphqlrequest.SubQueryRequest

internal interface SoraWalletSbApyCase {

    suspend fun getSbApy(url: String, networkClient: SoramitsuNetworkClient): List<SbApyInfo>
}

internal object SoraWalletSbApyCases : BasicCases<SoraWalletSbApyCase>() {

    override fun provideInstance(caseName: String): SoraWalletSbApyCase {
        return when (caseName) {
            "0" -> SoraWalletSbApyCase0()
            "1" -> SoraWalletSbApyCase1()
            "2" -> SoraWalletSbApyCase2()
            else -> throw IllegalArgumentException("SoraWalletSbApyCases [$caseName] not found")
        }
    }
}

private class SoraWalletSbApyCase0 : SoraWalletSbApyCase {

    override suspend fun getSbApy(
        url: String,
        networkClient: SoramitsuNetworkClient
    ): List<SbApyInfo> {
        val response = networkClient.createJsonRequest<SoraWalletSbApyCase0Response>(
            url,
            HttpMethod.Post,
            SubQueryRequest(graphQLRequestSoraWalletSbApyCase0())
        )
        return mapSoraWalletSbApyCase0(response)
    }
}

private class SoraWalletSbApyCase1 : SoraWalletSbApyCase {

    override suspend fun getSbApy(
        url: String,
        networkClient: SoramitsuNetworkClient
    ): List<SbApyInfo> {
        val response = networkClient.createJsonRequest<SoraWalletSbApyCase1Response>(
            url,
            HttpMethod.Post,
            SubQueryRequest(graphQLRequestSoraWalletSbApyCase1())
        )
        return mapSoraWalletSbApyCase1(response)
    }
}

private class SoraWalletSbApyCase2 : SoraWalletSbApyCase {

    override suspend fun getSbApy(
        url: String,
        networkClient: SoramitsuNetworkClient
    ): List<SbApyInfo> {
        val response = networkClient.createJsonRequest<SoraWalletSbApyCase2Response>(
            url,
            HttpMethod.Post,
            SubQueryRequest(graphQLRequestSoraWalletSbApyCase2())
        )
        return mapSoraWalletSbApyCase2(response)
    }
}
