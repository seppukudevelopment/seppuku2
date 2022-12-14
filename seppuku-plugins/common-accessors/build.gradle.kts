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
}

tasks {
  processResources {
    inputs.property("version", project.version)
    filesMatching("fabric.mod.json") {
      expand(mapOf("version" to project.version))
    }
  }
}
