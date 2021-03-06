import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
    id ("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.jfma75.movieskotlindemo"
        minSdkVersion (23)
        targetSdkVersion (30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        //kotlinCompilerVersion = Versions.kotlinVersion
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType(KotlinCompile::class).configureEach {
        kotlinOptions.useIR = true
        kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
        //kotlinOptions.freeCompilerArgs += "-Xallow-jvm-ir-dependencies" + "-Xskip-prerelease-check"
    }
}

dependencies {
    //implementation(fileTree("libs") { include(listOf("*.jar")) })

    implementation (Libs.Kotlin.stdlib)
    implementation (Libs.Coroutines.android)
    implementation (Libs.Coroutines.core)

    implementation (Libs.AndroidX.appCompat)
    implementation (Libs.AndroidX.activity)
    implementation (Libs.AndroidX.coreKtx)

    implementation (Libs.AndroidX.Compose.animation)
    implementation (Libs.AndroidX.Compose.activity)
    implementation (Libs.AndroidX.Compose.foundation)
    implementation (Libs.AndroidX.Compose.iconsExtended)
    implementation (Libs.AndroidX.Compose.layout)
    implementation (Libs.AndroidX.Compose.material)
    implementation (Libs.AndroidX.Compose.navigation)
    implementation (Libs.AndroidX.Compose.ui)
    implementation (Libs.AndroidX.Compose.runtime)
    implementation (Libs.AndroidX.Compose.tooling)

    implementation (Libs.AndroidX.datastore)

    implementation (Libs.Lifecycle.livedata)
    implementation (Libs.Lifecycle.viewmodel)

    implementation (Libs.Lifecycle.compose)
    implementation (Libs.Lifecycle.savedState)

    implementation (Libs.Android.hilt)
    kapt (Libs.Android.hiltCompiler)

    testImplementation (Libs.AndroidX.Test.junit)
    androidTestImplementation (Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation (Libs.AndroidX.Test.espressoCore)
    androidTestImplementation (Libs.AndroidX.Test.navigation)
}