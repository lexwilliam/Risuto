pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        maven { setUrl("https://jitpack.io") }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = java.net.URI("https://devrepo.kakao.com/nexus/content/groups/public/") }
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "Risuto"
rootProject.buildFileName = "build.gradle.kts"
include(
    ":app",
    ":data",
    ":domain",
    ":data-local",
    ":data-remote",
)
