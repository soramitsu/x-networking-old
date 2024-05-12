package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.validators.adapters.subquery

import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLSerializableRequestWrapper
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.JsonPostRequest

@Suppress("FunctionName")
internal inline fun SubQueryValidatorsRequest(
    url: String,
    accountAddress: String,
    eraFrom: String,
    eraTo: String
) = JsonPostRequest(
    url = url,
    body = GraphQLSerializableRequestWrapper(
        """
            {
                query {
                    eraValidatorInfos(
                        filter: {
                            era: { 
                                greaterThanOrEqualTo: $eraFrom, 
                                lessThanOrEqualTo: $eraTo
                            },
                            others: { 
                                contains:[
                                    {
                                        who: "$accountAddress"
                                    }
                                ]
                            }
                        }
                    ) {
                        nodes {
                            id
                            address
                            era
                            total
                            own
                        }
                    }
                }
            }
        """.trimIndent()
    ),
)