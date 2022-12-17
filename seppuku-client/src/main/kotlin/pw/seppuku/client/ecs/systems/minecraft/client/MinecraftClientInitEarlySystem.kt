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

package pw.seppuku.client.ecs.systems.minecraft.client

import pw.seppuku.client.ecs.components.minecraft.client.MinecraftClientInitEarly
import pw.seppuku.client.ecs.components.minecraft.client.MinecraftClientInitEarlyCallback
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.archetype
import pw.seppuku.ecs.findComponent
import pw.seppuku.ecs.findComponentOrNull
import pw.seppuku.ecs.systems.ArchetypeSystem

internal class MinecraftClientInitEarlySystem : ArchetypeSystem<MinecraftClientInitEarlyCallback>(
  archetype(MinecraftClientInitEarly::class)
) {

  override val process: MinecraftClientInitEarlyCallback = { runArgs ->
    val minecraftClient = this

    forEach {
      if (it.findComponentOrNull<Toggle>()?.state != false) {
        it.findComponent<MinecraftClientInitEarly>().callback(minecraftClient, runArgs)
      }
    }
  }
}
