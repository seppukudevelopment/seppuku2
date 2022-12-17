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

plugins {
  alias(mainLibs.plugins.kotlin.jvm)
  alias(minecraftLibs.plugins.fabric.loom)
}

dependencies {
  minecraftLibs {
    minecraft(minecraft)
    mappings(fabric.yarn)
    modImplementation(fabric.language.kotlin)
  }

  projects {
    implementation(seppukuClient) { targetConfiguration = "namedElements" }
  }
}

tasks {
  processResources {
    inputs.property("version", project.version)
    filesMatching("fabric.mod.json") {
      expand(mapOf("version" to project.version))
    }
  }
}
