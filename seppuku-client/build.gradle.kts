plugins {
  alias(mainLibs.plugins.kotlin.jvm)
  alias(minecraftLibs.plugins.fabric.loom)
}

dependencies {
  mainLibs {
    implementation(byteBuddy)
    include(byteBuddy)
  }

  minecraftLibs {
    minecraft(minecraft)
    mappings(fabric.yarn)
    modImplementation(fabric.loader)
    modImplementation(fabric.language.kotlin)
  }

  projects {
    implementation(seppukuSdk.components)
    implementation(seppukuSdk.dependencyInjection)
    implementation(seppukuSdk.ecs)
    implementation(seppukuSdk.ecsCodegen)
    implementation(seppukuSdk.plugin)
    implementation(seppukuSdk.settings)

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
