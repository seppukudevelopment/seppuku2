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
