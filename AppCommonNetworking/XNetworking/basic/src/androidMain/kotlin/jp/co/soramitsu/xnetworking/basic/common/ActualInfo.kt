package jp.co.soramitsu.xnetworking.basic.common

import jp.co.soramitsu.xnetworking.basic.common.platform_android

actual fun platform(): String = platform_android

// content this.PackageManager.GetPackageInfo(this.PackageName, 0)
