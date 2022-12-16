plugins {
  alias(mainLibs.plugins.kotlin.jvm)
  alias(minecraftLibs.plugins.fabric.loom)
}

dependencies {
  mainLibs {
    include(byteBuddy)
  }

  minecraftLibs {
    minecraft(minecraft)
    mappings(fabric.yarn)
    modImplementation(fabric.loader)
    modImplementation(fabric.language.kotlin)
  }

  projects {
    api(seppukuSdk.components)
    api(seppukuSdk.dependencyInjection)
    api(seppukuSdk.ecs)
    api(seppukuSdk.ecsCodegen)
    api(seppukuSdk.plugin)
    api(seppukuSdk.settings)

    include(seppukuSdk.components)
    include(seppukuSdk.dependencyInjection)
    include(seppukuSdk.ecs)
    include(seppukuSdk.ecsCodegen)
    include(seppukuSdk.plugin)
    include(seppukuSdk.settings)
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
