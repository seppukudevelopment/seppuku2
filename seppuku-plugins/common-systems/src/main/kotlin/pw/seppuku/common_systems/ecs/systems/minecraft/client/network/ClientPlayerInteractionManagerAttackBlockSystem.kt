package pw.seppuku.common_systems.ecs.systems.minecraft.client.network

import pw.seppuku.common_systems.ecs.components.minecraft.client.network.ClientPlayerInteractionManagerAttackBlock
import pw.seppuku.common_systems.ecs.components.minecraft.client.network.ClientPlayerInteractionManagerAttackBlockCallback
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.archetype
import pw.seppuku.ecs.findComponent
import pw.seppuku.ecs.findComponentOrNull
import pw.seppuku.ecs.systems.ArchetypeSystem

class ClientPlayerInteractionManagerAttackBlockSystem :
  ArchetypeSystem<ClientPlayerInteractionManagerAttackBlockCallback>(
    archetype(
      ClientPlayerInteractionManagerAttackBlock::class
    )
  ) {
  override val process: ClientPlayerInteractionManagerAttackBlockCallback = { blockPos, direction ->
    val clientPlayerInteractionManager = this

    forEach {
      if (it.findComponentOrNull<Toggle>()?.state != false) {
        it.findComponent<ClientPlayerInteractionManagerAttackBlock>()
          .callback(clientPlayerInteractionManager, blockPos, direction)
      }
    }
  }
}
