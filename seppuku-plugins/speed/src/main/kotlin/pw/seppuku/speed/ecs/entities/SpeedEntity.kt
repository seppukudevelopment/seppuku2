package pw.seppuku.speed.ecs.entities

import net.minecraft.util.math.Vec3d
import org.lwjgl.glfw.GLFW
import pw.seppuku.common_systems.ecs.components.minecraft.client.network.ClientPlayerEntityTick
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Entity
import pw.seppuku.keybind_system.ecs.components.onRelease
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
