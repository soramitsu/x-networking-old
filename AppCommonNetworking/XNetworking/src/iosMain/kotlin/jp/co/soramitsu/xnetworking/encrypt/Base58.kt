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

actual class Base58 {

    actual fun encode(input: ByteArray): String {
        TODO()
    }

    actual fun decode(input: String): ByteArray {
        TODO()
    }

    actual fun decodeToBigInteger(input: String): BigInteger {
        TODO()
    }

    actual fun decodeChecked(input: String): ByteArray {
        TODO()
    }
}
