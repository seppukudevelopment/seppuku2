package pw.seppuku.client.ecs.components.minecraft.client

import net.minecraft.client.MinecraftClient
import net.minecraft.client.RunArgs

internal typealias MinecraftClientInitEarlyCallback = MinecraftClient.(runArgs: RunArgs) -> Unit

internal data class MinecraftClientInitEarly(val callback: MinecraftClientInitEarlyCallback)