package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquery

import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLSerializableRequestWrapper
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.JsonPostRequest

@Suppress("FunctionName")
internal inline fun SubQueryApyRequest(
    url: String,
    collatorIds: List<String>?,
    roundId: Int?
) = JsonPostRequest(
    url = url,
    body = GraphQLSerializableRequestWrapper(
        """
        query {
            collatorRounds(
                filter: {
                    ${
                        collatorIds?.let { ids ->
                            "collatorId: { inInsensitive: ${ids.map { "\"" + it + "\"" }} }"
                        } ?: ""
                    }
                    apr: { 
                        isNull: false, 
                        greaterThan: 0 
                    }
                    roundId: { 
                        equalTo: "$roundId" 
                    }
                }
            ) {
                nodes {
                    collatorId
                    apr
                }
            }
        }
        """.trimIndent()
    ),
)

@Suppress("FunctionName")
internal inline fun SubQueryLastRoundRequest(
    url: String
) = JsonPostRequest(
    url = url,
    body = GraphQLSerializableRequestWrapper(
        """
            query {
              rounds(last: 1) {
                nodes {
                  id
                }
              }
            }
        """.trimIndent()
    ),
)