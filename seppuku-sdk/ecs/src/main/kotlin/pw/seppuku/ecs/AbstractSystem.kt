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

abstract class AbstractSystem<T>(
  private val entityPredicate: EntityPredicate,
) : System<T> {
  protected abstract fun onEntityAddedToSystem(entity: Entity)

  protected abstract fun onEntityRemovedFromSystem(entity: Entity)

  final override fun onEntityAddedToEngine(entity: Entity) {
    if (entity.entityPredicate()) {
      onEntityAddedToSystem(entity)
    }
  }

  final override fun onEntityRemovedFromEngine(entity: Entity) {
    if (entity.entityPredicate()) {
      onEntityRemovedFromSystem(entity)
    }
  }
}
