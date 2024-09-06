plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger)
    alias(libs.plugins.kotlin.serialization)
    id("kotlin-kapt")
}

android {
    namespace = "com.zekony.windichat"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zekony.windichat"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.bundles.core)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.compose.icons)

    implementation(libs.androidx.navigation)
    implementation(libs.bundles.orbit)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.androidx.compose)
    kapt(libs.hilt.androidx.compiler)

    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlinx.serialization)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.coil.compose)

    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
}