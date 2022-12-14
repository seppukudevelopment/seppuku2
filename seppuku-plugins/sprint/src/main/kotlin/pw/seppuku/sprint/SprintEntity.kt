package pw.seppuku.sprint

import net.minecraft.client.MinecraftClient
import org.lwjgl.glfw.GLFW
import pw.seppuku.common_systems.ecs.components.minecraft.client.network.ClientPlayerEntityTick
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.components.Toggle
import pw.seppuku.components.onRelease
import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Entity
import pw.seppuku.settings.config.ConfigFactory
import pw.seppuku.settings.config.setting

abstract class SprintEntity(
  configFactory: ConfigFactory,
  private val minecraftClient: MinecraftClient
) : Entity {

  @Component
  val humanIdentifier = HumanIdentifier("sprint")

  @Component
  val config = configFactory.create("sprint")

  @Component
  val toggle by config.setting("toggle", Toggle {
    minecraftClient.options.sprintKey.isPressed = this
  })

  @Component
  val keybind by config.setting("keybind", onRelease(GLFW.GLFW_KEY_B, 0) {
    toggle.state = !toggle.state
  })

  @Component
  val clientPlayerEntityTick = ClientPlayerEntityTick {
    minecraftClient.options.sprintKey.isPressed = true
  }
}
