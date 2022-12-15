package pw.seppuku.auto_tool.ecs.entities

import net.minecraft.block.BlockState
import net.minecraft.client.MinecraftClient
import net.minecraft.util.math.BlockPos
import org.lwjgl.glfw.GLFW
import pw.seppuku.common_systems.ecs.components.minecraft.client.network.ClientPlayerInteractionManagerAttackBlock
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Entity
import pw.seppuku.keybind_system.ecs.components.onRelease
import pw.seppuku.settings.config.ConfigFactory
import pw.seppuku.settings.config.setting

abstract class AutoToolEntity(
  configFactory: ConfigFactory,
  private val minecraftClient: MinecraftClient
) : Entity {

  @Component val humanIdentifier = HumanIdentifier("auto_tool")

  @Component val config = configFactory.create("auto_tool")

  @Component val toggle by config.setting("toggle", Toggle())

  @Component val keybind = onRelease(GLFW.GLFW_KEY_Z) {
    toggle.state = !toggle.state
  }

  @Component val clientPlayerInteractionManagerAttackBlock =
    ClientPlayerInteractionManagerAttackBlock { blockPos, _ ->
      val blockState = blockPos.getBlockState() ?: return@ClientPlayerInteractionManagerAttackBlock
      val bestRatedSlot =
        blockState.getBestRatedSlot() ?: return@ClientPlayerInteractionManagerAttackBlock
      bestRatedSlot.selectSlot()
    }

  private fun BlockPos.getBlockState(): BlockState? =
    this@AutoToolEntity.minecraftClient.world?.getBlockState(this)

  private fun BlockState.getBestRatedSlot(): Int? = (0..9).maxByOrNull {
    this.getSlotRating(it)
  }

  private fun BlockState.getSlotRating(index: Int): Float =
    this@AutoToolEntity.minecraftClient.player?.inventory?.getStack(index)
      ?.getMiningSpeedMultiplier(this) ?: 0f

  private fun Int.selectSlot() {
    val player = this@AutoToolEntity.minecraftClient.player ?: return
    player.inventory.selectedSlot = this
  }
}
