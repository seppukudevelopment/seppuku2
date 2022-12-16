package pw.seppuku.client.mixin.mixins.minecraft.client.network;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ClientPlayerInteractionManager.class) public interface IClientPlayerInteractionManager {

  @Accessor("currentBreakingProgress") float getCurrentBreakingProgress();

  @Accessor("currentBreakingProgress") void setCurrentBreakingProgress(
      final float currentBreakingProgress);

  @Accessor("blockBreakingCooldown") int getBlockBreakingCooldown();

  @Accessor("blockBreakingCooldown") void setBlockBreakingCooldown(final int blockBreakingCooldown);
}
