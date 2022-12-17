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

package pw.seppuku.client.ecs.systems

import pw.seppuku.client.ecs.components.Keybind
import pw.seppuku.client.ecs.components.Keybind.KeybindAction
import pw.seppuku.ecs.systems.ComponentSystem

class KeybindSystem : ComponentSystem<(key: Int, action: Int, modifiers: Int) -> Unit, Keybind>(
  Keybind::class.java
) {
  override val process: (key: Int, action: Int, modifiers: Int) -> Unit =
    { key, action, modifiers ->
      KeybindAction.fromOrNull(action)?.let { keybindAction ->
        filter { it.key == key && it.modifiers == modifiers }.forEach { it.onAction(keybindAction) }
      }
    }
}
