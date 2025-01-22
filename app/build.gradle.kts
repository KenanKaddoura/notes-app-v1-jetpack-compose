plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt") // For annotation processing (Room, Hilt)
    id("dagger.hilt.android.plugin") // Required for Dagger Hilt
}

android {
    namespace = "jetpack.cleanarchitecture.notes"
    compileSdk = 34

    defaultConfig {
        applicationId = "jetpack.cleanarchitecture.notes"
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Core Dependencies

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Testing Dependencies

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Jetpack Compose dependencies
//    implementation("androidx.compose.ui:ui:1.7.6") // Core Compose library
//    implementation("androidx.compose.material3:material3:1.3.1") // Material 3 design
//    implementation("androidx.compose.ui:ui-tooling-preview:1.7.6") // Preview support
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2") // ViewModel support
    implementation("androidx.navigation:navigation-compose:2.7.3") // Navigation support
    implementation("androidx.compose.material:material-icons-extended:1.5.1") // Material Icons support
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0") // Hilt support for Compose

//    debugImplementation("androidx.compose.ui:ui-tooling:1.7.6") // Debugging tools for Compose

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3") // Coroutines core library
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3") // Coroutines for Android

    //Dagger - Hilt
    // Dagger Hilt dependencies
    implementation("com.google.dagger:hilt-android:2.48") // Hilt dependency
    kapt("com.google.dagger:hilt-android-compiler:2.48") // Hilt compiler for annotation processing

    // Room
    implementation("androidx.room:room-runtime:2.6.0") // Room runtime
    kapt("androidx.room:room-compiler:2.6.0") // Room compiler for annotations


    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.6.0") // Kotlin extensions for Room
    implementation("androidx.room:room-paging:2.6.0") // Paging support for Room (optional)
}