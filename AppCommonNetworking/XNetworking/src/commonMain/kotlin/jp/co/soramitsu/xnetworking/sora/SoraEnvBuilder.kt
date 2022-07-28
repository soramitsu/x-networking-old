package jp.co.soramitsu.xnetworking.sora

import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.sora.model.SoraEnv

class SoraEnvBuilder(
    private val client: SoramitsuNetworkClient,
    private val baseUrl: String
) {

    suspend fun getSoraEnv(): SoraEnv =
        client.createJsonRequest(baseUrl)
}
