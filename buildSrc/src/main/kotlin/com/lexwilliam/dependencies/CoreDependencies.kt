package com.lexwilliam.dependencies

object Dependencies {
    object AndroidX {
        const val fragmentKtx =
            "androidx.fragment:fragment-ktx:${Versions.fragment}"
        const val legacySupport =
            "androidx.legacy:legacy-support-v4:${Versions.legacySupportV4}"
        const val coreKtx =
            "androidx.core:core-ktx:${Versions.core}"
        const val archComponents =
            "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleLivedataKtx}"
        const val browser = "androidx.browser:browser:${Versions.browser}"
        const val splashScreen = "androidx.core:core-splashscreen:${Versions.swipeRefresh}"

        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.compose}"
            const val systemUiController =
                "com.google.accompanist:accompanist-systemuicontroller:${Versions.composeSystemUi}"
            const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
            const val runtimeLiveData =
                "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
            const val material = "androidx.compose.material:material:${Versions.compose}"
            const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
            const val uiUtil = "androidx.compose.ui:ui-util:${Versions.compose}"
            const val navigation =
                "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
            const val viewModel =
                "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}"
            const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
        }
    }

    const val kotlinxCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutines}"

    const val kotlinReflect =
        "org.jetbrains.kotlin:kotlin-reflect:1.5.31"

    object Hilt {
        const val hiltAndroid =
            "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltAndroidCompiler =
            "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        const val hiltCompiler =
            "androidx.hilt:hilt-compiler:${Versions.hiltJetpack}"
        const val hiltViewModel =
            "com.google.dagger:hilt-android-compiler:${Versions.hiltVM}"
        const val hiltNavigationCompose =
            "androidx.hilt:hilt-navigation-compose:${Versions.hiltVM}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitConverterMoshi =
            "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val retrofitCoroutineAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutineAdapter}"
    }

    object Room {
        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    }

    object DataStore {
        const val datastore = "androidx.datastore:datastore:1.0.0"
        const val preferences = "androidx.datastore:datastore-preferences:1.0.0"
    }

    object Paging {
        const val runtime = "androidx.paging:paging-runtime-ktx:3.1.0"
        const val compose = "androidx.paging:paging-compose:1.0.0-alpha14"
    }

    object Accompanist {
        const val flowLayout = "com.google.accompanist:accompanist-flowlayout:${Versions.accompanist}"
        const val insets = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
        const val insetsUi = "com.google.accompanist:accompanist-insets-ui:${Versions.accompanist}"
        const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}"
        const val pager = "com.google.accompanist:accompanist-pager:${Versions.accompanist}"
        const val pagerIndicator = "com.google.accompanist:accompanist-pager-indicators:${Versions.accompanist}"
    }

    const val coil = "io.coil-kt:coil:${Versions.coil}"

    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val lottieCompose = "com.airbnb.android:lottie-compose:${Versions.lottieCompose}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLoggingInterceptor}"

    const val jodaTime = "joda-time:joda-time:${Versions.jodaTime}"

    const val material = "com.google.android.material:material:${Versions.material}"
}