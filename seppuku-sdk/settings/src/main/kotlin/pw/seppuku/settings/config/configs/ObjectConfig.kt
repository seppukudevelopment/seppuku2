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

package pw.seppuku.settings.config.configs

import pw.seppuku.settings.config.AbstractConfig
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class ObjectConfig(file: File) : AbstractConfig(file) {

  override fun writeToFile() {
    val serializer = ObjectOutputStream(file.outputStream())
    serializer.writeShort(OBJECT_CONFIG_VERSION)
    serializer.writeObject(pathToValueMap)
    serializer.flush()
    serializer.close()
  }

  @Suppress("UNCHECKED_CAST")
  override fun readFromFile() {
    val deserializer = ObjectInputStream(file.inputStream())
    deserializer.readShort()

    val deserializedPathToValueMap = deserializer.readObject() as MutableMap<String, Any?>
    pathToValueMap.clear()
    pathToValueMap.putAll(deserializedPathToValueMap)

    deserializer.close()
  }

  companion object {
    private const val OBJECT_CONFIG_VERSION: Int = 1
  }
}
