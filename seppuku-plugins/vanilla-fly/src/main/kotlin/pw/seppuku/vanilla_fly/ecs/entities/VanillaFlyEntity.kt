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

package pw.seppuku.vanilla_fly.ecs.entities

import net.minecraft.client.MinecraftClient
import org.lwjgl.glfw.GLFW
import pw.seppuku.client.ecs.components.minecraft.client.network.ClientPlayerEntityTick
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Entity
import pw.seppuku.client.ecs.components.onRelease
import pw.seppuku.settings.config.ConfigFactory
import pw.seppuku.settings.config.setting

abstract class VanillaFlyEntity(
  configFactory: ConfigFactory,
  private val minecraftClient: MinecraftClient
) : Entity {
  @Component val humanIdentifier = HumanIdentifier("vanilla_fly")

  @Component val config = configFactory.create("vanilla_fly")

  @Component val toggle by config.setting("toggle", Toggle {
    floatingTicks = 0

    if (this && minecraftClient.player?.isOnGround == true) {
      minecraftClient.player?.jump()
    }

    minecraftClient.player?.abilities?.allowFlying = this
    minecraftClient.player?.abilities?.flying = this
  })

  @Component val keybind by config.setting("keybind", onRelease(GLFW.GLFW_KEY_N) {
    toggle.state = !toggle.state
  })

  private var maxFloatingTicks by config.setting("max_floating_ticks", 60)
  private var fallSpeed by config.setting("fall_speed", 0.1)

  private var floatingTicks = 0

  @Component val clientPlayerEntityTick = ClientPlayerEntityTick {
    this.abilities.allowFlying = true

    if (isOnGround) {
      floatingTicks = 0
      return@ClientPlayerEntityTick
    }

    floatingTicks++
    if (floatingTicks > maxFloatingTicks) {
      velocity.add(0.0, -fallSpeed, 0.0)
      floatingTicks = 0
    }
  }
}
