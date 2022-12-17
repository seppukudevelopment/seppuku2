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

package pw.seppuku.settings.config.factories

import pw.seppuku.settings.config.Config
import pw.seppuku.settings.config.ConfigFactory
import java.io.File
import kotlin.reflect.KClass

abstract class CascadingConfigFactory<T : Config>(
  private val configClass: KClass<T>,
  private val directory: String,
  private val fileExtension: String? = null
) : ConfigFactory {

  init {
    File(directory).mkdirs()
  }

  override fun create(path: String): Config =
    configClass.constructors.first().call(
      File(buildFilePath(path))
    )

  private fun buildFilePath(path: String): String =
    "$directory/${path.replace(".", "/")}" +
      if (fileExtension != null) ".$fileExtension" else ""
}
