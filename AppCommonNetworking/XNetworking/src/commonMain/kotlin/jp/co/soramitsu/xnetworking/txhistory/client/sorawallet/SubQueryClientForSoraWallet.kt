package jp.co.soramitsu.xnetworking.txhistory.client.sorawallet

import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.txhistory.TxHistoryItem
import jp.co.soramitsu.xnetworking.txhistory.client.SubQueryClient
import jp.co.soramitsu.xnetworking.txhistory.subquery.graphqlrequest.soraHistoryGraphQLRequest
import jp.co.soramitsu.xnetworking.txhistory.subquery.response.sora.SoraSubQueryResponse
import jp.co.soramitsu.xnetworking.txhistory.subquery.response.sora.SoraWalletSubQueryHistoryMapper

class SubQueryClientForSoraWallet(
    private val networkClient: SoramitsuNetworkClient,
    private var baseUrl: String,
    private val pageSize: Int,
) {

    private val client: SubQueryClient<SoraSubQueryResponse, TxHistoryItem> =
        SubQueryClient(
            networkClient = networkClient,
            baseUrl = baseUrl,
            pageSize = pageSize,
            deserializationStrategy = SoraSubQueryResponse.serializer(),
            jsonToHistoryInfo = { response -> SoraWalletSubQueryHistoryMapper.map(response) },
            historyInfoToResult = { it },
            historyRequest = soraHistoryGraphQLRequest(),
        )

}