package pw.seppuku.client.mixin.mixins.minecraft.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.seppuku.client.Seppuku;
import pw.seppuku.client.ecs.systems.minecraft.client.MinecraftClientInitEarlySystem;
import pw.seppuku.plugin.mixin.ActualThis;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin implements ActualThis<MinecraftClient> {

  @Inject(method = "<init>", at = @At("TAIL"))
  private void onInitTail(final RunArgs runArgs, final CallbackInfo callback) {
    Seppuku.INSTANCE.getEngine()
        .findSystem(MinecraftClientInitEarlySystem.class)
        .getProcess()
        .invoke(actualThis(), runArgs);
  }
}
