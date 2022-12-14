package pw.seppuku.common_systems.ecs.components.minecraft.client.hud.gui

import net.minecraft.client.gui.hud.InGameHud
import net.minecraft.client.util.math.MatrixStack

typealias InGameHudRenderCallback = InGameHud.(matrices: MatrixStack, tickDelta: Float) -> Unit

data class InGameHudRender(val callback: InGameHudRenderCallback)