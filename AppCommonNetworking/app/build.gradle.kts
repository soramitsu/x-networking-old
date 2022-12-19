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
        kotlinCompilerExtensionVersion = "1.3.2"
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
    implementation(project(mapOf("path" to ":XNetworking")))
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.ui:ui:1.3.2")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.2")
    debugImplementation("androidx.compose.ui:ui-tooling:1.3.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.3.2")

    //test
    implementation("androidx.test.ext:junit:1.1.4")
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.12.4")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("io.ktor:ktor-client-mock:2.2.1")
    testImplementation("io.ktor:ktor-client-content-negotiation:2.2.1")
    testImplementation("io.ktor:ktor-serialization-kotlinx-json:2.2.1")
    androidTestImplementation("androidx.test:runner:1.5.1")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.work:work-testing:2.7.1")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
}