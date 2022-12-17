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

package pw.seppuku.settings

import pw.seppuku.settings.config.Config
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SettingDelegateProperty<K, V : Any>(
  private val config: Config,
  private val path: String,
  private val defaultValue: V,
  private val changeCallback: ((V) -> Unit)? = null
) : ReadWriteProperty<K, V> {

  override fun getValue(
    thisRef: K,
    property: KProperty<*>
  ): V {
    val value = config.read<V>(path)
    if (value != null)
      return value

    config.write(path, defaultValue)
    return defaultValue
  }

  override fun setValue(
    thisRef: K,
    property: KProperty<*>,
    value: V
  ) {
    config.write(path, value)
    changeCallback?.invoke(value)
  }
}
