package pw.seppuku.common_systems.ecs.systems.minecraft.network

import net.minecraft.network.ClientConnection
import net.minecraft.network.Packet

typealias ClientConnectionSendCallback = ClientConnection.(packet: Packet<*>) -> Unit

data class ClientConnectionSend(val callback: ClientConnectionSendCallback)