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

package pw.seppuku.client.ecs.components

import pw.seppuku.client.ecs.components.Keybind.KeybindAction.HOLD
import pw.seppuku.client.ecs.components.Keybind.KeybindAction.PRESS
import pw.seppuku.client.ecs.components.Keybind.KeybindAction.RELEASE
import java.io.Serializable

data class Keybind(
  val key: Int,
  val modifiers: Int,
  @Transient val onAction: (KeybindAction) -> Unit,
) : Serializable {

  enum class KeybindAction {
    RELEASE,
    PRESS,
    HOLD;

    internal companion object {
      internal fun fromOrNull(action: Int): KeybindAction? = when (action) {
        0 -> RELEASE
        1 -> PRESS
        2 -> HOLD
        else -> null
      }
    }
  }

  companion object {
    internal fun onRelease(onReleaseAction: () -> Unit): (KeybindAction) -> Unit = {
      if (it == RELEASE) onReleaseAction()
    }

    internal fun onPress(onPressAction: () -> Unit): (KeybindAction) -> Unit = {
      if (it == PRESS) onPressAction()
    }

    internal fun onHold(onHoldAction: () -> Unit): (KeybindAction) -> Unit = {
      if (it == HOLD) onHoldAction()
    }
  }
}
