plugins {
  alias(mainLibs.plugins.kotlin.jvm)
  alias(mainLibs.plugins.shadow)
  alias(minecraftLibs.plugins.fabric.loom)
}

dependencies {
  mainLibs {
    implementation(mainLibs.kotlin.scripting.common)
    implementation(mainLibs.kotlin.scripting.jvm)
    implementation(mainLibs.kotlin.scripting.jvm.host)
    implementation(mainLibs.kotlin.scripting.compiler.embeddable)

    shadow(mainLibs.kotlin.scripting.common)
    shadow(mainLibs.kotlin.scripting.jvm)
    shadow(mainLibs.kotlin.scripting.jvm.host)
  }

  minecraftLibs {
    minecraft(minecraft)
    mappings(fabric.yarn)
    modImplementation(fabric.language.kotlin)
  }

  projects {
    implementation(seppukuPlugins.commonSystems) { targetConfiguration = "namedElements" }

    implementation(seppukuSdk.dependencyInjection)
    implementation(seppukuSdk.components)
    implementation(seppukuSdk.ecs)
    implementation(seppukuSdk.ecsCodegen)
  }
}

tasks {
  processResources {
    inputs.property("version", project.version)
    filesMatching("fabric.mod.json") {
      expand(mapOf("version" to project.version))
    }
  }

  shadowJar {
    configurations = listOf(project.configurations.shadow.get())
    minimize()
  }

  remapJar {
    dependsOn(shadowJar)
    mustRunAfter(shadowJar)
    inputFile.set(shadowJar.get().archiveFile)
  }
}
