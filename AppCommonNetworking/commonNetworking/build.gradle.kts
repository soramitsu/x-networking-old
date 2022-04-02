plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    kotlin("native.cocoapods")
    id("maven-publish")
}

group = "jp.co.soramitsu"

version = "0.0.2"

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "jp.co.soramitsu"
            artifactId = "common-networking"
            version = "0.0.2"

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

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "commonNetworking"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0"){
                    version {
                        strictly("1.6.0")
                    }
                }

                implementation("io.ktor:ktor-client-core:1.6.8")
                implementation("io.ktor:ktor-client-serialization:1.6.8")
                implementation("io.ktor:ktor-client-json:1.6.8")
                implementation("io.ktor:ktor-client-logging:1.6.8")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("io.ktor:ktor-client-okhttp:1.6.8")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.6.8")
            }
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
    android {
        publishAllLibraryVariants()
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
}
