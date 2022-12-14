package pw.seppuku.common_systems.ecs.systems.minecraft.network

import pw.seppuku.components.Toggle
import pw.seppuku.ecs.archetype
import pw.seppuku.ecs.findComponent
import pw.seppuku.ecs.findComponentOrNull
import pw.seppuku.ecs.systems.ArchetypeSystem

class ClientConnectionSendSystem : ArchetypeSystem<ClientConnectionSendCallback>(
  archetype(ClientConnectionSend::class)
) {
  override val process: ClientConnectionSendCallback = { packet ->
    forEach {
      if (it.findComponentOrNull<Toggle>()?.state != false) {
        it.findComponent<ClientConnectionSend>().callback(this, packet)
      }
    }
  }
}
