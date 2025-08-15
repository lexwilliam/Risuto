import com.lexwilliam.dependencies.Dependencies
import com.lexwilliam.dependencies.ProjectModules

plugins {
    id("com.android.library")
    id("com.lexwilliam.android.plugin")
}

android {
    namespace = "com.lexwilliam.data_local"
}

dependencies {
    implementation(project(ProjectModules.data))

    api(Dependencies.Room.room)
    kapt(Dependencies.Room.roomCompiler)
    api(Dependencies.Room.roomKtx)

    api(Dependencies.DataStore.datastore)
    api(Dependencies.DataStore.preferences)

    implementation(Dependencies.Hilt.hiltAndroid)
    implementation(Dependencies.Hilt.hiltAndroidCompiler)
}