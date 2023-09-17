import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    kotlin("native.cocoapods")
    id("maven-publish")
    id("com.apollographql.apollo3") version "4.0.0-alpha.3"
}

group = "jp.co.soramitsu"

version = "0.1.0"

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "jp.co.soramitsu"
            artifactId = "xnetworking.sorawallet"
            version = "0.1.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
    repositories {
        maven {
            name = "scnRepo"
            url = uri(if (hasProperty("RELEASE_REPOSITORY_URL")) property("RELEASE_REPOSITORY_URL")!! else System.getenv()["RELEASE_REPOSITORY_URL"]!!)
            credentials {
                username = if (hasProperty("NEXUS_USERNAME")) (property("NEXUS_USERNAME") as String) else System.getenv()["NEXUS_USERNAME"]
                password = if (hasProperty("NEXUS_PASSWORD")) (property("NEXUS_PASSWORD") as String) else System.getenv()["NEXUS_PASSWORD"]
            }
        }
        maven {
            name = "scnRepoLocal"
            url = uri("${project.buildDir}/scnrepo")
        }
    }
}

val coroutineVersion = "1.6.4"
val ktorVersion = "2.3.1"

kotlin {
    val iosFrameworkName = "XNetworking.SoraWallet"
    val xcf = XCFramework()

    android()
    iosX64 {
        binaries.framework {
            baseName = iosFrameworkName
            xcf.add(this)
        }
    }
    iosArm64 {
        binaries.framework {
            baseName = iosFrameworkName
            xcf.add(this)
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = iosFrameworkName
            xcf.add(this)
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = iosFrameworkName
        }
    }

    sourceSets {
        val androidMain by getting {
            dependencies {
                api("io.ktor:ktor-client-okhttp:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")
            }
        }
        
        val commonMain by getting {
            dependencies {
                implementation(project(":core:basic"))
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")

                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                //implementation("io.ktor:ktor-client-json:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")

                implementation("com.russhwolf:multiplatform-settings:1.0.0")
                implementation("com.russhwolf:multiplatform-settings-serialization:1.0.0")
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
    android {
        publishAllLibraryVariants()
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
    namespace = "jp.co.soramitsu.xnetworking.sorawallet"
}

apollo {
    service("sorawallet") {
        packageName.set("jp.co.soramitsu.xnetworking")
        schemaFiles.setFrom(
            file("../../schema").walkTopDown().filter {
                it.isFile && it.extension == "graphqls"
            }.toList()
        )
        srcDir(file("src/commonMain/qraphql"))
        outputDir.set(File("${project.buildDir}/generated/apollo/", "schemas"))
    }
}

tasks.register<Copy>("copyiOSTestResources") {
    from("src/iosTest/resources")
    into("build/bin/iosX64/debugTest/resources")
}

tasks.findByName("iosX64Test")!!.dependsOn("copyiOSTestResources")