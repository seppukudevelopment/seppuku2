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

import pw.seppuku.client.ecs.components.minecraft.client.KeyboardOnKey
import pw.seppuku.client.ecs.components.minecraft.client.KeyboardOnKeyCallback
import pw.seppuku.client.ecs.systems.KeybindSystem
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.*
import pw.seppuku.ecs.systems.ArchetypeSystem

class KeyboardOnKeySystem(
  engine: Engine
) : ArchetypeSystem<KeyboardOnKeyCallback>(archetype(KeyboardOnKey::class)) {
  private val keybindSystem: KeybindSystem by lazy { engine.findSystem() }

  override val process: KeyboardOnKeyCallback = { window, key, scanCode, action, modifiers ->
    keybindSystem.process(key, action, modifiers)

    forEach {
      if (it.findComponentOrNull<Toggle>()?.state != false) {
        it.findComponent<KeyboardOnKey>().callback(this, window, key, scanCode, action, modifiers)
      }
    }
  }
}
