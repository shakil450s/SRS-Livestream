plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.srs.livestream"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.srs.livestream"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
    }
}
