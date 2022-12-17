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

package pw.seppuku.speed.ecs.entities

import net.minecraft.util.math.Vec3d
import org.lwjgl.glfw.GLFW
import pw.seppuku.client.ecs.components.minecraft.client.network.ClientPlayerEntityTick
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Entity
import pw.seppuku.client.ecs.components.onRelease
import pw.seppuku.settings.config.ConfigFactory
import pw.seppuku.settings.config.setting

abstract class SpeedEntity(configFactory: ConfigFactory) : Entity {
  @Component val humanIdentifier = HumanIdentifier("speed")

  @Component val config = configFactory.create("speed")

  @Component val toggle by config.setting("toggle", Toggle())

  @Component val keybind by config.setting("keybind", onRelease(GLFW.GLFW_KEY_V) {
    toggle.state = !toggle.state
  })

  private var factor: Float by config.setting("factor", 0.5f)

  @Component val clientPlayerEntityTick = ClientPlayerEntityTick {
    if (!this.isOnGround) return@ClientPlayerEntityTick

    val input = Vec3d(sidewaysSpeed.toDouble(), upwardSpeed.toDouble(), forwardSpeed.toDouble())
    updateVelocity(factor, input)
  }
}
