package pw.seppuku.client.ecs.components.minecraft.client.network

import net.minecraft.client.network.ClientPlayerInteractionManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

typealias ClientPlayerInteractionManagerUpdateBlockBreakingProgressCallback = ClientPlayerInteractionManager.(blockPos: BlockPos, direction: Direction) -> Unit

data class ClientPlayerInteractionManagerUpdateBlockBreakingProgress(val callback: ClientPlayerInteractionManagerUpdateBlockBreakingProgressCallback)
