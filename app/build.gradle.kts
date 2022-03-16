import com.lexwilliam.dependencies.ProjectModules
import com.lexwilliam.dependencies.Dependencies
import com.lexwilliam.dependencies.TestDependencies
import com.lexwilliam.dependencies.Versions.coil

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.lexwilliam.android.plugin")
    id("dagger.hilt.android.plugin")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

androidPlugin {
    buildType = com.lexwilliam.android.plugin.BuildType.App
}

android {
    defaultConfig {
        applicationId = "com.lexwilliam.risuto"
        minSdk = com.lexwilliam.dependencies.AndroidSettings.minSdk
        targetSdk = com.lexwilliam.dependencies.AndroidSettings.targetSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            buildConfigField("Integer", "PORT", "8080")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    file("proguard-rules.pro")
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = com.lexwilliam.dependencies.Versions.compose
    }

    hilt {
        enableExperimentalClasspathAggregation = true
    }
}

dependencies {
    implementation(project(ProjectModules.cache))
    implementation(project(ProjectModules.api))
    implementation(project(ProjectModules.domain))
    implementation(project(ProjectModules.data))

    implementation(Dependencies.AndroidX.Compose.viewModel)
    implementation(Dependencies.material)
    kapt(Dependencies.AndroidX.lifecycleCompiler)
    implementation(Dependencies.AndroidX.archComponents)
    implementation(Dependencies.AndroidX.browser)
    implementation(Dependencies.kotlinReflect)

    implementation(Dependencies.AndroidX.Compose.ui)
    implementation(Dependencies.AndroidX.Compose.systemUiController)
    implementation(Dependencies.AndroidX.Compose.material)
    implementation(Dependencies.AndroidX.Compose.uiTooling)
    implementation(Dependencies.AndroidX.Compose.runtime)
    implementation(Dependencies.AndroidX.Compose.runtimeLiveData)
    implementation(Dependencies.AndroidX.Compose.navigation)

    implementation(Dependencies.Paging.runtime)
    implementation(Dependencies.Paging.compose)

    implementation(Dependencies.Hilt.hiltAndroid)
    implementation(Dependencies.Hilt.hiltAndroidCompiler)
    implementation(Dependencies.Hilt.hiltViewModel)
    implementation(Dependencies.Hilt.hiltCompiler)
    implementation(Dependencies.Hilt.hiltNavigationCompose)

    implementation(Dependencies.flowLayout)
    implementation(Dependencies.coilCompose)
    implementation(Dependencies.lottie)
    implementation(Dependencies.lottieCompose)

    implementation("io.coil-kt:coil:$coil")
    implementation("com.google.accompanist:accompanist-coil:0.12.0")
    implementation("com.google.accompanist:accompanist-insets:0.24.3-alpha")
    implementation("com.google.accompanist:accompanist-insets-ui:0.24.3-alpha")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.24.3-alpha")
    implementation("androidx.core:core-splashscreen:1.0.0-beta01")

    implementation(Dependencies.AndroidX.legacySupport)
    implementation(Dependencies.jodaTime)

    testImplementation(Dependencies.jodaTime)
//    testImplementation(project(ProjectModules.coreAndroidTest))
    testImplementation(project(ProjectModules.domain))

//    androidTestImplementation(project(ProjectModules.coreAndroidTest))
    testImplementation(TestDependencies.AndroidX.googleTruth)
    androidTestImplementation(TestDependencies.AndroidX.core)
    androidTestImplementation(TestDependencies.AndroidX.coreKtx)
    androidTestImplementation(TestDependencies.AndroidX.runner)
    androidTestImplementation(TestDependencies.AndroidX.rules)
    androidTestImplementation(TestDependencies.AndroidX.composeUiTest)
    androidTestImplementation(TestDependencies.AndroidX.composeUiTestJUnit4)
    debugImplementation(TestDependencies.AndroidX.uiTestManifest)

    androidTestImplementation(TestDependencies.mockWebServer)

    androidTestImplementation(TestDependencies.Hilt.androidTesting)
//    kaptAndroidTest(TestDependencies.Hilt.androidCompiler)
    androidTestAnnotationProcessor(TestDependencies.Hilt.androidCompiler)
}