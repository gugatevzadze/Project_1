plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.project_1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.project_1"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        getByName("debug") {
//            buildConfigField("String", "BASE_URL", "\"https://perenual.com/api/\"")
//            buildConfigField("String", "API_KEY", "\"sk-ZAXK65a6d11091c7f3804\"")
            buildConfigField(
                "String",
                "LIST_BASE_URL",
                "\"https://65a7056e94c2c5762da627f6.mockapi.io/api/v1/\""
            )
            buildConfigField(
                "String",
                "DETAIL_BASE_URL",
                "\"https://px5gxu4cxi.api.quickmocker.com/\""
            )
        }

        getByName("release") {
//            buildConfigField("String", "BASE_URL", "\"https://perenual.com/api/\"")
//            buildConfigField("String", "API_KEY", "\"sk-ZAXK65a6d11091c7f3804\"")
            buildConfigField(
                "String",
                "LIST_BASE_URL",
                "\"https://65a7056e94c2c5762da627f6.mockapi.io/api/v1/\""
            )
            buildConfigField(
                "String",
                "DETAIL_BASE_URL",
                "\"https://px5gxu4cxi.api.quickmocker.com/\""
            )
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
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.datastore:datastore-core:1.0.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //navgraph
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    //moshi
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    //datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    //hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")
    //glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    //splash screen api
    implementation("androidx.core:core-splashscreen:1.0.1")
    //okhttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
    //firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-auth")
    //room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
}
kapt {
    correctErrorTypes = true
}