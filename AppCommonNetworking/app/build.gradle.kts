plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "jp.co.soramitsu.appxnetworking"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    namespace = "jp.co.soramitsu.appxnetworking"
}

dependencies {
    implementation(project(mapOf("path" to ":XNetworking")))
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("androidx.compose.ui:ui:1.4.2")
    implementation("androidx.compose.material:material:1.4.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.2")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.2")

    //test
    implementation("androidx.test.ext:junit:1.1.5")
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.13.5")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("io.ktor:ktor-client-mock:2.2.3")
    testImplementation("io.ktor:ktor-client-content-negotiation:2.2.3")
    testImplementation("io.ktor:ktor-serialization-kotlinx-json:2.2.3")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.work:work-testing:2.8.1")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}