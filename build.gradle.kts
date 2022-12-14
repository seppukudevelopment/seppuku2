plugins {
    alias(mainLibs.plugins.kotlin.jvm) apply false
    alias(mainLibs.plugins.shadow) apply false
    alias(minecraftLibs.plugins.fabric.loom) apply false
    alias(testLibs.plugins.kotlinx.benchmark) apply false
}

allprojects {
    group = "pw.seppuku"
    version = "0.1.0"
}
