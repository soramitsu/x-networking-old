/**
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.soramitsu.xnetworking.encrypt

import com.ionspin.kotlin.bignum.integer.BigInteger
import jp.co.soramitsu.xnetworking.exceptions.AddressFormatException

expect class Base58() {

    fun encode(input: ByteArray): String

    @Throws(AddressFormatException::class)
    fun decode(input: String): ByteArray

    @Throws(AddressFormatException::class)
    fun decodeToBigInteger(input: String): BigInteger

    @Throws(AddressFormatException::class)
    fun decodeChecked(input: String): ByteArray
}
