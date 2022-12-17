/*
 *     Copyright (C) 2022  Seppuku Development
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
