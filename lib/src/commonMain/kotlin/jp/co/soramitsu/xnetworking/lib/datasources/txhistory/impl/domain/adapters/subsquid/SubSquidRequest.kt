package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.subsquid

import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLSerializableRequestWrapper
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.JsonPostRequest

@Suppress("FunctionName")
internal inline fun SubSquidRequest(
    url: String,
    address: String,
    cursor: String? = null,
    limit: Int
) = JsonPostRequest(
    url = url,
    body = GraphQLSerializableRequestWrapper(
        """
            query MyQuery {
              historyElementsConnection(
                where: {
                  address_eq: "$address"
                }, 
                orderBy: timestamp_DESC, 
                first: $limit, 
                after: "$cursor"
              ) {
                pageInfo {
                  hasNextPage
                  endCursor
                }
                edges {
                  node {
                    timestamp
                    id
                    extrinsicIdx
                    extrinsicHash
                    address
                    success
                    transfer {
                      amount
                      fee
                      from
                      to
                    }
                    reward {
                      amount
                      era
                      stash
                    }
                  }
                }
              }
            }
        """.trimIndent()
    ),
)