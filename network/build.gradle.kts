import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.library.model.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.model.get().pluginId)
    kotlin("kapt")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.projectbyzakaria.network"
    compileSdk =  ProjectConf.COMPILE_SDK

    defaultConfig {
        minSdk =  ProjectConf.MIN_SDK
        targetSdk =  ProjectConf.TAERGET_SDK
        val url:String = gradleLocalProperties(rootDir)["url"]?.toString() ?: ""
        buildConfigField("String", "base_url", "\"${url}\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false

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
        buildConfig = true
    }
}

dependencies {
    libs.apply {
        api(project(":model"))
        implementation(platform(androidx.compose))
        implementation(androidx.core)
        testImplementation(junit)
        androidTestImplementation(androidx.test.junit)
        androidTestImplementation(androidx.espresso.core)

        implementation(retrofit.retrofit)
        implementation(retrofit.okHttp)
        implementation(retrofit.jsonConverter)

        implementation(hilt.android)
        kapt(hilt.compiler)

        implementation(serialization.json)
        implementation(coroutines.kotlin)









    }

}