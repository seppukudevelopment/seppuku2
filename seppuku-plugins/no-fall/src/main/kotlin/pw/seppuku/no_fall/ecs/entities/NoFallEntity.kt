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

package pw.seppuku.no_fall.ecs.entities

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket
import org.lwjgl.glfw.GLFW
import pw.seppuku.client.ecs.components.minecraft.network.ClientConnectionSend
import pw.seppuku.client.mixin.mixins.minecraft.network.packet.c2s.play.IPlayerMoveC2SPacket
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Entity
import pw.seppuku.client.ecs.components.onRelease
import pw.seppuku.settings.config.ConfigFactory
import pw.seppuku.settings.config.setting

abstract class NoFallEntity(configFactory: ConfigFactory) : Entity {
  @Component val humanIdentifier = HumanIdentifier("no_fall")

  @Component val config = configFactory.create("no_fall")

  @Component val toggle by config.setting("toggle", Toggle())

  @Component val keybind by config.setting("keybind", onRelease(GLFW.GLFW_KEY_C) {
    toggle.state = !toggle.state
  })

  @Component val clientConnectionSend = ClientConnectionSend { packet ->
    if (packet is PlayerMoveC2SPacket) {
      packet as IPlayerMoveC2SPacket
      packet.setOnGround(false)
    }
  }
}
