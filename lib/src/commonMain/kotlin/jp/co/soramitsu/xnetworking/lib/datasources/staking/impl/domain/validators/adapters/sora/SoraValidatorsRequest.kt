package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.validators.adapters.sora

import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLSerializableRequestWrapper
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.JsonPostRequest

@Suppress("FunctionName")
internal inline fun SoraValidatorsRequest(
    url: String,
    accountAddress: String,
    eraFrom: String,
    eraTo: String
) = JsonPostRequest(
    url = url,
    body = GraphQLSerializableRequestWrapper(
        """
            query MyQuery {
              stakingEraNominators(
                where: {
                  AND: {
                    era: {
                      index_gte: $eraFrom, 
                      index_lte: $eraTo
                    }
                  }, 
                  staker: {
                    id_eq: "$accountAddress"
                  }
                }
              ) {
                nominations {
                  validator {
                    validator {
                      id
                    }
                  }
                }
              }
            }
        """.trimIndent()
    ),
)