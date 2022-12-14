package pw.seppuku.common_systems.ecs.systems.minecraft.client.network

import pw.seppuku.common_systems.ecs.components.minecraft.client.network.ClientPlayerInteractionManagerUpdateBlockBreakingProgress
import pw.seppuku.common_systems.ecs.components.minecraft.client.network.ClientPlayerInteractionManagerUpdateBlockBreakingProgressCallback
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.archetype
import pw.seppuku.ecs.findComponent
import pw.seppuku.ecs.findComponentOrNull
import pw.seppuku.ecs.systems.ArchetypeSystem

class ClientPlayerInteractionManagerUpdateBlockBreakingProgressSystem :
  ArchetypeSystem<ClientPlayerInteractionManagerUpdateBlockBreakingProgressCallback>(
    archetype(
      ClientPlayerInteractionManagerUpdateBlockBreakingProgress::class
    )
  ) {
  override val process: ClientPlayerInteractionManagerUpdateBlockBreakingProgressCallback =
    { blockPos, direction ->
      val clientPlayerInteractionManager = this

      forEach {
        if (it.findComponentOrNull<Toggle>()?.state != false) {
          it.findComponent<ClientPlayerInteractionManagerUpdateBlockBreakingProgress>()
            .callback(clientPlayerInteractionManager, blockPos, direction)
        }
      }
    }
}
