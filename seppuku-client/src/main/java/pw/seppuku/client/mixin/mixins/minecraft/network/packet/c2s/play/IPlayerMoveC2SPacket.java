package pw.seppuku.client.mixin.mixins.minecraft.network.packet.c2s.play;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PlayerMoveC2SPacket.class) public interface IPlayerMoveC2SPacket {
  @Accessor("x") @Mutable void setX(final double x);

  @Accessor("y") @Mutable void setY(final double y);

  @Accessor("z") @Mutable void setZ(final double z);

  @Accessor("yaw") @Mutable void setYaw(final float yaw);

  @Accessor("pitch") @Mutable void setPitch(final float pitch);

  @Accessor("onGround") @Mutable void setOnGround(final boolean onGround);

  @Accessor("changePosition") @Mutable void setChangePosition(final boolean changePosition);

  @Accessor("changeLook") @Mutable void setChangeLook(final boolean changeLook);
}
