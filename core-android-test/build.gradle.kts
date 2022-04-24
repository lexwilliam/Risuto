import com.lexwilliam.dependencies.TestDependencies
import com.lexwilliam.dependencies.Dependencies

plugins {
    id("com.android.library")
    id("com.lexwilliam.android.plugin")
}

dependencies {
    implementation(TestDependencies.kotlinxCoroutines)
    implementation(TestDependencies.AndroidX.espressoCore)
    implementation(TestDependencies.AndroidX.espressoContrib)

    implementation(Dependencies.jodaTime)
}