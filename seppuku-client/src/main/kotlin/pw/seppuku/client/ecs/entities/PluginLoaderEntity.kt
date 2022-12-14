package pw.seppuku.client.ecs.entities

import net.minecraft.client.MinecraftClient
import org.spongepowered.asm.launch.GlobalProperties
import org.spongepowered.asm.mixin.Mixins
import org.spongepowered.asm.mixin.transformer.Config
import pw.seppuku.client.ecs.components.minecraft.client.MinecraftClientInitEarly
import pw.seppuku.client.ecs.components.mixin.MixinOnLoad
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.di.DependencyInjector
import pw.seppuku.di.DependencyProvider
import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Engine
import pw.seppuku.ecs.Entity
import pw.seppuku.ecs.System
import pw.seppuku.ecs.codegen.EntityCodeGenerator
import java.net.URL
import java.net.URLClassLoader
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes
import java.util.*
import java.util.zip.ZipFile
import kotlin.reflect.KClass

internal abstract class PluginLoaderEntity(
    private val dependencyInjector: DependencyInjector,
    private val entityCodeGenerator: EntityCodeGenerator,
    private val engine: Engine
) : Entity {

    @Component
    val humanIdentifier = HumanIdentifier("plugin_loader")

    private val loadedPluginClasses = mutableListOf<Class<*>>()

    private lateinit var knotClassLoader: ClassLoader

    @Component
    val mixinOnLoad = MixinOnLoad {
        knotClassLoader = Thread.currentThread().contextClassLoader

        // Ghetto way of preventing ConcurrentModificationException,
        // not sure if this breaks anything... I hope not.
        GlobalProperties.put(MIXIN_CONFIGS_KEY, linkedSetOf<Config>())

        getPluginPaths().forEach {
            it.injectPluginResourcesIntoKnotClassLoader()
            it.addPluginMixinConfigurationsToMixinConfigurations()
        }
    }

    @Component
    val minecraftClientInitEarly = MinecraftClientInitEarly {
        dependencyInjector.bind(MinecraftClient::class to DependencyProvider.singleton(this))

        getPluginPaths().forEach {
            it.loadAndInjectPluginClassesIntoKnotClassLoader()
        }

        createAndAddPluginSystemsToEngine()
        createAndAddPluginEntitiesToEngine()
    }

    @Suppress("UNCHECKED_CAST")
    private fun createAndAddPluginSystemsToEngine() {
        loadedPluginClasses
            .filter { System::class.java.isAssignableFrom(it) }
            .map { it.kotlin as KClass<out System<*>> }
            .map { dependencyInjector.create(it) }
            .forEach(engine::addSystem)
    }

    @Suppress("UNCHECKED_CAST")
    private fun createAndAddPluginEntitiesToEngine() {
        loadedPluginClasses
            .filter { Entity::class.java.isAssignableFrom(it) }
            .map { it.kotlin as KClass<out Entity> }
            .map { entityCodeGenerator.generateImplementationClass(it, knotClassLoader) }
            .map { dependencyInjector.create(it) }
            .forEach(engine::addEntity)
    }

    private fun getPluginPaths(): List<Path> {
        val pluginPaths = mutableListOf<Path>()

        // Can't use Kotlin walkFileTree because Kotlin is not in the KnotClassLoader yet
        Files.walkFileTree(Path.of("seppuku", "plugins"), EnumSet.of(FileVisitOption.FOLLOW_LINKS), 1,
            object : SimpleFileVisitor<Path>() {
                override fun visitFile(path: Path, attrs: BasicFileAttributes): FileVisitResult {
                    pluginPaths.add(path)
                    return FileVisitResult.CONTINUE
                }
            })

        return pluginPaths
    }

    private fun Path.injectPluginResourcesIntoKnotClassLoader() {
        val knotUrlLoader = knotClassLoader::class.java
            .getDeclaredField("urlLoader")
            .apply { isAccessible = true }
            .get(knotClassLoader)

        knotUrlLoader::class.java
            .getDeclaredMethod("addURL", URL::class.java)
            .apply { isAccessible = true }
            .invoke(knotUrlLoader, toUri().toURL())
    }

    private fun Path.loadAndInjectPluginClassesIntoKnotClassLoader() {
        val pluginZipFile = ZipFile(toFile())

        val pluginClassLoader = URLClassLoader(arrayOf(toUri().toURL()), knotClassLoader)
        val pluginClasses = pluginZipFile
            .entries()
            .toList()
            .filter { it.name.endsWith(".class") }
            // Ghetto fix but it works for now, ideally read the class
            // bytes and check for the Mixin annotation
            .filterNot { it.name.contains("Mixin") }
            .map {
                pluginClassLoader.loadClass(it.name.substring(0, it.name.length - 6).replace("/", "."))
            }

        loadedPluginClasses.addAll(pluginClasses)
    }

    private fun Path.addPluginMixinConfigurationsToMixinConfigurations() {
        val pluginZipFile = ZipFile(toFile())
        pluginZipFile.entries().toList()
            .filter { it.name.endsWith(".mixins.json") }
            .forEach {
                Mixins.addConfiguration(it.name)
            }
    }

    private companion object {
        private val MIXIN_CONFIGS_KEY by lazy {
            GlobalProperties.Keys.of("${GlobalProperties.Keys.CONFIGS}.queue")
        }
    }
}