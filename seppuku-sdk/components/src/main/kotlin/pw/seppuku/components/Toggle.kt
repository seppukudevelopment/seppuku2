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

package pw.seppuku.components

import java.io.Serializable

data class Toggle(
  private val initialState: Boolean = false,
  @Transient private val onStateChange: Boolean.() -> Unit = {}
) : Serializable {
  var state: Boolean = initialState
    set(value) {
      field = value
      onStateChange(value)
    }

  override fun equals(other: Any?) = when (other) {
    is Boolean -> other == state
    else -> super.equals(other)
  }

  override fun hashCode() = state.hashCode()

  override fun toString() = state.toString()
}
