package pw.seppuku.client.ecs.components.minecraft.network

import net.minecraft.network.ClientConnection
import net.minecraft.network.Packet

typealias ClientConnectionSendCallback = ClientConnection.(packet: Packet<*>) -> Unit

data class ClientConnectionSend(val callback: ClientConnectionSendCallback)
