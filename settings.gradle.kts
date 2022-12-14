rootProject.name = "seppuku2"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("mainLibs") {
            from(files("gradle/mainLibs.versions.toml"))
        }

        create("minecraftLibs") {
            from(files("gradle/minecraftLibs.versions.toml"))
        }

        create("testLibs") {
            from(files("gradle/testLibs.versions.toml"))
        }
    }
}

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()

        maven("https://maven.fabricmc.net")
    }
}

include(
    "seppuku-client",

    "seppuku-sdk:components",
    "seppuku-sdk:dependency-injection",
    "seppuku-sdk:ecs",
    "seppuku-sdk:ecs-codegen",
    "seppuku-sdk:plugin",
    "seppuku-sdk:settings",

    "seppuku-plugins:common-accessors",
    "seppuku-plugins:common-systems",

    "seppuku-plugins:auto-tool",
    "seppuku-plugins:fast-mine",
    "seppuku-plugins:heads-up-display",
    "seppuku-plugins:no-fall",
    "seppuku-plugins:speed",
    "seppuku-plugins:sprint",
    "seppuku-plugins:vanilla-fly",
)
