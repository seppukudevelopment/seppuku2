package pw.seppuku.vanilla_fly.ecs.entities

import net.minecraft.client.MinecraftClient
import org.lwjgl.glfw.GLFW
import pw.seppuku.common_systems.ecs.components.minecraft.client.network.ClientPlayerEntityTick
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Entity
import pw.seppuku.keybind_system.ecs.components.onRelease
import pw.seppuku.settings.config.ConfigFactory
import pw.seppuku.settings.config.setting

abstract class VanillaFlyEntity(
  configFactory: ConfigFactory,
  private val minecraftClient: MinecraftClient
) : Entity {
  @Component val humanIdentifier = HumanIdentifier("vanilla_fly")

  @Component val config = configFactory.create("sprint")

  @Component val toggle by config.setting("toggle", Toggle {
    if (this && minecraftClient.player?.isOnGround == true) {
      minecraftClient.player?.jump()
    }

    minecraftClient.player?.abilities?.allowFlying = this
    minecraftClient.player?.abilities?.flying = this
  })

  @Component val keybind = onRelease(GLFW.GLFW_KEY_N) {
    toggle.state = !toggle.state
  }

  @Component val clientPlayerEntityTick = ClientPlayerEntityTick {
    this.abilities.allowFlying = true
  }
}
