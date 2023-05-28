@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.library.model.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.model.get().pluginId)
    kotlin("plugin.serialization")
}

android {
    namespace = "com.projectbyzakaria.model"
    compileSdk =  ProjectConf.COMPILE_SDK

    defaultConfig {
        minSdk =  ProjectConf.MIN_SDK
        targetSdk =  ProjectConf.TAERGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    libs.apply {
        implementation(platform(androidx.compose))
        implementation(androidx.core)
        testImplementation(junit)
        androidTestImplementation(androidx.test.junit)
        androidTestImplementation(androidx.espresso.core)

        implementation(serialization.json)
    }

}