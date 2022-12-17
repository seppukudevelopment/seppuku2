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

package pw.seppuku.client.ecs.systems.minecraft.client.network

import pw.seppuku.client.ecs.components.minecraft.client.network.ClientPlayerInteractionManagerAttackBlock
import pw.seppuku.client.ecs.components.minecraft.client.network.ClientPlayerInteractionManagerAttackBlockCallback
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.archetype
import pw.seppuku.ecs.findComponent
import pw.seppuku.ecs.findComponentOrNull
import pw.seppuku.ecs.systems.ArchetypeSystem

class ClientPlayerInteractionManagerAttackBlockSystem : ArchetypeSystem<ClientPlayerInteractionManagerAttackBlockCallback>(
  archetype(
    ClientPlayerInteractionManagerAttackBlock::class
  )
) {
  override val process: ClientPlayerInteractionManagerAttackBlockCallback = { blockPos, direction ->
    forEach {
      if (it.findComponentOrNull<Toggle>()?.state != false) {
        it.findComponent<ClientPlayerInteractionManagerAttackBlock>()
          .callback(this, blockPos, direction)
      }
    }
  }
}
