package jp.co.soramitsu.commonnetworking.subquery.factory

import android.content.Context
import jp.co.soramitsu.commonnetworking.dbengine.DatabaseDriverFactory
import jp.co.soramitsu.commonnetworking.dbengine.HistoryDatabaseProvider
import jp.co.soramitsu.commonnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.commonnetworking.subquery.SubQueryClient
import jp.co.soramitsu.commonnetworking.subquery.history.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

actual class SubQueryClientFactory<T>(private val context: Context) {
    actual fun create(
        soramitsuNetworkClient: SoramitsuNetworkClient,
        baseUrl: String,
        pageSize: Int,
        str: DeserializationStrategy<T>,
        to: (T) -> SubQueryHistoryInfo,
    ): SubQueryClient<T> {
        return SubQueryClient(
            soramitsuNetworkClient,
            baseUrl,
            pageSize,
            str,
            to,
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
    ): SubQueryClient<SoraSubqueryResponse> {
        return SubQueryClientFactory<SoraSubqueryResponse>(context).create(
            soramitsuNetworkClient = soramitsuNetworkClient,
            baseUrl = baseUrl,
            pageSize = pageSize,
            SoraSubqueryResponse.serializer(),
            to = { response ->
                SubQueryHistoryInfo(
                    pageInfo = SubQueryPageInfo(
                        endCursor = response.data.historyElements.pageInfo.endCursor,
                        endReached = response.data.historyElements.pageInfo.hasNextPage.not(),
                    ),
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
            }
        )
    }
}