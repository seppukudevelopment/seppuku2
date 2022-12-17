/*
 *     Copyright (C) 2022  Seppuku Development
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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

  "seppuku-plugins:auto-tool",
  "seppuku-plugins:fast-mine",
  "seppuku-plugins:heads-up-display",
  "seppuku-plugins:no-fall",
  "seppuku-plugins:speed",
  "seppuku-plugins:sprint",
  "seppuku-plugins:vanilla-fly",
)
