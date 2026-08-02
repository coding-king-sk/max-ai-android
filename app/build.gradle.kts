plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}
android {
    namespace = "com.max.ai"
    compileSdk = 35
    defaultConfig { applicationId = "com.max.ai"; minSdk = 29; targetSdk = 35; versionCode = 1; versionName = "1.0.0" }
    buildTypes {
        release { isMinifyEnabled = true; isShrinkResources = true; proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro") }
        debug { isMinifyEnabled = false; applicationIdSuffix = ".debug"; versionNameSuffix = "-debug" }
    }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
    kotlinOptions { jvmTarget = "17" }
    buildFeatures { compose = true; buildConfig = true }
}
dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui); implementation(libs.compose.ui.graphics)
    implementation(libs.compose.material3); implementation(libs.compose.material.icons)
    implementation(libs.compose.animation); implementation(libs.compose.foundation)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.core.ktx); implementation(libs.activity.compose)
    implementation(libs.lifecycle.runtime); implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.service); implementation(libs.navigation.compose)
    implementation(libs.hilt.android); ksp(libs.hilt.compiler); implementation(libs.hilt.navigation)
    implementation(libs.room.runtime); implementation(libs.room.ktx); ksp(libs.room.compiler)
    implementation(libs.datastore)
    implementation(libs.ktor.client); implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.serialization); implementation(libs.ktor.logging)
    implementation(libs.coroutines.core); implementation(libs.coroutines.android)
    implementation(libs.serialization.json); implementation(libs.coil.compose)
}
