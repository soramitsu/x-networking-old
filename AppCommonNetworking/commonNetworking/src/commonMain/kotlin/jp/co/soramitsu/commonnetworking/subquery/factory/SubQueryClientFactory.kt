package jp.co.soramitsu.commonnetworking.subquery.factory

import jp.co.soramitsu.commonnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.commonnetworking.subquery.SubQueryClient

expect class SubQueryClientFactory {
    fun create(soramitsuNetworkClient: SoramitsuNetworkClient, baseUrl: String, pageSize: Int): SubQueryClient
}