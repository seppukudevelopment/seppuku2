package pw.seppuku.client.ecs.systems.minecraft.client.network

import pw.seppuku.client.ecs.components.minecraft.client.network.ClientPlayerEntityTick
import pw.seppuku.client.ecs.components.minecraft.client.network.ClientPlayerEntityTickCallback
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.archetype
import pw.seppuku.ecs.findComponent
import pw.seppuku.ecs.findComponentOrNull
import pw.seppuku.ecs.systems.ArchetypeSystem

class ClientPlayerEntityTickSystem : ArchetypeSystem<ClientPlayerEntityTickCallback>(
  archetype(
    ClientPlayerEntityTick::class
  )
) {
  override val process: ClientPlayerEntityTickCallback = {
    forEach {
      if (it.findComponentOrNull<Toggle>()?.state != false) {
        it.findComponent<ClientPlayerEntityTick>().callback(this)
      }
    }
  }
}
