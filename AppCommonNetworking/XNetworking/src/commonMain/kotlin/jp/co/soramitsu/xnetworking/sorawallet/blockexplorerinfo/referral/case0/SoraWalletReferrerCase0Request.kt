package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral.case0

internal fun graphQLRequestSoraWalletReferrerCase0(
    address: String,
) = """
    query {
        referrerRewards(
          filter: { referrer: {equalTo: "$address"} }
        ) {
            groupedAggregates(groupBy:[REFERRAL]){keys sum {amount}}
        }
    }
""".trimIndent()
