package pw.seppuku.client.ecs.systems.minecraft.client.network

import pw.seppuku.client.ecs.components.minecraft.client.network.ClientPlayerInteractionManagerAttackBlock
import pw.seppuku.client.ecs.components.minecraft.client.network.ClientPlayerInteractionManagerAttackBlockCallback
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.archetype
import pw.seppuku.ecs.findComponent
import pw.seppuku.ecs.findComponentOrNull
import pw.seppuku.ecs.systems.ArchetypeSystem

class ClientPlayerInteractionManagerAttackBlockSystem : ArchetypeSystem<ClientPlayerInteractionManagerAttackBlockCallback>(
  archetype(
    ClientPlayerInteractionManagerAttackBlock::class
  )
) {
  override val process: ClientPlayerInteractionManagerAttackBlockCallback = { blockPos, direction ->
    forEach {
      if (it.findComponentOrNull<Toggle>()?.state != false) {
        it.findComponent<ClientPlayerInteractionManagerAttackBlock>()
          .callback(this, blockPos, direction)
      }
    }
  }
}
