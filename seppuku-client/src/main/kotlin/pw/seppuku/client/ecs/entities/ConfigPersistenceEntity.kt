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

package pw.seppuku.client.ecs.entities

import pw.seppuku.client.ecs.components.minecraft.client.MinecraftClientInitEarly
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Engine
import pw.seppuku.ecs.Entity
import pw.seppuku.ecs.mapComponent
import pw.seppuku.settings.config.Config
import kotlin.concurrent.thread

internal abstract class ConfigPersistenceEntity(
  private val engine: Engine
) : Entity {

  @Component val humanIdentifier = HumanIdentifier("config_persistence")

  @Component val minecraftClientInitEarly = MinecraftClientInitEarly {
    engine.mapComponent<Config>().filter(Config::exists).forEach {
      it.readFromFile()
    }

    Runtime.getRuntime().addShutdownHook(thread(start = false) {
      engine.mapComponent<Config>().forEach { it.writeToFile() }
    })
  }
}
