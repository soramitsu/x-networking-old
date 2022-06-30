package jp.co.soramitsu.commonnetworking.subquery.factory

import jp.co.soramitsu.commonnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.commonnetworking.subquery.SubQueryClient
import jp.co.soramitsu.commonnetworking.subquery.history.SubQueryHistoryInfo
import kotlinx.serialization.DeserializationStrategy

expect class SubQueryClientFactory<T> {
    fun create(
        soramitsuNetworkClient: SoramitsuNetworkClient,
        baseUrl: String,
        pageSize: Int,
        str: DeserializationStrategy<T>,
        to: (T) -> SubQueryHistoryInfo,
    ): SubQueryClient<T>
}
