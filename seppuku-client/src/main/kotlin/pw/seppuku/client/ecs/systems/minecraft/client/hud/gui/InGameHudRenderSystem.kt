package pw.seppuku.client.ecs.systems.minecraft.client.hud.gui

import pw.seppuku.client.ecs.components.minecraft.client.hud.gui.InGameHudRender
import pw.seppuku.client.ecs.components.minecraft.client.hud.gui.InGameHudRenderCallback
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.archetype
import pw.seppuku.ecs.findComponent
import pw.seppuku.ecs.findComponentOrNull
import pw.seppuku.ecs.systems.ArchetypeSystem

class InGameHudRenderSystem : ArchetypeSystem<InGameHudRenderCallback>(archetype(InGameHudRender::class)) {
  override val process: InGameHudRenderCallback = { matrices, tickDelta ->
    forEach {
      if (it.findComponentOrNull<Toggle>()?.state != false) {
        it.findComponent<InGameHudRender>().callback(this, matrices, tickDelta)
      }
    }
  }
}
