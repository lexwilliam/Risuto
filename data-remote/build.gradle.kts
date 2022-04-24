import com.lexwilliam.dependencies.ProjectModules
import com.lexwilliam.dependencies.Dependencies

plugins {
    id("com.android.library")
    id("com.lexwilliam.android.plugin")
}

dependencies {
    implementation(project(ProjectModules.data))
    testImplementation(project(ProjectModules.coreAndroidTest))

    api(Dependencies.Retrofit.retrofit)
    api(Dependencies.Retrofit.retrofitConverterMoshi)
    api(Dependencies.okHttpLoggingInterceptor)
    api(Dependencies.Retrofit.moshiKotlin)
    api(Dependencies.Retrofit.retrofitCoroutineAdapter)

    implementation(Dependencies.Paging.runtime)

    implementation(Dependencies.Hilt.hiltAndroid)
    implementation(Dependencies.Hilt.hiltAndroidCompiler)
}