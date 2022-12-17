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

package pw.seppuku.client.mixin.mixins.minecraft.client;

import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.seppuku.client.Seppuku;
import pw.seppuku.client.ecs.systems.minecraft.client.KeyboardOnKeySystem;
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
