package jp.co.soramitsu.xnetworking.subquery.factory

import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.subquery.SubQueryClient
import jp.co.soramitsu.xnetworking.subquery.history.SubQueryHistoryInfo
import jp.co.soramitsu.xnetworking.subquery.history.SubQueryHistoryItem
import kotlinx.serialization.DeserializationStrategy

expect class SubQueryClientFactory<T, R> {
    fun create(
        soramitsuNetworkClient: SoramitsuNetworkClient,
        baseUrl: String,
        pageSize: Int,
        deserializationStrategy: DeserializationStrategy<T>,
        jsonToHistoryInfo: (T) -> SubQueryHistoryInfo,
        historyIntoToResult: (SubQueryHistoryItem) -> R,
        historyRequest: String,
    ): SubQueryClient<T, R>
}
