package pw.seppuku.common_systems.mixin.mixins.minecraft.client.gui.hud;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.seppuku.client.Seppuku;
import pw.seppuku.common_systems.ecs.systems.minecraft.client.gui.hud.InGameHudRenderSystem;
import pw.seppuku.plugin.mixin.ActualThis;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin implements ActualThis<InGameHud> {

  @Inject(method = "render", at = @At("TAIL"))
  private void onRenderTail(final MatrixStack matrices, final float tickDelta,
      final CallbackInfo callback) {
    Seppuku.INSTANCE.getEngine()
        .findSystem(InGameHudRenderSystem.class)
        .getProcess()
        .invoke(actualThis(), matrices, tickDelta);
  }
}
