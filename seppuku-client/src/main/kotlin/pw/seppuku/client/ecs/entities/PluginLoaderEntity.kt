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

package pw.seppuku.client.ecs.entities

import net.fabricmc.loader.impl.launch.FabricLauncherBase
import net.minecraft.client.MinecraftClient
import pw.seppuku.client.ecs.components.minecraft.client.MinecraftClientInitEarly
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.di.DependencyInjector
import pw.seppuku.di.DependencyProvider
import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Engine
import pw.seppuku.ecs.Entity
import pw.seppuku.ecs.System
import pw.seppuku.ecs.codegen.EntityCodeGenerator
import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import kotlin.reflect.KClass

internal abstract class PluginLoaderEntity(
  private val dependencyInjector: DependencyInjector,
  private val entityCodeGenerator: EntityCodeGenerator,
  private val engine: Engine
) : Entity {
  @Component val humanIdentifier = HumanIdentifier("plugin_loader")

  @Component val minecraftClientInitEarly = MinecraftClientInitEarly { _ ->
    dependencyInjector.bind(MinecraftClient::class to DependencyProvider.singleton(this))

    val knotClassLoader = FabricLauncherBase.getLauncher().targetClassLoader
    val pluginClasses = findPluginClasses(knotClassLoader)
    pluginClasses.createAndAddSystemsToEngine()
    pluginClasses.generateImplementationCreateAndAddEntitiesToEngine()
  }

  private fun findPluginClasses(classLoader: ClassLoader): List<Class<*>> =
    File("mods").walkBottomUp()
      .filter { it.isFile }
      .filter { it.extension == "jar" || it.extension == "zip" }
      .map { ZipFile(it) }
      .filter { it.getEntry("seppuku.json") != null }
      .map { it.entries().toList() }
      .flatten()
      .filter { it.isClass() }
      .filter { it.shouldLoadClass() }
      .map { it.loadClass(classLoader) }
      .toList()

  private fun List<Class<*>>.createAndAddSystemsToEngine() {
    filter { it.isSystem() }.map { it.createSystem() }.forEach { engine.addSystem(it) }
  }

  private fun List<Class<*>>.generateImplementationCreateAndAddEntitiesToEngine() {
    filter { it.isEntity() }.map { it.generateEntityImplementationClass() }
      .map { it.createEntity() }
      .forEach { engine.addEntity(it) }
  }

  private fun ZipEntry.isClass(): Boolean = name.endsWith(".class")

  private fun ZipEntry.shouldLoadClass(): Boolean = !name.removeSuffix(".class").endsWith("Mixin")

  private fun ZipEntry.loadClass(classLoader: ClassLoader) =
    classLoader.loadClass(name.removeSuffix(".class").replace("/", "."))

  private fun Class<*>.isSystem(): Boolean = System::class.java.isAssignableFrom(this)

  @Suppress("UNCHECKED_CAST") private fun Class<*>.createSystem(): System<*> =
    dependencyInjector.create(this.kotlin as KClass<out System<*>>)

  private fun Class<*>.isEntity(): Boolean = Entity::class.java.isAssignableFrom(this)

  @Suppress("UNCHECKED_CAST")
  private fun Class<*>.generateEntityImplementationClass(): KClass<out Entity> =
    entityCodeGenerator.generateImplementationClass(this.kotlin as KClass<out Entity>)

  private fun KClass<out Entity>.createEntity(): Entity = dependencyInjector.create(this)
}
