plugins {
    alias(mainLibs.plugins.kotlin.jvm)
}

dependencies {
    mainLibs {
        implementation(kotlin.stdlib)
    }
}