plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias (libs.plugins.hilt.application)
    id ("dagger.hilt.android.plugin")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.ksp)

}

android {
    namespace = "com.example.paintcompose"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.paintcompose"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    //navigation
    implementation (libs.androidx.navigation.compose)

    //coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    //lifecycle
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.runtime.ktx.v231)
    implementation (libs.androidx.lifecycle.viewmodel.compose)

    //material
    implementation(libs.google.compose.material.material)
    implementation(libs.androidx.material3)

    //coil
    implementation(libs.compose.coil)
    //color-picker
    implementation(libs.compose.colorpicker)

    //hilt
    implementation (libs.hilt.android)
    implementation (libs.hilt.navigation.compose)
    ksp (libs.hilt.android.compiler)

    //serialization
    implementation(libs.kotlinx.serialization)

    //retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)

    //room
    implementation(libs.androidx.room.roomKtx)
    implementation(libs.androidx.room.core.testing)
    ksp(libs.androidx.room.roomCompiler)

    //datastore
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.preferences)

    //work manager
    implementation(libs.workManager)

    //test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //other
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation (libs.accompanist.flowlayout)

}
