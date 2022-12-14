package pw.seppuku.client.ecs.systems.minecraft.client

import pw.seppuku.client.ecs.components.minecraft.client.MinecraftClientInitEarly
import pw.seppuku.client.ecs.components.minecraft.client.MinecraftClientInitEarlyCallback
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.archetype
import pw.seppuku.ecs.findComponent
import pw.seppuku.ecs.findComponentOrNull
import pw.seppuku.ecs.systems.ArchetypeSystem

internal class MinecraftClientInitEarlySystem : ArchetypeSystem<MinecraftClientInitEarlyCallback>(
  archetype(MinecraftClientInitEarly::class)
) {

  override val process: MinecraftClientInitEarlyCallback = { runArgs ->
    val minecraftClient = this

    forEach {
      if (it.findComponentOrNull<Toggle>()?.state != false) {
        it.findComponent<MinecraftClientInitEarly>().callback(minecraftClient, runArgs)
      }
    }
  }
}
