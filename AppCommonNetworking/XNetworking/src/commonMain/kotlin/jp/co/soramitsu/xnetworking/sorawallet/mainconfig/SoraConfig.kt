package jp.co.soramitsu.xnetworking.sorawallet.mainconfig

data class SoraConfig(
    val blockExplorerUrl: String,
    val blockExplorerType: String,
    val nodes: List<SoraConfigNode>,
    val genesis: String,
    val joinUrl: String,
    val substrateTypesUrl: String,
    val currencies: List<SoraCurrency>,
)

data class SoraConfigNode(
    val chain: String,
    val name: String,
    val address: String,
)

data class SoraCurrency(
    val code: String,
    val name: String,
    val sign: String,
)
