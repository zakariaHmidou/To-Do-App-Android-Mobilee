@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id (libs.plugins.android.application.model.get().pluginId)
    id (libs.plugins.jetbrains.kotlin.android.model.get().pluginId)
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace ="com.projectbyzakaria.todojway"
    compileSdk = ProjectConf.COMPILE_SDK

    defaultConfig {
        applicationId ="com.projectbyzakaria.todojway"
        minSdk =ProjectConf.MIN_SDK
        targetSdk =ProjectConf.TAERGET_SDK
        versionCode =ProjectConf.VERSTION_CODE
        versionName =ProjectConf.VERSTION_NAME

        testInstrumentationRunner= "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary =true
        }
    }

    kotlin {
        sourceSets.configureEach {
            kotlin.srcDir("$buildDir/generated/ksp/$name/kotlin/")
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug"){
            isDebuggable = true
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility =JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose =true
    }
    composeOptions {
        kotlinCompilerExtensionVersion ="1.3.2"
    }
    packagingOptions {
        resources {
            exclude("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    libs.apply {
        implementation(project(":data"))
        implementation(project(":model"))

        implementation(androidx.core)
        implementation(platform(androidx.compose))
        implementation(androidx.lifecycle)
        implementation(androidx.activity)
        implementation(androidx.compose.ui)
        implementation(androidx.compose.ui.graphics)
        implementation(androidx.compose.ui.tooling.preview)
        implementation(androidx.material3)
        testImplementation(junit)
        androidTestImplementation(platform(androidx.compose))
        androidTestImplementation(androidx.test.junit)
        androidTestImplementation(androidx.espresso.core)
        androidTestImplementation(androidx.compose.ui.test.junit4)
        androidTestImplementation(androidx.compose.ui.tooling)
        androidTestImplementation(androidx.compose.ui.test.manifest)

        //navigation component
        implementation(androidx.navigation.compose)
        // splash screen
        implementation(androidx.core.splashscreen)

        //constraint layout
        implementation(androidx.constraintlayout.compose)

        //window class
        implementation(windowSizeClass)

        implementation(hilt.android)
        kapt(hilt.compiler)
        implementation(coroutines.kotlin)

        constraints {
            implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
                because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
            }
            implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
                because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
            }
        }


    }

}