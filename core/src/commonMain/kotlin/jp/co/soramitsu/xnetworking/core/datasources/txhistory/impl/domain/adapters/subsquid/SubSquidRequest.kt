package jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.subsquid

import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.GraphQLSerialiableRequestWrapper
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.JsonPostRequest


@Suppress("FunctionName")
inline fun SubSquidRequest(
    url: String,
    address: String,
    cursor: String? = null,
    limit: Int
) = JsonPostRequest(
    url = url,
    body = GraphQLSerialiableRequestWrapper(
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