package jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.subquery

import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.TxFilter
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.GraphQLSerialiableRequestWrapper
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.JsonPostRequest
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.utils.GraphQLExts.and
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.utils.GraphQLExts.anyOf
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.utils.GraphQLExts.not
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.utils.GraphQLExts.or
import jp.co.soramitsu.xnetworking.core.engines.rest.api.RestClient

@Suppress("FunctionName")
internal fun SubQueryRequest(
    url: String,
    cursor: String?,
    pageCount: Int,
    address: String,
    filters: Set<TxFilter>,
    requestRewards: Boolean
) = JsonPostRequest(
    url = url,
    body = GraphQLSerialiableRequestWrapper(
        """
            query {
                historyElements(
                    after: ${if (cursor == null) null else "\"$cursor\""},
                    first: $pageCount,
                    orderBy: TIMESTAMP_DESC,
                    filter: { 
                        address:{ equalTo: "$address"},
                        ${filters.toQueryFilter()}
                    }
                ) {
                    pageInfo {
                        startCursor,
                        endCursor
                    },
                    nodes {
                        id
                        timestamp
                        address
                        ${if (requestRewards) "reward" else ""}
                        extrinsic
                        transfer
                    }
                }
            }
        """.trimIndent()
    )
)


/*
    or: [ {transfer: { notEqualTo: "null"} },  {extrinsic: { notEqualTo: "null"} } ]
 */
private fun Set<TxFilter>.toQueryFilter(): String {
    // optimize query in case all filters are on
    if (allFiltersIncluded()) {
        return ""
    }

    val typeExpressions = map {
        if (it == TxFilter.EXTRINSIC) {
            createExtrinsicExpression()
        } else {
            hasType(it.filterName)
        }
    }

    val result = anyOf(typeExpressions)

    return result
}

private fun createExtrinsicExpression(): String {
    val exists = hasType(TxFilter.EXTRINSIC.filterName)

    val restrictedModulesList = EXTRINSIC_RESTRICTIONS.map {
        val restrictedCallsExpressions = it.restrictedCalls.map(::callNamed)

        and(
            moduleNamed(it.moduleName),
            anyOf(restrictedCallsExpressions)
        )
    }

    val hasRestrictedModules = or(restrictedModulesList)

    return and(
        exists,
        not(hasRestrictedModules)
    )
}

private fun callNamed(callName: String) = "extrinsic: {contains: {call: \"$callName\"}}"
private fun moduleNamed(moduleName: String) = "extrinsic: {contains: {module: \"$moduleName\"}}"
private fun hasType(typeName: String) = "$typeName: {isNull: false}"

private val TxFilter.filterName
    get() = name.lowercase()

private fun Set<TxFilter>.allFiltersIncluded(): Boolean {
    return size == TxFilter.values().size
}

val requestContentType: RestClient.ContentType = RestClient.ContentType.JSON

private val EXTRINSIC_RESTRICTIONS = listOf(
    ModuleRestriction(
        moduleName = "balances",
        restrictedCalls = listOf(
            "transfer",
            "transferKeepAlive",
            "forceTransfer"
        )
    )
)

private class ModuleRestriction(
    val moduleName: String,
    val restrictedCalls: List<String>
)