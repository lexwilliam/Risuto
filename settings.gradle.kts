rootProject.name = "RisutoV2"
rootProject.buildFileName = "build.gradle.kts"
include(
    ":app",
    ":data",
    ":domain",
    ":data-local",
    ":data-remote"
)
include(":core-android-test")
include(":feature-home")
