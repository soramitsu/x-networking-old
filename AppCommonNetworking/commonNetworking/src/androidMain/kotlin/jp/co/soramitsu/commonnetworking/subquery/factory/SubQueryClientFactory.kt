package jp.co.soramitsu.commonnetworking.subquery.factory

import android.content.Context
import jp.co.soramitsu.commonnetworking.dbengine.DatabaseDriverFactory
import jp.co.soramitsu.commonnetworking.dbengine.HistoryDatabaseProvider
import jp.co.soramitsu.commonnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.commonnetworking.subquery.SubQueryClient
import jp.co.soramitsu.commonnetworking.subquery.graphql.soraHistoryGraphQLRequest
import jp.co.soramitsu.commonnetworking.subquery.history.*
import jp.co.soramitsu.commonnetworking.subquery.history.sora.SoraSubqueryResponse
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

actual class SubQueryClientFactory<T, R>(private val context: Context) {
    actual fun create(
        soramitsuNetworkClient: SoramitsuNetworkClient,
        baseUrl: String,
        pageSize: Int,
        deserializationStrategy: DeserializationStrategy<T>,
        jsonToHistoryInfo: (T) -> SubQueryHistoryInfo,
        historyIntoToResult: (SubQueryHistoryInfo) -> R,
        historyRequest: String,
    ): SubQueryClient<T, R> {
        return SubQueryClient(
            soramitsuNetworkClient,
            baseUrl,
            pageSize,
            deserializationStrategy,
            jsonToHistoryInfo,
            historyIntoToResult,
            historyRequest,
            HistoryDatabaseProvider(DatabaseDriverFactory(context))
        )
    }
}

object SubQueryClientForSora {
    fun build(
        context: Context,
        soramitsuNetworkClient: SoramitsuNetworkClient,
        baseUrl: String,
        pageSize: Int,
    ): SubQueryClient<SoraSubqueryResponse, SubQueryHistoryInfo> {
        return SubQueryClientFactory<SoraSubqueryResponse, SubQueryHistoryInfo>(context).create(
            soramitsuNetworkClient = soramitsuNetworkClient,
            baseUrl = baseUrl,
            pageSize = pageSize,
            deserializationStrategy = SoraSubqueryResponse.serializer(),
            jsonToHistoryInfo = { response ->
                SubQueryHistoryInfo(
                    endCursor = response.data.historyElements.pageInfo.endCursor,
                    endReached = response.data.historyElements.pageInfo.hasNextPage.not(),
                    items = response.data.historyElements.nodes.map {
                        SubQueryHistoryItem(
                            id = it.id,
                            blockHash = it.blockHash,
                            module = it.module,
                            method = it.method,
                            timestamp = it.timestamp,
                            networkFee = it.networkFee,
                            success = it.execution.success,
                            data = (it.data as? JsonObject)?.let { json ->
                                json.map { mapItem ->
                                    SubQueryHistoryItemParam(
                                        paramName = mapItem.key,
                                        paramValue = (mapItem.value as? JsonPrimitive)?.content.orEmpty()
                                    )
                                }
                            },
                            nestedData = (it.data as? JsonArray)?.let { jsonArray ->
                                jsonArray.filterIsInstance<JsonObject>().map { jsonObject ->
                                    SubQueryHistoryItemNested(
                                        hash = (jsonObject["hash"] as? JsonPrimitive)?.content.orEmpty(),
                                        module = (jsonObject["module"] as? JsonPrimitive)?.content.orEmpty(),
                                        method = (jsonObject["method"] as? JsonPrimitive)?.content.orEmpty(),
                                        data = ((jsonObject["data"] as? JsonObject)?.get("args") as? JsonObject)?.map { mapItem ->
                                            SubQueryHistoryItemParam(
                                                paramName = mapItem.key,
                                                paramValue = (mapItem.value as? JsonPrimitive)?.content.orEmpty()
                                            )
                                        } ?: emptyList()
                                    )
                                }
                            },
                        )
                    },
                )
            },
            historyIntoToResult = { it },
            historyRequest = soraHistoryGraphQLRequest(),
        )
    }
}