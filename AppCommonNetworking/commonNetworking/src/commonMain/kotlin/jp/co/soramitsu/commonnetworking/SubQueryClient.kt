package jp.co.soramitsu.commonnetworking

import io.ktor.http.HttpMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.coroutines.cancellation.CancellationException

@Serializable
data class SubqueryRequest(
    val query: String,
    val variables: String? = null
)

@Serializable
data class SoraSubqueryResponse(
    val data: SoraSubqueryResponseData
)

@Serializable
data class SoraSubqueryResponseData(
    val historyElements: HistoryResponseDataElements
)

@Serializable
data class HistoryResponseDataElements(
    val nodes: List<HistoryResponseItem>, val pageInfo: HistoryResponsePageInfo
)

@Serializable
data class HistoryResponsePageInfo(
    val endCursor: String,
    val hasNextPage: Boolean,
)

@Serializable
data class HistoryResponseItem(
    val id: String,
    val blockHash: String,
    val module: String,
    val method: String,
    val timestamp: String,
    val networkFee: String,
    val execution: ExecutionResult,
    val data: JsonElement
)

@Serializable
data class ExecutionResult(
    val success: Boolean,
    val error: Error? = null
)

@Serializable
data class Error(
    val moduleErrorId: Int? = null,
    val moduleErrorIndex: Int? = null,
    val nonModuleErrorMessage: String? = null
)

data class SoraHistoryInfo(
    val endCursor: String,
    val hasNextPage: Boolean,
    val items: List<SoraHistoryItem>
)

data class SoraHistoryItem(
    val id: String,
    val blockHash: String,
    val module: String,
    val method: String,
    val timestamp: String,
    val networkFee: String,
    val success: Boolean,
    val data: List<SoraHistoryItemParam>?,
    val nestedData: List<SoraHistoryItemNested>?
)

data class SoraHistoryItemParam(
    val paramName: String,
    val paramValue: String,
)

data class SoraHistoryItemNested(
    val module: String,
    val method: String,
    val data: List<SoraHistoryItemParam>
)

data class SbApyInfo(
    val tokenId: String,
    val priceUsd: Double,
    val sbApy: Double,
)

class SubQueryClient(
    private val networkClient: SoraNetworkClient,
    private val baseUrl: String,
) {

    @Throws(SoraNetworkException::class, CancellationException::class)
    suspend fun getSpApy(): List<SbApyInfo> {
        val response = networkClient.createJsonRequest<SubQuerySbApyResponse>(
            baseUrl,
            HttpMethod.Post,
            SubqueryRequest(sbApyRequest())
        )
        return response.data.poolXYKEntities.nodes.firstOrNull()?.pools?.edges?.map {
            SbApyInfo(
                tokenId = it.node.targetAssetId,
                priceUsd = it.node.priceUSD.toDoubleOrNull() ?: 0.0,
                sbApy = it.node.strategicBonusApy.toDoubleOrNull() ?: 0.0,
            )
        } ?: emptyList()
    }

    @Throws(SoraNetworkException::class, CancellationException::class)
    suspend fun getTransactionHistory(
        amount: Int,
        address: String,
        cursor: String = "",
        assetId: String = ""
    ): SoraHistoryInfo {
        val response = networkClient.createJsonRequest<SoraSubqueryResponse>(
            baseUrl,
            HttpMethod.Post,
            SubqueryRequest(soraSubqueryRequest(amount, address, cursor, assetId))
        )
        val elements = response.data.historyElements
        return SoraHistoryInfo(
            elements.pageInfo.endCursor,
            elements.pageInfo.hasNextPage,
            elements.nodes.map { item ->
                SoraHistoryItem(
                    item.id,
                    item.blockHash,
                    item.module,
                    item.method,
                    item.timestamp,
                    item.networkFee,
                    item.execution.success,
                    if (item.data is JsonObject) {
                        item.data.map {
                            SoraHistoryItemParam(
                                it.key,
                                (it.value as JsonPrimitive).content
                            )
                        }
                    } else null,
                    if (item.data is JsonArray) {
                        item.data.filterIsInstance<JsonObject>().map { json ->
                            SoraHistoryItemNested(
                                (json["module"] as JsonPrimitive).content,
                                (json["method"] as JsonPrimitive).content,
                                ((json["data"] as JsonObject)["args"] as JsonObject).map {
                                    SoraHistoryItemParam(
                                        it.key,
                                        (it.value as JsonPrimitive).content
                                    )
                                }
                            )
                        }
                    } else null
                )
            }
        )
    }
}
