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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pw.seppuku.client.Seppuku;
import pw.seppuku.client.ecs.systems.minecraft.client.network.ClientPlayerInteractionManagerAttackBlockSystem;
import pw.seppuku.client.ecs.systems.minecraft.client.network.ClientPlayerInteractionManagerUpdateBlockBreakingProgressSystem;
import pw.seppuku.plugin.mixin.ActualThis;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class ClientPlayerInteractionManagerMixin
    implements ActualThis<ClientPlayerInteractionManager> {

  @Inject(method = "attackBlock", at = @At("HEAD"))
  private void onAttackBlockHead(final BlockPos blockPos, final Direction direction,
      final CallbackInfoReturnable<Boolean> callback) {
    Seppuku.INSTANCE.getEngine()
        .findSystem(ClientPlayerInteractionManagerAttackBlockSystem.class)
        .getProcess()
        .invoke(actualThis(), blockPos, direction);
  }

  @Inject(method = "updateBlockBreakingProgress", at = @At("HEAD"))
  private void onUpdateBlockBreakingProgressHead(final BlockPos blockPos, final Direction direction,
      final CallbackInfoReturnable<Boolean> callback) {
    Seppuku.INSTANCE.getEngine()
        .findSystem(ClientPlayerInteractionManagerUpdateBlockBreakingProgressSystem.class)
        .getProcess()
        .invoke(actualThis(), blockPos, direction);
  }
}
