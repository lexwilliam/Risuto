import com.lexwilliam.dependencies.Dependencies
import com.lexwilliam.dependencies.ProjectModules
import com.lexwilliam.dependencies.TestDependencies

plugins {
    id("com.android.library")
    id("com.lexwilliam.android.plugin")
}

android {
    namespace = "com.lexwilliam.data_remote"
}

dependencies {
    implementation(project(ProjectModules.data))

    api(Dependencies.Retrofit.retrofit)
    api(Dependencies.Retrofit.retrofitConverterMoshi)
    api(Dependencies.okHttpLoggingInterceptor)
    api(Dependencies.Retrofit.moshiKotlin)
    api(Dependencies.Retrofit.retrofitCoroutineAdapter)

    implementation(Dependencies.Paging.runtime)

    implementation(Dependencies.Hilt.hiltAndroid)
    implementation(Dependencies.Hilt.hiltAndroidCompiler)

    implementation(TestDependencies.mockWebServer)
    implementation(TestDependencies.AndroidX.googleTruth)
}