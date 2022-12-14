package pw.seppuku.common_systems.ecs.systems.minecraft.network

import net.minecraft.network.Packet
import net.minecraft.network.listener.PacketListener

typealias ClientConnectionHandlePacketCallback = (packet: Packet<*>, packetListener: PacketListener?) -> Unit

data class ClientConnectionHandlePacket(val callback: ClientConnectionHandlePacketCallback)