package pw.seppuku.no_fall.ecs.entities

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket
import org.lwjgl.glfw.GLFW
import pw.seppuku.client.ecs.components.minecraft.network.ClientConnectionSend
import pw.seppuku.client.mixin.mixins.minecraft.network.packet.c2s.play.IPlayerMoveC2SPacket
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Entity
import pw.seppuku.keybind_system.ecs.components.onRelease
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
