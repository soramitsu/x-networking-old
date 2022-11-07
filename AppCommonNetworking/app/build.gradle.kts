plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "jp.co.soramitsu.appxnetworking"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}

dependencies {
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(project(mapOf("path" to ":XNetworking")))

    //test
//    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.test.ext:junit:1.1.3")
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.12.4")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("io.ktor:ktor-client-mock:2.0.0")
    testImplementation("io.ktor:ktor-client-content-negotiation:2.0.0")
    testImplementation("io.ktor:ktor-serialization-kotlinx-json:2.0.0")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    androidTestImplementation("androidx.work:work-testing:2.7.1")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}