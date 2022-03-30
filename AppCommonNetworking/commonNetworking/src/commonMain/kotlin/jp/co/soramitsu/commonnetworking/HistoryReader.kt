package jp.co.soramitsu.commonnetworking

import io.ktor.http.HttpMethod
import kotlinx.serialization.Serializable
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
    val data: List<SoraHistoryItemParam>,
)

data class SoraHistoryItemParam(
    val paramName: String,
    val paramValue: String,
)

class HistoryReader(
    private val networkClient: SoraNetworkClient,
    private val baseUrl: String,
) {
    @Throws(SoraNetworkException::class, CancellationException::class)
    suspend fun getSoraSubQuery(
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
                    (item.data as JsonObject).map {
                        SoraHistoryItemParam(
                            it.key,
                            (it.value as JsonPrimitive).content
                        )
                    }
                )
            }
        )
    }
}
