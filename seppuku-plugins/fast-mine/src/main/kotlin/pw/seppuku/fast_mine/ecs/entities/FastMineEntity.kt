package pw.seppuku.fast_mine.ecs.entities

import org.lwjgl.glfw.GLFW
import pw.seppuku.client.ecs.components.minecraft.client.network.ClientPlayerInteractionManagerUpdateBlockBreakingProgress
import pw.seppuku.client.mixin.mixins.minecraft.client.network.IClientPlayerInteractionManager
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Entity
import pw.seppuku.client.ecs.components.onRelease
import pw.seppuku.settings.config.ConfigFactory
import pw.seppuku.settings.config.setting

abstract class FastMineEntity(
  configFactory: ConfigFactory
) : Entity {

  @Component val humanIdentifier = HumanIdentifier("fast_mine")

  @Component val config = configFactory.create("fast_mine")

  @Component val toggle by config.setting("toggle", Toggle())

  @Component val keybind by config.setting("keybind", onRelease(GLFW.GLFW_KEY_X) {
    toggle.state = !toggle.state
  })

  private var speed by config.setting("speed", 0.1f)

  @Component val clientPlayerInteractionManagerUpdateBlockBreakingProgress =
    ClientPlayerInteractionManagerUpdateBlockBreakingProgress { _, _ ->
      this as IClientPlayerInteractionManager
      currentBreakingProgress += speed
    }
}
