package pw.seppuku.client.ecs.components.minecraft.network

import net.minecraft.network.Packet
import net.minecraft.network.listener.PacketListener

typealias ClientConnectionHandlePacketCallback = (packet: Packet<*>, packetListener: PacketListener?) -> Unit

data class ClientConnectionHandlePacket(val callback: ClientConnectionHandlePacketCallback)
