package jp.co.soramitsu.xnetworking.common

import com.ionspin.kotlin.bignum.integer.toBigInteger
import com.ionspin.kotlin.bignum.integer.BigInteger as SharedBigInteger
import java.math.BigInteger as JavaBigInteger

fun SharedBigInteger.toJavaBigInteger(): JavaBigInteger {
    return JavaBigInteger(toString(10), 10)
}

fun JavaBigInteger.toSharedBigInteger(): SharedBigInteger {
    return toString(10).toBigInteger(10)
}