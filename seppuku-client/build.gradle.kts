plugins {
    alias(mainLibs.plugins.kotlin.jvm)
    alias(mainLibs.plugins.shadow)
    alias(minecraftLibs.plugins.fabric.loom)
}

dependencies {
    mainLibs {
        implementation(byteBuddy)
        shadow(byteBuddy)
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

        shadow(seppukuSdk.components)
        shadow(seppukuSdk.dependencyInjection)
        shadow(seppukuSdk.ecs)
        shadow(seppukuSdk.ecsCodegen)
        shadow(seppukuSdk.plugin)
        shadow(seppukuSdk.settings)
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
        dependencies {
            exclude(dependency("org.jetbrains:.*"))
            exclude(dependency("org.jetbrains.kotlin:.*"))
            exclude(dependency("org.jetbrains.kotlinx:.*"))
        }
    }

    remapJar {
        dependsOn(shadowJar)
        mustRunAfter(shadowJar)
        inputFile.set(shadowJar.get().archiveFile)
    }
}