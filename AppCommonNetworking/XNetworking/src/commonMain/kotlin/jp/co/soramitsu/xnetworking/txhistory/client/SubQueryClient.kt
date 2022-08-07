package jp.co.soramitsu.xnetworking.txhistory.client

import io.ktor.http.*
import jp.co.soramitsu.xnetworking.db.Extrinsics
import jp.co.soramitsu.xnetworking.db.SignerInfo
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkException
import jp.co.soramitsu.xnetworking.txhistory.subquery.graphqlrequest.SubQueryRequest
import jp.co.soramitsu.xnetworking.txhistory.subquery.graphqlrequest.soraHistoryGraphQLVariables
import jp.co.soramitsu.xnetworking.txhistory.HistoryMapper
import jp.co.soramitsu.xnetworking.txhistory.TxHistoryInfo
import jp.co.soramitsu.xnetworking.txhistory.TxHistoryItem
import jp.co.soramitsu.xnetworking.txhistory.TxHistoryResult
import jp.co.soramitsu.xnetworking.txhistory.HistoryDatabaseProvider
import kotlinx.serialization.DeserializationStrategy
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.max
import kotlin.math.min

class SubQueryClient<T, R> internal constructor(
    private val networkClient: SoramitsuNetworkClient,
    private var baseUrl: String,
    private val pageSize: Int,
    private val deserializationStrategy: DeserializationStrategy<T>,
    private val jsonToHistoryInfo: (T) -> TxHistoryInfo,
    private val historyInfoToResult: (TxHistoryItem) -> R,
    private val historyRequest: String,
    historyDatabaseProvider: HistoryDatabaseProvider
) {
    private val historyDatabase = historyDatabaseProvider.provide()

    fun clearAllData() {
        historyDatabase.clearDatabase()
    }

    fun clearData(address: String, networkName: String) {
        historyDatabase.clearAddressData(address, networkName)
    }

    fun getTransactionPeers(query: String, networkName: String): List<String> =
        historyDatabase.getTransfersAddress(query, networkName)

    fun getTransactionHistoryCached(
        address: String,
        networkName: String,
    ): TxHistoryInfo {
        val extrinsics = historyDatabase.getExtrinsics(address, networkName, 0, pageSize)
        return buildResultHistoryInfo(true, extrinsics)
    }

    fun getTransactionCached(
        address: String,
        networkName: String,
        txHash: String,
    ): TxHistoryInfo {
        val extrinsic = historyDatabase.getExtrinsic(address, networkName, txHash)
        return buildResultHistoryInfo(
            true,
            if (extrinsic == null) emptyList() else listOf(extrinsic)
        )
    }

    private lateinit var curSignerInfo: SignerInfo

    @Throws(
        SoramitsuNetworkException::class,
        CancellationException::class,
        IllegalArgumentException::class
    )
    suspend fun getTransactionHistoryPaged(
        address: String,
        networkName: String,
        page: Long,
        url: String? = null,
        filter: ((R) -> Boolean)? = null
    ): TxHistoryResult<R> {
        require(page >= 1) { "Page value must >= 1" }
        if (url != null) baseUrl = url
        curSignerInfo = historyDatabase.getSignerInfo(address, networkName)
        if (page == 1L) {
            loadInfo(address = address, networkName = networkName)
        }
        var curPage = page
        val list = mutableListOf<R>()
        var endCursor: String?
        var endReached: Boolean
        while (true) {
            val info = getHistoryInfo(curPage, address, networkName)
            endCursor = info.endCursor
            endReached = info.endReached
            val itemsMapped = info.items.map(historyInfoToResult)
            val itemsFiltered = if (filter != null) itemsMapped.filter {
                filter.invoke(it)
            } else itemsMapped
            list.addAll(itemsFiltered)
            if (list.size >= pageSize || info.endReached) {
                break
            } else {
                curPage++
            }
        }
        return TxHistoryResult(
            endCursor = endCursor,
            endReached = endReached,
            page = curPage,
            items = list
        )
    }

    private suspend fun getHistoryInfo(
        page: Long,
        address: String,
        networkName: String,
    ): TxHistoryInfo {
        var dbOffset = (page - 1) * pageSize
        val extrinsics = historyDatabase.getExtrinsics(address, networkName, dbOffset, pageSize)
        dbOffset += extrinsics.size
        return if (extrinsics.size.toLong() >= pageSize) {
            buildResultHistoryInfo(false, extrinsics)
        } else {
            if (curSignerInfo.endReached) {
                buildResultHistoryInfo(true, extrinsics)
            } else {
                loadInfo(curSignerInfo.oldCursor.orEmpty(), address, networkName)
                val moreCount = pageSize - extrinsics.size
                val moreExtrinsics =
                    historyDatabase.getExtrinsics(address, networkName, dbOffset, moreCount)
                dbOffset += moreExtrinsics.size
                buildResultHistoryInfo(curSignerInfo.endReached, extrinsics + moreExtrinsics)
            }
        }
    }

    private suspend fun loadInfo(cursor: String = "", address: String, networkName: String) {
        val request = SubQueryRequest(
            query = historyRequest,
            variables = soraHistoryGraphQLVariables(
                countRemote = pageSize,
                myAddress = address,
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
            historyDatabase.insertExtrinsics(address, networkName, infoDecoded)
        curSignerInfo = SignerInfo(
            signAddress = address,
            networkName = networkName,
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
    ): TxHistoryInfo {
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
        return TxHistoryInfo("", endReached, mapped)
    }
}
