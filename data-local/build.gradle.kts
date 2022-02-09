import com.lexwilliam.dependencies.ProjectModules
import com.lexwilliam.dependencies.Dependencies

plugins {
    id("com.android.library")
    id("com.lexwilliam.android.plugin")
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