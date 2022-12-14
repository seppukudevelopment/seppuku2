package pw.seppuku.common_systems.ecs.components.minecraft.client

import net.minecraft.client.MinecraftClient
import net.minecraft.client.RunArgs

typealias MinecraftClientInitCallback = MinecraftClient.(runArgs: RunArgs) -> Unit

data class MinecraftClientInit(val callback: MinecraftClientInitCallback)