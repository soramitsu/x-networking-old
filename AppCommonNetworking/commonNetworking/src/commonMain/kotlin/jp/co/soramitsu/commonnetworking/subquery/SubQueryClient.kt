package jp.co.soramitsu.commonnetworking.subquery

import io.ktor.http.*
import jp.co.soramitsu.commonnetworking.db.Extrinsics
import jp.co.soramitsu.commonnetworking.db.SignerInfo
import jp.co.soramitsu.commonnetworking.dbengine.HistoryDatabaseProvider
import jp.co.soramitsu.commonnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.commonnetworking.networkclient.SoramitsuNetworkException
import jp.co.soramitsu.commonnetworking.subquery.graphql.*
import jp.co.soramitsu.commonnetworking.subquery.history.HistoryMapper
import jp.co.soramitsu.commonnetworking.subquery.history.SubQueryHistoryInfo
import jp.co.soramitsu.commonnetworking.subquery.response.ReferrerRewardsResponse
import jp.co.soramitsu.commonnetworking.subquery.response.SubQuerySbApyResponse
import kotlinx.serialization.DeserializationStrategy
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.max
import kotlin.math.min

class SubQueryClient<T, R> internal constructor(
    private val networkClient: SoramitsuNetworkClient,
    private var baseUrl: String,
    private val pageSize: Int,
    private val deserializationStrategy: DeserializationStrategy<T>,
    private val jsonToHistoryInfo: (T) -> SubQueryHistoryInfo,
    private val historyInfoToResult: (SubQueryHistoryInfo) -> R,
    private val historyRequest: String,
    historyDatabaseProvider: HistoryDatabaseProvider
) {
    private val historyDatabase = historyDatabaseProvider.provide()

    fun clearAllData() {
        historyDatabase.clearDatabase()
    }

    fun clearData(address: String) {
        historyDatabase.clearAddressData(address)
    }

    @Throws(SoramitsuNetworkException::class, CancellationException::class)
    suspend fun getSpApy(
        url: String = baseUrl,
    ): List<SbApyInfo> {
        val response = networkClient.createJsonRequest<SubQuerySbApyResponse>(
            url,
            HttpMethod.Post,
            SubQueryRequest(sbApyGraphQLRequest())
        )
        return SubQueryMapper.map(response)
    }

    @Throws(SoramitsuNetworkException::class, CancellationException::class)
    suspend fun getReferrerRewards(
        address: String,
        url: String = baseUrl,
    ): ReferrerRewardsInfo {
        val response = networkClient.createJsonRequest<ReferrerRewardsResponse>(
            url,
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

    private var historyAddress: String = ""
    private lateinit var curSignerInfo: SignerInfo

    fun getTransactionPeers(query: String): List<String> =
        historyDatabase.getTransfersAddress(query)

    fun getTransactionHistoryCached(
        address: String,
    ): SubQueryHistoryInfo {
        val extrinsics = historyDatabase.getExtrinsics(address, 0, pageSize)
        return buildResultHistoryInfo(true, extrinsics)
    }

    fun getTransactionCached(
        address: String,
        txHash: String,
    ): SubQueryHistoryInfo {
        val extrinsic = historyDatabase.getExtrinsic(address, txHash)
        return buildResultHistoryInfo(
            true,
            if (extrinsic == null) emptyList() else listOf(extrinsic)
        )
    }

    @Throws(
        SoramitsuNetworkException::class,
        CancellationException::class,
        IllegalArgumentException::class
    )
    suspend fun getTransactionHistoryPaged(
        address: String,
        page: Long,
        url: String? = null,
    ): R {
        require(((page > 1 && historyAddress.isEmpty()) || page < 1).not()) { "First page value must = 1" }
        if (url != null) baseUrl = url
        if (page == 1L) {
            historyAddress = address
            curSignerInfo = historyDatabase.getSignerInfo(historyAddress)
            loadInfo()
        }
        return historyInfoToResult.invoke(getHistoryInfo(page))
    }

    private suspend fun getHistoryInfo(page: Long): SubQueryHistoryInfo {
        var dbOffset = (page - 1) * pageSize
        val extrinsics = historyDatabase.getExtrinsics(historyAddress, dbOffset, pageSize)
        dbOffset += extrinsics.size
        return if (extrinsics.size.toLong() >= pageSize) {
            buildResultHistoryInfo(false, extrinsics)
        } else {
            if (curSignerInfo.endReached) {
                buildResultHistoryInfo(true, extrinsics)
            } else {
                loadInfo(curSignerInfo.oldCursor.orEmpty())
                val moreCount = pageSize - extrinsics.size
                val moreExtrinsics =
                    historyDatabase.getExtrinsics(historyAddress, dbOffset, moreCount)
                dbOffset += moreExtrinsics.size
                buildResultHistoryInfo(curSignerInfo.endReached, extrinsics + moreExtrinsics)
            }
        }
    }

    private suspend fun loadInfo(cursor: String = "") {
        val request = SubQueryRequest(
            query = historyRequest,
            variables = soraHistoryGraphQLVariables(
                countRemote = pageSize,
                myAddress = historyAddress,
                cursor = cursor,
            )
        )
        val response = networkClient.createRequest<String>(
            baseUrl,
            HttpMethod.Post,
            request,
            ContentType.Application.Json
        )
        val decoded = networkClient.json.decodeFromString(deserializationStrategy, response)
        val infoDecoded = jsonToHistoryInfo.invoke(decoded)
        val info =
            historyDatabase.insertExtrinsics(historyAddress, infoDecoded)
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
    ): SubQueryHistoryInfo {
        val mapped = extrinsics.map { extrinsic ->
            if (extrinsic.batch.not()) {
                val params = historyDatabase.getExtrinsicParams(extrinsic.txHash)
                HistoryMapper.mapParams(extrinsic, params)
            } else {
                val nestedExtrinsics = historyDatabase.getExtrinsicNested(extrinsic.txHash)
                val nestedMapped = nestedExtrinsics.map {
                    val nestedParams = historyDatabase.getExtrinsicParams(it.txHash)
                    HistoryMapper.mapItemNested(it, nestedParams)
                }
                HistoryMapper.mapItems(extrinsic, nestedMapped)
            }
        }
        return SubQueryHistoryInfo("", endReached, mapped)
    }
}
