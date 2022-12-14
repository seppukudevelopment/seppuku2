package pw.seppuku.common_systems.ecs.components.minecraft.client.network

import net.minecraft.client.network.ClientPlayerEntity

typealias ClientPlayerEntityTickCallback = ClientPlayerEntity.() -> Unit

data class ClientPlayerEntityTick(val callback: ClientPlayerEntityTickCallback)