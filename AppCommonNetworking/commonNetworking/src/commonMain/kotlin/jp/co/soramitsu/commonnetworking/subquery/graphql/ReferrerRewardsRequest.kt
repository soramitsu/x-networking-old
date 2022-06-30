package jp.co.soramitsu.commonnetworking.subquery.graphql

internal fun referrerRewardsGraphQLRequest(
    address: String,
) = """
    query {
        referrerRewards(
          filter: { referrer: {equalTo: "$address"} }
        ) {
            groupedAggregates(groupBy: REFERRAL) {
                keys sum {amount}
            }
        }
    }
""".trimIndent()
