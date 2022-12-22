package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo

import kotlin.jvm.Synchronized

abstract class BasicCases<T> {
    private val map = mutableMapOf<String, T>()

    @Synchronized
    fun getCase(caseName: String): T {
        return map[caseName] ?: provideInstance(caseName).also {
            map[caseName] = it
        }
    }

    abstract fun provideInstance(caseName: String): T
}
