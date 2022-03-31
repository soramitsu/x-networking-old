package jp.co.soramitsu.commonnetworking

import kotlinx.serialization.Serializable
import kotlin.coroutines.cancellation.CancellationException

internal expect fun platform(): String

internal expect fun version(): String

@Serializable
data class ChainNode(
    val url: String,
    val name: String,
)

@Serializable
data class ChainModel(
    val chainId: String,
    val name: String,
    val nodes: List<ChainNode>,
)

class FearlessChainsBuilder(
    private val networkClient: SoraNetworkClient,
    private val baseUrl: String,
) {

    companion object {
        private const val INDEX_URL = "index.json"
    }

    @Throws(SoraNetworkException::class, CancellationException::class)
    suspend fun getChains(version: String): List<ChainModel> {
        val indexResponse = networkClient.createJsonRequest<IndexResponse>(baseUrl + INDEX_URL)
        val platform =
            indexResponse.platformVersions.find { it.platform == platform() } ?: return emptyList()
        val curVersion = version.split(".").map { it.toInt() }
        val selectedVersion = platform.versions.find { v ->
            v.v.split(".").map { it.toInt() }.match(curVersion)
        } ?: return emptyList()
        val chainsResponse =
            networkClient.createJsonRequest<List<ChainResponse>>(baseUrl + selectedVersion.path)
        return chainsResponse.map {
            networkClient.createJsonRequest<ChainModel>(baseUrl + it.chain)
        }
    }

    private fun List<Int>.match(cur: List<Int>): Boolean {
        var i = 0
        while (i < this.size && i < cur.size) {
            if (this[i] > cur[i]) return false
            i++
        }
        return true
    }
}

@Serializable
data class IndexResponse(
    val platformVersions: List<PlatformVersion>,
)

@Serializable
data class PlatformVersion(
    val platform: String,
    val versions: List<Version>,
)

@Serializable
data class Version(
    val v: String,
    val path: String,
)

@Serializable
data class ChainResponse(
    val chain: String,
    val hash: String,
)
