package pw.seppuku.heads_up_display

import net.minecraft.client.MinecraftClient
import pw.seppuku.common_systems.ecs.components.minecraft.client.hud.gui.InGameHudRender
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.*

abstract class HeadsUpDisplayEntity(
    private val engine: Engine,
    private val minecraftClient: MinecraftClient,
) : Entity {

    @Component
    val humanIdentifier = HumanIdentifier("heads_up_display")

    @Component
    val inGameHudRender = InGameHudRender { matrices, _ ->
        if (minecraftClient.options.debugEnabled) return@InGameHudRender

        textRenderer.run {
            drawWithShadow(matrices, "seppuku", 2f, 2f, 0xffffff)
            drawWithShadow(matrices, "${minecraftClient.player?.blockPos?.toShortString()}", 2f, 11f, 0xffffff)

            engine.hasComponent<Toggle> { state }.mapComponent<HumanIdentifier>().map(HumanIdentifier::toString)
                .forEachIndexed { index, humanIdentifier ->
                    val x = 2f
                    val y = 11f + (index + 1) * 9
                    drawWithShadow(matrices, humanIdentifier, x, y, 0xffffff)
                }
        }
    }
}