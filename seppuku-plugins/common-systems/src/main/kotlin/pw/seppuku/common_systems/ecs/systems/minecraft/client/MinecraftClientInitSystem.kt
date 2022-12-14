package pw.seppuku.common_systems.ecs.systems.minecraft.client

import pw.seppuku.common_systems.ecs.components.minecraft.client.MinecraftClientInit
import pw.seppuku.common_systems.ecs.components.minecraft.client.MinecraftClientInitCallback
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.archetype
import pw.seppuku.ecs.findComponent
import pw.seppuku.ecs.findComponentOrNull
import pw.seppuku.ecs.systems.ArchetypeSystem

class MinecraftClientInitSystem :
  ArchetypeSystem<MinecraftClientInitCallback>(archetype(MinecraftClientInit::class)) {
  override val process: MinecraftClientInitCallback = { runArgs ->
    val minecraftClient = this

    forEach {
      if (it.findComponentOrNull<Toggle>()?.state != false) {
        it.findComponent<MinecraftClientInit>().callback(minecraftClient, runArgs)
      }
    }
  }
}
