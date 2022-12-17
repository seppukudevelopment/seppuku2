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

package pw.seppuku.client

import pw.seppuku.client.ecs.engines.SeppukuEngine
import pw.seppuku.client.ecs.entities.ConfigPersistenceEntity
import pw.seppuku.client.ecs.entities.PluginLoaderEntity
import pw.seppuku.client.ecs.systems.KeybindSystem
import pw.seppuku.client.ecs.systems.minecraft.client.KeyboardOnKeySystem
import pw.seppuku.client.ecs.systems.minecraft.client.MinecraftClientInitEarlySystem
import pw.seppuku.client.ecs.systems.minecraft.client.MinecraftClientInitSystem
import pw.seppuku.client.ecs.systems.minecraft.client.hud.gui.InGameHudRenderSystem
import pw.seppuku.client.ecs.systems.minecraft.client.network.ClientPlayerEntityTickSystem
import pw.seppuku.client.ecs.systems.minecraft.client.network.ClientPlayerInteractionManagerAttackBlockSystem
import pw.seppuku.client.ecs.systems.minecraft.client.network.ClientPlayerInteractionManagerUpdateBlockBreakingProgressSystem
import pw.seppuku.client.ecs.systems.minecraft.network.ClientConnectionHandlePacketSystem
import pw.seppuku.client.ecs.systems.minecraft.network.ClientConnectionSendSystem
import pw.seppuku.client.settings.config.factories.SeppukuConfigFactory
import pw.seppuku.di.DependencyInjector
import pw.seppuku.di.DependencyProvider
import pw.seppuku.di.get
import pw.seppuku.di.injectors.SimpleDependencyInjector
import pw.seppuku.ecs.Engine
import pw.seppuku.ecs.Entity
import pw.seppuku.ecs.System
import pw.seppuku.ecs.codegen.EntityCodeGenerator
import pw.seppuku.settings.config.ConfigFactory
import java.io.File
import kotlin.reflect.KClass

object Seppuku {

  private val dependencyInjector = SimpleDependencyInjector().apply {
    bind(DependencyInjector::class to DependencyProvider.singleton(this))
    bind(EntityCodeGenerator::class to DependencyProvider.singleton(EntityCodeGenerator(File("seppuku/codegen/"))))
    bind(ConfigFactory::class to DependencyProvider.singleton(SeppukuConfigFactory()))
    bind(Engine::class to DependencyProvider.singleton(SeppukuEngine()))
  }

  val engine: Engine by lazy { dependencyInjector.get() }

  private val entityCodeGenerator: EntityCodeGenerator by lazy { dependencyInjector.get() }

  init {
    createAndAddSystemsToEngine(
      KeybindSystem::class,

      // minecraft.client
      KeyboardOnKeySystem::class,
      MinecraftClientInitEarlySystem::class,
      MinecraftClientInitSystem::class,

      // minecraft.client.gui.hud
      InGameHudRenderSystem::class,

      // minecraft.client.network
      ClientPlayerEntityTickSystem::class,
      ClientPlayerInteractionManagerAttackBlockSystem::class,
      ClientPlayerInteractionManagerUpdateBlockBreakingProgressSystem::class,

      // minecraft.network
      ClientConnectionHandlePacketSystem::class,
      ClientConnectionSendSystem::class,
    )

    generateImplementationCreateAndAddEntitiesToEngine(
      PluginLoaderEntity::class, ConfigPersistenceEntity::class
    )
  }

  private fun createAndAddSystemsToEngine(vararg systemClasses: KClass<out System<*>>) {
    systemClasses.forEach(this::createAndAddSystemToEngine)
  }

  private fun createAndAddSystemToEngine(systemClass: KClass<out System<*>>) {
    val system = dependencyInjector.create(systemClass)
    engine.addSystem(system)
  }

  private fun generateImplementationCreateAndAddEntitiesToEngine(vararg entityClasses: KClass<out Entity>) {
    entityClasses.forEach(this::generateImplementationCreateAndAddEntityToEngine)
  }

  private fun generateImplementationCreateAndAddEntityToEngine(entityClass: KClass<out Entity>) {
    val entityImplementationClass = entityCodeGenerator.generateImplementationClass(entityClass)
    val entity = dependencyInjector.create(entityImplementationClass)
    engine.addEntity(entity)
  }
}
