package jp.co.soramitsu.xnetworking.sorawallet.mainconfig

import jp.co.soramitsu.xnetworking.common.platform
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkException
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.coroutines.cancellation.CancellationException

class SoraRemoteConfigBuilder(
    private val client: SoramitsuNetworkClient,
    private val commonUrl: String,
    private val mobileUrl: String,
) {

    @Throws(SoramitsuNetworkException::class, CancellationException::class)
    suspend fun getConfig(): SoraConfig {
        val commonDto: ConfigDto = client.createJsonRequest(commonUrl)
        val mobile: MobileDto = client.createJsonRequest(mobileUrl)
        return SoraConfig(
            blockExplorerUrl = commonDto.subquery,
            blockExplorerType = mobile.explorerType,
            nodes = commonDto.nodes.map {
                SoraConfigNode(
                    chain = it.chain,
                    name = it.name,
                    address = it.address,
                )
            },
            genesis = commonDto.genesis,
            joinUrl = mobile.joinLink,
            substrateTypesUrl = if (platform() == "android") mobile.substrateTypesAndroid else mobile.substrateTypesIos,
            currencies = mobile.currencies.map {
                SoraCurrency(
                    code = it.code,
                    name = it.name,
                    sign = it.sign,
                )
            }
        )
    }
}

@Serializable
private data class ConfigDto(
    @SerialName("SUBQUERY_ENDPOINT")
    val subquery: String,
    @SerialName("DEFAULT_NETWORKS")
    val nodes: List<NodeInfo>,
    @SerialName("CHAIN_GENESIS_HASH")
    val genesis: String,
)

@Serializable
private data class NodeInfo(
    @SerialName("chain")
    val chain: String,
    @SerialName("name")
    val name: String,
    @SerialName("address")
    val address: String,
)

@Serializable
private data class MobileDto(
    @SerialName("explorer_type")
    val explorerType: String,
    @SerialName("join_link")
    val joinLink: String,
    @SerialName("substrate_types_android")
    val substrateTypesAndroid: String,
    @SerialName("substrate_types_ios")
    val substrateTypesIos: String,
    @SerialName("currencies")
    val currencies: List<CurrencyDto>,
)

@Serializable
private data class CurrencyDto(
    @SerialName("code")
    val code: String,
    @SerialName("name")
    val name: String,
    @SerialName("sign")
    val sign: String,
)
