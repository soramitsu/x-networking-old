package jp.co.soramitsu.xnetworking.common

object Utils {
    fun String.toDoubleNan(): Double? = this.toDoubleOrNull()?.let {
        if (it.isNaN()) null else it
    }
}
