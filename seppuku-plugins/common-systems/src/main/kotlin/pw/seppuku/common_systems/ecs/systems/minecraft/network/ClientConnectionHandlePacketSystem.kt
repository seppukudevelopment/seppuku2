package pw.seppuku.common_systems.ecs.systems.minecraft.network

import pw.seppuku.components.Toggle
import pw.seppuku.ecs.archetype
import pw.seppuku.ecs.findComponent
import pw.seppuku.ecs.findComponentOrNull
import pw.seppuku.ecs.systems.ArchetypeSystem

class ClientConnectionHandlePacketSystem : ArchetypeSystem<ClientConnectionHandlePacketCallback>(
  archetype(ClientConnectionHandlePacket::class)
) {
  override val process: ClientConnectionHandlePacketCallback = { packet, packetListener ->
    forEach {
      if (it.findComponentOrNull<Toggle>()?.state != false) {
        it.findComponent<ClientConnectionHandlePacket>().callback(packet, packetListener)
      }
    }
  }
}
