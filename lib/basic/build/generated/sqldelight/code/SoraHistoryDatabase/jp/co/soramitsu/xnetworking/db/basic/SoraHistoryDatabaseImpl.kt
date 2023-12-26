package jp.co.soramitsu.xnetworking.db.basic

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import jp.co.soramitsu.xnetworking.db.ExtrinsicParam
import jp.co.soramitsu.xnetworking.db.Extrinsics
import jp.co.soramitsu.xnetworking.db.SignerInfo
import jp.co.soramitsu.xnetworking.db.SoraHistoryDatabase
import jp.co.soramitsu.xnetworking.db.SoraHistoryDatabaseQueries
import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<SoraHistoryDatabase>.schema: SqlDriver.Schema
  get() = SoraHistoryDatabaseImpl.Schema

internal fun KClass<SoraHistoryDatabase>.newInstance(driver: SqlDriver): SoraHistoryDatabase =
    SoraHistoryDatabaseImpl(driver)

private class SoraHistoryDatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), SoraHistoryDatabase {
  public override val soraHistoryDatabaseQueries: SoraHistoryDatabaseQueriesImpl =
      SoraHistoryDatabaseQueriesImpl(this, driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 2

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE TABLE Extrinsics (
          |    txHash TEXT NOT NULL PRIMARY KEY,
          |    signAddress TEXT NOT NULL,
          |    blockHash TEXT,
          |    module TEXT NOT NULL,
          |    method TEXT NOT NULL,
          |    networkFee TEXT NOT NULL,
          |    timestamp INTEGER NOT NULL DEFAULT 0,
          |    success INTEGER NOT NULL DEFAULT 0,
          |    batch INTEGER NOT NULL DEFAULT 0,
          |    parentHash TEXT,
          |    networkName TEXT NOT NULL DEFAULT ""
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE ExtrinsicParam (
          |    extrinsicHash TEXT NOT NULL,
          |    paramName TEXT NOT NULL,
          |    paramValue TEXT NOT NULL,
          |    PRIMARY KEY (extrinsicHash, paramName),
          |    FOREIGN KEY(extrinsicHash) REFERENCES Extrinsics(txHash) ON UPDATE NO ACTION ON DELETE CASCADE
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE SignerInfo (
          |    signAddress TEXT NOT NULL PRIMARY KEY,
          |    topTime INTEGER NOT NULL DEFAULT 0,
          |    oldTime INTEGER NOT NULL DEFAULT 0,
          |    oldCursor TEXT,
          |    endReached INTEGER NOT NULL DEFAULT 0,
          |    networkName TEXT NOT NULL DEFAULT ""
          |)
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
      if (oldVersion <= 1 && newVersion > 1) {
        driver.execute(null,
            "ALTER TABLE Extrinsics ADD COLUMN networkName TEXT NOT NULL DEFAULT \"\"", 0)
        driver.execute(null,
            "ALTER TABLE SignerInfo ADD COLUMN networkName TEXT NOT NULL DEFAULT \"\"", 0)
      }
    }
  }
}

private class SoraHistoryDatabaseQueriesImpl(
  private val database: SoraHistoryDatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), SoraHistoryDatabaseQueries {
  internal val selectExtrinsicsPaged: MutableList<Query<*>> = copyOnWriteList()

  internal val selectExtrinsicsNested: MutableList<Query<*>> = copyOnWriteList()

  internal val selectExtrinsic: MutableList<Query<*>> = copyOnWriteList()

  internal val selectExtrinsicParams: MutableList<Query<*>> = copyOnWriteList()

  internal val selectTransfersPeers: MutableList<Query<*>> = copyOnWriteList()

  internal val selectSignerInfo: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> selectExtrinsicsPaged(
    address: String,
    network: String,
    limit: Long,
    offset: Long,
    mapper: (
      txHash: String,
      signAddress: String,
      blockHash: String?,
      module: String,
      method: String,
      networkFee: String,
      timestamp: Long,
      success: Boolean,
      batch: Boolean,
      parentHash: String?,
      networkName: String
    ) -> T
  ): Query<T> = SelectExtrinsicsPagedQuery(address, network, limit, offset) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2),
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getLong(6)!!,
      cursor.getLong(7)!! == 1L,
      cursor.getLong(8)!! == 1L,
      cursor.getString(9),
      cursor.getString(10)!!
    )
  }

  public override fun selectExtrinsicsPaged(
    address: String,
    network: String,
    limit: Long,
    offset: Long
  ): Query<Extrinsics> = selectExtrinsicsPaged(address, network, limit, offset) { txHash,
      signAddress, blockHash, module, method, networkFee, timestamp, success, batch, parentHash,
      networkName ->
    Extrinsics(
      txHash,
      signAddress,
      blockHash,
      module,
      method,
      networkFee,
      timestamp,
      success,
      batch,
      parentHash,
      networkName
    )
  }

  public override fun <T : Any> selectExtrinsicsNested(parentHash: String?, mapper: (
    txHash: String,
    signAddress: String,
    blockHash: String?,
    module: String,
    method: String,
    networkFee: String,
    timestamp: Long,
    success: Boolean,
    batch: Boolean,
    parentHash: String?,
    networkName: String
  ) -> T): Query<T> = SelectExtrinsicsNestedQuery(parentHash) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2),
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getLong(6)!!,
      cursor.getLong(7)!! == 1L,
      cursor.getLong(8)!! == 1L,
      cursor.getString(9),
      cursor.getString(10)!!
    )
  }

  public override fun selectExtrinsicsNested(parentHash: String?): Query<Extrinsics> =
      selectExtrinsicsNested(parentHash) { txHash, signAddress, blockHash, module, method,
      networkFee, timestamp, success, batch, parentHash_, networkName ->
    Extrinsics(
      txHash,
      signAddress,
      blockHash,
      module,
      method,
      networkFee,
      timestamp,
      success,
      batch,
      parentHash_,
      networkName
    )
  }

  public override fun <T : Any> selectExtrinsic(
    hash: String,
    address: String,
    network: String,
    mapper: (
      txHash: String,
      signAddress: String,
      blockHash: String?,
      module: String,
      method: String,
      networkFee: String,
      timestamp: Long,
      success: Boolean,
      batch: Boolean,
      parentHash: String?,
      networkName: String
    ) -> T
  ): Query<T> = SelectExtrinsicQuery(hash, address, network) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2),
      cursor.getString(3)!!,
      cursor.getString(4)!!,
      cursor.getString(5)!!,
      cursor.getLong(6)!!,
      cursor.getLong(7)!! == 1L,
      cursor.getLong(8)!! == 1L,
      cursor.getString(9),
      cursor.getString(10)!!
    )
  }

  public override fun selectExtrinsic(
    hash: String,
    address: String,
    network: String
  ): Query<Extrinsics> = selectExtrinsic(hash, address, network) { txHash, signAddress, blockHash,
      module, method, networkFee, timestamp, success, batch, parentHash, networkName ->
    Extrinsics(
      txHash,
      signAddress,
      blockHash,
      module,
      method,
      networkFee,
      timestamp,
      success,
      batch,
      parentHash,
      networkName
    )
  }

  public override fun <T : Any> selectExtrinsicParams(extrinsicHash: String, mapper: (
    extrinsicHash: String,
    paramName: String,
    paramValue: String
  ) -> T): Query<T> = SelectExtrinsicParamsQuery(extrinsicHash) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!
    )
  }

  public override fun selectExtrinsicParams(extrinsicHash: String): Query<ExtrinsicParam> =
      selectExtrinsicParams(extrinsicHash) { extrinsicHash_, paramName, paramValue ->
    ExtrinsicParam(
      extrinsicHash_,
      paramName,
      paramValue
    )
  }

  public override fun selectTransfersPeers(network: String, query: String): Query<String> =
      SelectTransfersPeersQuery(network, query) { cursor ->
    cursor.getString(0)!!
  }

  public override fun <T : Any> selectSignerInfo(
    address: String,
    network: String,
    mapper: (
      signAddress: String,
      topTime: Long,
      oldTime: Long,
      oldCursor: String?,
      endReached: Boolean,
      networkName: String
    ) -> T
  ): Query<T> = SelectSignerInfoQuery(address, network) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getLong(1)!!,
      cursor.getLong(2)!!,
      cursor.getString(3),
      cursor.getLong(4)!! == 1L,
      cursor.getString(5)!!
    )
  }

  public override fun selectSignerInfo(address: String, network: String): Query<SignerInfo> =
      selectSignerInfo(address, network) { signAddress, topTime, oldTime, oldCursor, endReached,
      networkName ->
    SignerInfo(
      signAddress,
      topTime,
      oldTime,
      oldCursor,
      endReached,
      networkName
    )
  }

  public override fun insertExtrinsic(
    txHash: String,
    signAddress: String,
    networkName: String,
    blockHash: String?,
    module: String,
    method: String,
    networkFee: String,
    timestamp: Long,
    success: Boolean,
    batch: Boolean,
    parentHash: String?
  ): Unit {
    driver.execute(-1411428595, """
    |INSERT OR REPLACE INTO Extrinsics(txHash, signAddress, networkName, blockHash, module, method, networkFee, timestamp, success, batch, parentHash)
    |VALUES(?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)
    """.trimMargin(), 11) {
      bindString(1, txHash)
      bindString(2, signAddress)
      bindString(3, networkName)
      bindString(4, blockHash)
      bindString(5, module)
      bindString(6, method)
      bindString(7, networkFee)
      bindLong(8, timestamp)
      bindLong(9, if (success) 1L else 0L)
      bindLong(10, if (batch) 1L else 0L)
      bindString(11, parentHash)
    }
    notifyQueries(-1411428595, {database.soraHistoryDatabaseQueries.selectExtrinsicsPaged +
        database.soraHistoryDatabaseQueries.selectTransfersPeers +
        database.soraHistoryDatabaseQueries.selectExtrinsic +
        database.soraHistoryDatabaseQueries.selectExtrinsicsNested})
  }

  public override fun insertExtrinsicParam(
    extrinsicHash: String,
    paramName: String,
    paramValue: String
  ): Unit {
    driver.execute(-786548352, """
    |INSERT OR REPLACE INTO ExtrinsicParam(extrinsicHash, paramName, paramValue)
    |VALUES (?, ?, ?)
    """.trimMargin(), 3) {
      bindString(1, extrinsicHash)
      bindString(2, paramName)
      bindString(3, paramValue)
    }
    notifyQueries(-786548352, {database.soraHistoryDatabaseQueries.selectTransfersPeers +
        database.soraHistoryDatabaseQueries.selectExtrinsicParams})
  }

  public override fun insertSignerInfo(
    signAddress: String,
    networkName: String,
    topTime: Long,
    oldTime: Long,
    oldCursor: String?,
    endReached: Boolean
  ): Unit {
    driver.execute(1532363874, """
    |INSERT OR REPLACE INTO SignerInfo(signAddress, networkName, topTime, oldTime, oldCursor, endReached)
    |VALUES (?, ?, ?, ?, ?, ?)
    """.trimMargin(), 6) {
      bindString(1, signAddress)
      bindString(2, networkName)
      bindLong(3, topTime)
      bindLong(4, oldTime)
      bindString(5, oldCursor)
      bindLong(6, if (endReached) 1L else 0L)
    }
    notifyQueries(1532363874, {database.soraHistoryDatabaseQueries.selectSignerInfo})
  }

  public override fun insertSignerInfoFull(SignerInfo: SignerInfo): Unit {
    driver.execute(-29713903, """
    |INSERT OR REPLACE INTO SignerInfo(signAddress, networkName, topTime, oldTime, oldCursor, endReached)
    |VALUES (?, ?, ?, ?, ?, ?)
    """.trimMargin(), 6) {
      bindString(1, SignerInfo.signAddress)
      bindString(2, SignerInfo.networkName)
      bindLong(3, SignerInfo.topTime)
      bindLong(4, SignerInfo.oldTime)
      bindString(5, SignerInfo.oldCursor)
      bindLong(6, if (SignerInfo.endReached) 1L else 0L)
    }
    notifyQueries(-29713903, {database.soraHistoryDatabaseQueries.selectSignerInfo})
  }

  public override fun removeAllExtrinsics(): Unit {
    driver.execute(-1541428088, """DELETE FROM Extrinsics""", 0)
    notifyQueries(-1541428088, {database.soraHistoryDatabaseQueries.selectExtrinsicsPaged +
        database.soraHistoryDatabaseQueries.selectTransfersPeers +
        database.soraHistoryDatabaseQueries.selectExtrinsic +
        database.soraHistoryDatabaseQueries.selectExtrinsicsNested +
        database.soraHistoryDatabaseQueries.selectExtrinsicParams})
  }

  public override fun removeExtrinsics(address: String, network: String): Unit {
    driver.execute(12601105, """DELETE FROM Extrinsics WHERE signAddress = ? AND networkName = ?""",
        2) {
      bindString(1, address)
      bindString(2, network)
    }
    notifyQueries(12601105, {database.soraHistoryDatabaseQueries.selectExtrinsicsPaged +
        database.soraHistoryDatabaseQueries.selectTransfersPeers +
        database.soraHistoryDatabaseQueries.selectExtrinsic +
        database.soraHistoryDatabaseQueries.selectExtrinsicsNested +
        database.soraHistoryDatabaseQueries.selectExtrinsicParams})
  }

  public override fun removeAllSignerInfo(): Unit {
    driver.execute(795549156, """DELETE FROM SignerInfo""", 0)
    notifyQueries(795549156, {database.soraHistoryDatabaseQueries.selectSignerInfo})
  }

  public override fun removeSignerInfo(address: String, network: String): Unit {
    driver.execute(-1945388947,
        """DELETE FROM SignerInfo WHERE signAddress = ? AND networkName = ?""", 2) {
      bindString(1, address)
      bindString(2, network)
    }
    notifyQueries(-1945388947, {database.soraHistoryDatabaseQueries.selectSignerInfo})
  }

  private inner class SelectExtrinsicsPagedQuery<out T : Any>(
    public val address: String,
    public val network: String,
    public val limit: Long,
    public val offset: Long,
    mapper: (SqlCursor) -> T
  ) : Query<T>(selectExtrinsicsPaged, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(-1927823444, """
    |SELECT Extrinsics.*
    |FROM Extrinsics WHERE signAddress = ? AND networkName = ? AND parentHash ISNULL ORDER BY timestamp DESC LIMIT ? OFFSET ?
    """.trimMargin(), 4) {
      bindString(1, address)
      bindString(2, network)
      bindLong(3, limit)
      bindLong(4, offset)
    }

    public override fun toString(): String = "SoraHistoryDatabase.sq:selectExtrinsicsPaged"
  }

  private inner class SelectExtrinsicsNestedQuery<out T : Any>(
    public val parentHash: String?,
    mapper: (SqlCursor) -> T
  ) : Query<T>(selectExtrinsicsNested, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(null, """
    |SELECT Extrinsics.*
    |FROM Extrinsics WHERE parentHash ${ if (parentHash == null) "IS" else "=" } ?
    """.trimMargin(), 1) {
      bindString(1, parentHash)
    }

    public override fun toString(): String = "SoraHistoryDatabase.sq:selectExtrinsicsNested"
  }

  private inner class SelectExtrinsicQuery<out T : Any>(
    public val hash: String,
    public val address: String,
    public val network: String,
    mapper: (SqlCursor) -> T
  ) : Query<T>(selectExtrinsic, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(-22431702, """
    |SELECT Extrinsics.*
    |FROM Extrinsics WHERE txHash = ? AND signAddress = ? AND networkName = ?
    """.trimMargin(), 3) {
      bindString(1, hash)
      bindString(2, address)
      bindString(3, network)
    }

    public override fun toString(): String = "SoraHistoryDatabase.sq:selectExtrinsic"
  }

  private inner class SelectExtrinsicParamsQuery<out T : Any>(
    public val extrinsicHash: String,
    mapper: (SqlCursor) -> T
  ) : Query<T>(selectExtrinsicParams, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(1381324368, """
    |SELECT ExtrinsicParam.*
    |FROM ExtrinsicParam WHERE extrinsicHash = ?
    """.trimMargin(), 1) {
      bindString(1, extrinsicHash)
    }

    public override fun toString(): String = "SoraHistoryDatabase.sq:selectExtrinsicParams"
  }

  private inner class SelectTransfersPeersQuery<out T : Any>(
    public val network: String,
    public val query: String,
    mapper: (SqlCursor) -> T
  ) : Query<T>(selectTransfersPeers, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(-68791370, """
    |SELECT DISTINCT ExtrinsicParam.paramValue
    |FROM ExtrinsicParam INNER JOIN Extrinsics ON ExtrinsicParam.extrinsicHash = Extrinsics.txHash
    |WHERE (paramName = "to" OR paramName = "from") AND Extrinsics.networkName = ?
    |AND paramValue LIKE '%' || ? || "%"
    """.trimMargin(), 2) {
      bindString(1, network)
      bindString(2, query)
    }

    public override fun toString(): String = "SoraHistoryDatabase.sq:selectTransfersPeers"
  }

  private inner class SelectSignerInfoQuery<out T : Any>(
    public val address: String,
    public val network: String,
    mapper: (SqlCursor) -> T
  ) : Query<T>(selectSignerInfo, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(1641594597, """
    |SELECT SignerInfo.*
    |FROM SignerInfo WHERE signAddress = ? AND networkName = ?
    """.trimMargin(), 2) {
      bindString(1, address)
      bindString(2, network)
    }

    public override fun toString(): String = "SoraHistoryDatabase.sq:selectSignerInfo"
  }
}
