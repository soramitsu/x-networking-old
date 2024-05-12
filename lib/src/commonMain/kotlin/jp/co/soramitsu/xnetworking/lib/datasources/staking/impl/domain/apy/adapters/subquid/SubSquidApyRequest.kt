package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.apy.adapters.subquid

import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLSerializableRequestWrapper
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.JsonPostRequest

@Suppress("FunctionName")
internal inline fun SubSquidApyRequest(
    url: String
) = JsonPostRequest(
    url = url,
    body = GraphQLSerializableRequestWrapper(
        """
        query MyQuery {
          stakers(
            where: {
              role_eq: "collator"
            }
          ) {
            stashId
            apr24h
          }
        }
        """.trimIndent()
    ),
)