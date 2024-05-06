buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    val sqlDelightVersion: String by project

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.20")
        classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
        classpath("com.android.tools.build:gradle:7.4.2")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
    afterEvaluate {
        tasks.register("testClasses") {}
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}