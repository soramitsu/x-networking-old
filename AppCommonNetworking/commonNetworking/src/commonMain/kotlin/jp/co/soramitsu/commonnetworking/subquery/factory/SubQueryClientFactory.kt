package jp.co.soramitsu.commonnetworking.subquery.factory

import jp.co.soramitsu.commonnetworking.networkclient.SoraNetworkClient
import jp.co.soramitsu.commonnetworking.subquery.SubQueryClient

expect class SubQueryClientFactory {
    fun create(soraNetworkClient: SoraNetworkClient, baseUrl: String): SubQueryClient
}