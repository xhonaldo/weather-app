plugins {
    alias(libs.plugins.android.application)
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.trackforce"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.trackforce"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            buildConfigField("String", "BASE_DOMAIN", "\"https://api.openweathermap.org/\"")
            buildConfigField("String", "API_VERSION", "\"data/2.5/\"")
            buildConfigField("String", "OPEN_WEATHER_API_KEY", "\"${property("OPEN_WEATHER_API_KEY")}\"")

        }
        getByName("release") {
            buildConfigField("String", "BASE_DOMAIN", "\"https://api.openweathermap.org/\"")
            buildConfigField("String", "API_VERSION", "\"data/3.0/\"")
            buildConfigField("String", "OPEN_WEATHER_API_KEY", "\"${property("OPEN_WEATHER_API_KEY")}\"")

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    //AndroidX
    implementation(libs.androidx.multidex)
    //ROOM Database
    implementation(libs.androidx.room.runtime)
    implementation(libs.play.services.location)
    annotationProcessor(libs.androidx.room.compiler)
    //Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Network
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit.main)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit.adapter.rxjava3)

    // RxJava3 core and Android bindings
    implementation(libs.rxjava)
    implementation(libs.rxandroid)

    //DI
    implementation(libs.hilt.android)
    implementation(libs.hilt.androidx.common)
    annotationProcessor(libs.hilt.android.compiler)



    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}