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

package pw.seppuku.client.mixin.mixins.minecraft.network;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.seppuku.client.Seppuku;
import pw.seppuku.client.ecs.systems.minecraft.network.ClientConnectionHandlePacketSystem;
import pw.seppuku.client.ecs.systems.minecraft.network.ClientConnectionSendSystem;
import pw.seppuku.plugin.mixin.ActualThis;

@Mixin(ClientConnection.class) public abstract class ClientConnectionMixin
    implements ActualThis<ClientConnection> {

  @Inject(method = "handlePacket", at = @At("HEAD"))
  private static <T extends PacketListener> void onHandlePacketHead(final Packet<T> packet,
      final PacketListener packetListener, final CallbackInfo callback) {
    Seppuku.INSTANCE.getEngine()
        .findSystem(ClientConnectionHandlePacketSystem.class)
        .getProcess()
        .invoke(packet, packetListener);
  }

  @Inject(method = "send(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"))
  private <T extends PacketListener> void onSendHead(final Packet<T> packet,
      final CallbackInfo callback) {
    Seppuku.INSTANCE.getEngine()
        .findSystem(ClientConnectionSendSystem.class)
        .getProcess()
        .invoke(actualThis(), packet);
  }
}
