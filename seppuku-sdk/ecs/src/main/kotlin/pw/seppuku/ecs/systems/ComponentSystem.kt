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

package pw.seppuku.ecs.systems

import pw.seppuku.ecs.AbstractSystem
import pw.seppuku.ecs.Entity

abstract class ComponentSystem<T, C : Any>(
  private val componentClass: Class<C>,
  private val components: MutableList<C> = mutableListOf()
) : AbstractSystem<T>({ hasComponent(componentClass) }), Iterable<C> {
  final override fun onEntityAddedToSystem(entity: Entity) {
    components.add(entity.findComponent(componentClass))
  }

  final override fun onEntityRemovedFromSystem(entity: Entity) {
    components.remove(entity.findComponent(componentClass))
  }

  final override fun iterator(): Iterator<C> = components.iterator()
}
