package jp.co.soramitsu.commonnetworking.subquery

import io.ktor.http.HttpMethod
import jp.co.soramitsu.commonnetworking.db.Extrinsics
import jp.co.soramitsu.commonnetworking.db.SignerInfo
import jp.co.soramitsu.commonnetworking.dbengine.HistoryDatabaseProvider
import jp.co.soramitsu.commonnetworking.networkclient.SoraNetworkClient
import jp.co.soramitsu.commonnetworking.networkclient.SoraNetworkException
import jp.co.soramitsu.commonnetworking.subquery.graphql.SubQueryRequest
import jp.co.soramitsu.commonnetworking.subquery.graphql.referrerRewardsGraphQLRequest
import jp.co.soramitsu.commonnetworking.subquery.graphql.sbApyGraphQLRequest
import jp.co.soramitsu.commonnetworking.subquery.graphql.soraHistoryGraphQLRequest
import jp.co.soramitsu.commonnetworking.subquery.history.HistoryMapper
import jp.co.soramitsu.commonnetworking.subquery.history.SoraHistoryInfo
import jp.co.soramitsu.commonnetworking.subquery.history.SoraSubqueryResponse
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.max
import kotlin.math.min

class SubQueryClient internal constructor(
    private val networkClient: SoraNetworkClient,
    private val baseUrl: String,
    historyDatabaseProvider: HistoryDatabaseProvider
) {
    private val historyDatabase = historyDatabaseProvider.provide()

    fun clearAllData() {
        historyDatabase.clearDatabase()
    }

    fun clearData(address: String) {
        historyDatabase.clearAddressData(address)
    }

    @Throws(SoraNetworkException::class, CancellationException::class)
    suspend fun getSpApy(): List<SbApyInfo> {
        val response = networkClient.createJsonRequest<SubQuerySbApyResponse>(
            baseUrl,
            HttpMethod.Post,
            SubQueryRequest(sbApyGraphQLRequest())
        )
        return SubQueryMapper.map(response)
    }

    @Throws(SoraNetworkException::class, CancellationException::class)
    suspend fun getReferrerRewards(
        address: String,
    ): ReferrerRewardsInfo {
        val response = networkClient.createJsonRequest<ReferrerRewardsResponse>(
            baseUrl,
            HttpMethod.Post,
            SubQueryRequest(referrerRewardsGraphQLRequest(address))
        )
        return if (response.data.referrerRewards.groupedAggregates.isEmpty()) {
            ReferrerRewardsInfo(rewards = emptyList())
        } else {
            ReferrerRewardsInfo(rewards = response.data.referrerRewards.groupedAggregates.map {
                ReferrerRewards(
                    referral = it.keys.firstOrNull().orEmpty(),
                    amount = it.sum.amount
                )
            })
        }
    }

    private var historyPageSize: Long = 40
    private var historyAddress: String = ""
    private var dbOffset: Long = 0
    private lateinit var curSignerInfo: SignerInfo

    @Throws(SoraNetworkException::class, CancellationException::class)
    suspend fun initTransactionHistory(
        pageSize: Long,
        address: String,
    ): SoraHistoryInfo {
        historyPageSize = pageSize
        historyAddress = address
        dbOffset = 0
        curSignerInfo = historyDatabase.getSignerInfo(historyAddress)
        loadInfo()
        return getHistoryInfo(historyPageSize)
    }

    @Throws(SoraNetworkException::class, CancellationException::class)
    suspend fun getNextPageOfTransactionHistory(): SoraHistoryInfo {
        return getHistoryInfo(historyPageSize)
    }

    private suspend fun getHistoryInfo(count: Long): SoraHistoryInfo {
        val extrinsics = historyDatabase.getExtrinsics(historyAddress, dbOffset, count)
        println("foxxx gethistoryinfo $count curoffset $dbOffset got ${extrinsics.size}")
        dbOffset += extrinsics.size
        return if (extrinsics.size.toLong() >= count) {
            buildResultHistoryInfo(false, extrinsics)
        } else {
            println("foxxx sig $curSignerInfo")
            if (curSignerInfo.endReached) {
                buildResultHistoryInfo(true, extrinsics)
            } else {
                loadInfo(curSignerInfo.oldCursor.orEmpty())
                val moreCount = count - extrinsics.size
                println("foxxx more $moreCount")
                val moreExtrinsics =
                    historyDatabase.getExtrinsics(historyAddress, dbOffset, moreCount)
                dbOffset += moreExtrinsics.size
                println("foxxx loaded more ${moreExtrinsics.size} offset=$dbOffset")
                buildResultHistoryInfo(curSignerInfo.endReached, extrinsics + moreExtrinsics)
            }
        }
    }

    private suspend fun loadInfo(cursor: String = "") {
        val response = networkClient.createJsonRequest<SoraSubqueryResponse>(
            baseUrl,
            HttpMethod.Post,
            SubQueryRequest(
                soraHistoryGraphQLRequest(
                    historyPageSize,
                    historyAddress,
                    cursor
                )
            )
        )
        val info = historyDatabase.insertExtrinsics(historyAddress, response)
        curSignerInfo = SignerInfo(
            signAddress = historyAddress,
            topTime = max(info.topTime, curSignerInfo.topTime),
            oldTime = if (curSignerInfo.oldTime == 0L) info.oldTime else min(
                info.oldTime,
                curSignerInfo.oldTime
            ),
            endReached = info.endReached || curSignerInfo.endReached,
            oldCursor = if (info.endReached || curSignerInfo.oldCursor.isNullOrEmpty() || info.oldTime < curSignerInfo.oldTime) info.oldCursor else curSignerInfo.oldCursor
        )
        historyDatabase.insertSignerInfo(curSignerInfo)
    }

    private fun buildResultHistoryInfo(
        endReached: Boolean,
        extrinsics: List<Extrinsics>
    ): SoraHistoryInfo {
        val mapped = extrinsics.map { extrinsic ->
            if (extrinsic.batch.not()) {
                val params = historyDatabase.getExtrinsicParams(extrinsic.txHash)
                HistoryMapper.map(extrinsic, params)
            } else {
                val nestedExtrinsics = historyDatabase.getExtrinsicNested(extrinsic.txHash)
                val nestedMapped = nestedExtrinsics.map {
                    val nestedParams = historyDatabase.getExtrinsicParams(it.txHash)
                    HistoryMapper.mapItemNested(it, nestedParams)
                }
                HistoryMapper.map(extrinsic, nestedMapped)
            }
        }
        return SoraHistoryInfo(endReached, mapped)
    }
}
