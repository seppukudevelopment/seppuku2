package pw.seppuku.common_systems.mixin.mixins.minecraft.network;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.seppuku.client.Seppuku;
import pw.seppuku.common_systems.ecs.systems.minecraft.network.ClientConnectionHandlePacketSystem;
import pw.seppuku.common_systems.ecs.systems.minecraft.network.ClientConnectionSendSystem;
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
