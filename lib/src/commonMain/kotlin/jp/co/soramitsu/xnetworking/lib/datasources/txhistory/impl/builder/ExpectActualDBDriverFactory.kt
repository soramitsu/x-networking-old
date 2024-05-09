package jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.builder

import com.squareup.sqldelight.db.SqlDriver

expect class ExpectActualDBDriverFactory {
    internal fun createDriver(): SqlDriver
}