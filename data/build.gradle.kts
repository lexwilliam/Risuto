import com.lexwilliam.dependencies.Dependencies
import com.lexwilliam.dependencies.ProjectModules

plugins {
    id("com.android.library")
    id("com.lexwilliam.android.plugin")
}


android {
    namespace = "com.lexwilliam.data"
}

dependencies {
    implementation(project(ProjectModules.domain))

    implementation(Dependencies.Paging.runtime)

    implementation(Dependencies.Hilt.hiltAndroid)
    implementation(Dependencies.Hilt.hiltAndroidCompiler)
}