package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.unbonding.adapters.subquery

import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLSerializableRequestWrapper
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.JsonPostRequest

@Suppress("FunctionName")
internal inline fun SubQueryUnbondingRequest(
    url: String,
    delegatorAddress: String,
    collatorAddress: String
) = JsonPostRequest(
    url = url,
    body = GraphQLSerializableRequestWrapper(
        """
        query {
            delegatorHistoryElements(
              orderBy: TIMESTAMP_DESC,
              last: 10,
              filter: {
                delegatorId: { 
                  equalToInsensitive: "$delegatorAddress}"
                },
                collatorId: { 
                  equalToInsensitive: "$collatorAddress"
                } 
              }
            ) {
                nodes {
                  id
                  blockNumber
                  delegatorId
                  collatorId
                  timestamp
                  type
                  roundId
                  amount
                }
            }
        }
        """.trimIndent()
    ),
)