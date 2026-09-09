plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.xboard.sinhala"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.xboard.sinhala"
        minSdk = 26
        targetSdk = 35
        versionCode = 10
        versionName = "1.0"
    }
}
