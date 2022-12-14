package pw.seppuku.common_systems.mixin.mixins.minecraft.client.network;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pw.seppuku.client.Seppuku;
import pw.seppuku.common_systems.ecs.systems.minecraft.client.network.ClientPlayerInteractionManagerAttackBlockSystem;
import pw.seppuku.common_systems.ecs.systems.minecraft.client.network.ClientPlayerInteractionManagerUpdateBlockBreakingProgressSystem;
import pw.seppuku.plugin.mixin.ActualThis;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class ClientPlayerInteractionManagerMixin implements ActualThis<ClientPlayerInteractionManager> {

    @Inject(method = "attackBlock", at = @At("HEAD"))
    private void onAttackBlockHead(final BlockPos blockPos, final Direction direction, final CallbackInfoReturnable<Boolean> callback) {
        Seppuku.INSTANCE.getEngine().findSystem(ClientPlayerInteractionManagerAttackBlockSystem.class).getProcess().invoke(actualThis(), blockPos, direction);
    }

    @Inject(method = "updateBlockBreakingProgress", at = @At("HEAD"))
    private void onUpdateBlockBreakingProgressHead(final BlockPos blockPos, final Direction direction, final CallbackInfoReturnable<Boolean> callback) {
        Seppuku.INSTANCE.getEngine().findSystem(ClientPlayerInteractionManagerUpdateBlockBreakingProgressSystem.class).getProcess().invoke(actualThis(), blockPos, direction);
    }
}
