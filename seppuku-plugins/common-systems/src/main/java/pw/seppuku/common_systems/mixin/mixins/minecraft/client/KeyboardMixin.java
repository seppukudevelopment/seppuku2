package pw.seppuku.common_systems.mixin.mixins.minecraft.client;

import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.seppuku.client.Seppuku;
import pw.seppuku.common_systems.ecs.systems.minecraft.client.KeyboardOnKeySystem;
import pw.seppuku.plugin.mixin.ActualThis;

@Mixin(Keyboard.class) public abstract class KeyboardMixin implements ActualThis<Keyboard> {

  @Inject(method = "onKey", at = @At("HEAD"))
  private void onKeyHead(final long window, final int key, final int scanCode, final int action,
      final int modifiers, final CallbackInfo callback) {
    Seppuku.INSTANCE.getEngine()
        .findSystem(KeyboardOnKeySystem.class)
        .getProcess()
        .invoke(actualThis(), window, key, scanCode, action, modifiers);
  }
}
