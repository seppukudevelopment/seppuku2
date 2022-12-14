package pw.seppuku.common_systems.mixin.mixins.minecraft.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.seppuku.client.Seppuku;
import pw.seppuku.common_systems.ecs.systems.minecraft.client.MinecraftClientInitSystem;
import pw.seppuku.plugin.mixin.ActualThis;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin implements ActualThis<MinecraftClient> {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInitTail(final RunArgs runArgs, final CallbackInfo callback) {
        Seppuku.INSTANCE.getEngine().findSystem(MinecraftClientInitSystem.class).getProcess().invoke(actualThis(), runArgs);
    }
}
