package pw.seppuku.client

import pw.seppuku.client.ecs.engines.SeppukuEngine
import pw.seppuku.client.ecs.entities.PluginLoaderEntity
import pw.seppuku.client.ecs.systems.minecraft.client.MinecraftClientInitEarlySystem
import pw.seppuku.client.ecs.systems.mixin.MixinOnLoadSystem
import pw.seppuku.client.settings.config.factories.SeppukuConfigFactory
import pw.seppuku.di.DependencyInjector
import pw.seppuku.di.DependencyProvider
import pw.seppuku.di.create
import pw.seppuku.di.get
import pw.seppuku.di.injectors.SimpleDependencyInjector
import pw.seppuku.ecs.Engine
import pw.seppuku.ecs.Entity
import pw.seppuku.ecs.System
import pw.seppuku.ecs.codegen.EntityCodeGenerator
import pw.seppuku.settings.config.ConfigFactory
import java.io.File

object Seppuku {

    private val dependencyInjector = SimpleDependencyInjector().apply {
        bind(DependencyInjector::class to DependencyProvider.singleton(this))
        bind(EntityCodeGenerator::class to DependencyProvider.singleton(EntityCodeGenerator(File("seppuku/codegen/"))))
        bind(ConfigFactory::class to DependencyProvider.singleton(SeppukuConfigFactory()))
        bind(Engine::class to DependencyProvider.singleton(SeppukuEngine()))
    }

    private val entityCodeGenerator: EntityCodeGenerator by lazy { dependencyInjector.get() }

    val engine: Engine by lazy { dependencyInjector.get() }

    init {
        createAndAddSystemToEngine<MixinOnLoadSystem>()
        createAndAddSystemToEngine<MinecraftClientInitEarlySystem>()

        generateImplementationCreateAndAddEntityToEngine<PluginLoaderEntity>()
    }

    private inline fun <reified T : System<*>> createAndAddSystemToEngine() {
        val system = dependencyInjector.create<T>()
        engine.addSystem(system)
    }

    private inline fun <reified T : Entity> generateImplementationCreateAndAddEntityToEngine() {
        val entityImplementation = entityCodeGenerator.generateImplementationClass(T::class)
        val entity = dependencyInjector.create(entityImplementation)
        engine.addEntity(entity)
    }
}