import com.lexwilliam.dependencies.Dependencies

plugins {
    id("com.android.library")
    id("com.lexwilliam.android.plugin")
}

android {
    namespace = "com.lexwilliam.domain"
}

dependencies {
    implementation(Dependencies.Paging.runtime)

    implementation(Dependencies.Hilt.hiltAndroid)
    implementation(Dependencies.Hilt.hiltAndroidCompiler)
}