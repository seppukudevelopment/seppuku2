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

package pw.seppuku.ecs

abstract class AbstractEngine(
  private val systemTypeToSystemMap: MutableMap<Class<out System<*>>, System<*>> = mutableMapOf(),
  private val entities: MutableList<Entity> = mutableListOf(),
) : Engine {
  @Suppress("UNCHECKED_CAST")
  final override fun <T : System<*>> findSystemOrNull(systemClass: Class<T>): T? =
    systemTypeToSystemMap[systemClass] as T?

  final override fun addSystem(system: System<*>) {
    systemTypeToSystemMap[system::class.java] = system
    entities.forEach(system::onEntityAddedToEngine)
  }

  final override fun removeSystem(system: System<*>) {
    systemTypeToSystemMap.remove(system::class.java)
    entities.forEach(system::onEntityRemovedFromEngine)
  }

  final override fun addEntity(entity: Entity) {
    entities.add(entity)
    systemTypeToSystemMap.values.forEach { it.onEntityAddedToEngine(entity) }
  }

  final override fun removeEntity(entity: Entity) {
    entities.remove(entity)
    systemTypeToSystemMap.values.forEach { it.onEntityRemovedFromEngine(entity) }
  }

  final override fun iterator(): Iterator<Entity> = entities.iterator()
}
