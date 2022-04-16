package jp.co.soramitsu.commonnetworking.subquery

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

object SubQueryMapper {

    fun map(subQuerySbApyResponse: SubQuerySbApyResponse): List<SbApyInfo> {
        return subQuerySbApyResponse.data.poolXYKEntities.nodes.firstOrNull()?.pools?.edges?.map {
            SbApyInfo(
                tokenId = it.node.targetAssetId,
                priceUsd = it.node.priceUSD?.toDouble(),
                sbApy = it.node.strategicBonusApy?.toDouble(),
            )
        } ?: emptyList()
    }

    fun map(soraSubQueryResponse: SoraSubqueryResponse): SoraHistoryInfo {
        val elements = soraSubQueryResponse.data.historyElements
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
