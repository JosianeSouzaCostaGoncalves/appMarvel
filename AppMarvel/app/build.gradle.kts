plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("kotlin-android")
}

android {
    namespace = "com.example.appmarvel"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.appmarvel"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation (libs.androidx.room.runtime)
    implementation (libs.androidx.room.ktx)
    implementation("androidx.lifecycle:lifecycle-extensions:2.0.0-alpha1")
    kapt("androidx.lifecycle:lifecycle-compiler:2.0.0-alpha1")
//    implementation("androidx.room:room-runtime:2.0.0-alpha1")
//    kapt("androidx.room:room-compiler:2.0.0-alpha1")
//    ksp("android.arch.persistence.room:compiler:1.1.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.logging.interceptor)
    implementation (libs.timber)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.material.v1120)
    implementation(libs.okhttp)
    implementation(libs.glide)
    implementation(libs.koin.android)


    implementation ("com.github.chuckerteam.chucker:library:4.0.0")
  //  implementation ("com.github.chuckerteam.chucker:library-no-op:4.0.0")

    implementation ("io.coil-kt:coil:2.1.0")
}