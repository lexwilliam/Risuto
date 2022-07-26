import org.jetbrains.kotlin.gradle.plugin.statistics.ReportStatisticsToElasticSearch.url

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
    dependencies {
        classpath(com.lexwilliam.dependencies.BuildDependencies.androidGradle)
        classpath(com.lexwilliam.dependencies.BuildDependencies.kotlinGradlePlugin)
        classpath(com.lexwilliam.dependencies.BuildDependencies.hiltAndroidGradlePlugin)
        classpath(com.lexwilliam.dependencies.BuildDependencies.googleSecret)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}

task("clean") {
    delete(rootProject.buildDir)
}