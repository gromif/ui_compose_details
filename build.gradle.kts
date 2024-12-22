plugins {
    alias(libs.plugins.astracrypt.android.library)
    alias(libs.plugins.astracrypt.android.library.compose)
}

android {
    namespace = "com.nevidimka655.compose_details"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}