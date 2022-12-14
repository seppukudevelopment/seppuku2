package pw.seppuku.common_systems.mixin.mixins.minecraft.network;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public abstract class ClientConnectionMixin {

  @Inject(method = "handlePacket", at = @At("HEAD"))
  private static <T extends PacketListener> void onHandlePacketHead(final Packet<T> packet,
      final PacketListener packetListener, final CallbackInfo callback) {
  }

  @Inject(method = "send", at = @At("HEAD"))
  private void onSendHead(final Packet<?> packet, final CallbackInfo callback) {
  }
}
