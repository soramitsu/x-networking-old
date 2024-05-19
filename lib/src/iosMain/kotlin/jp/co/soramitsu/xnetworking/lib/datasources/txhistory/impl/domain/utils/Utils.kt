package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.utils

import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.utils.PackedCursor
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.utils.PackedCursorImpl
import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("create")
fun PackedCursor.Companion.createIOS(cursor: String?): PackedCursor = PackedCursorImpl(cursor)