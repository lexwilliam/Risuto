// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
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
    }
}

task("clean") {
    delete(rootProject.buildDir)
}