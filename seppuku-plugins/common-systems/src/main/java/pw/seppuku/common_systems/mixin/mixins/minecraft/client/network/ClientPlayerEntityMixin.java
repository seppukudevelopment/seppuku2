package pw.seppuku.common_systems.mixin.mixins.minecraft.client.network;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.seppuku.client.Seppuku;
import pw.seppuku.common_systems.ecs.systems.minecraft.client.network.ClientPlayerEntityTickSystem;
import pw.seppuku.plugin.mixin.ActualThis;

@Mixin(ClientPlayerEntity.class) public abstract class ClientPlayerEntityMixin
    implements ActualThis<ClientPlayerEntity> {

  @Inject(method = "tick", at = @At("HEAD")) private void onTickHead(final CallbackInfo callback) {
    Seppuku.INSTANCE.getEngine()
        .findSystem(ClientPlayerEntityTickSystem.class)
        .getProcess()
        .invoke(actualThis());
  }
}
