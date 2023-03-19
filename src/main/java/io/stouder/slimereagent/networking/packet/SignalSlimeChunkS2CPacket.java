package io.stouder.slimereagent.networking.packet;

import io.stouder.slimereagent.client.SlimySignaling;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SignalSlimeChunkS2CPacket {
    private final boolean isSlimy;

    public SignalSlimeChunkS2CPacket(boolean isSlimy) {
        this.isSlimy = isSlimy;
    }

    public SignalSlimeChunkS2CPacket(FriendlyByteBuf buf) {
        this.isSlimy = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.isSlimy);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            SlimySignaling.signalSlimy(this.isSlimy);
        });
        return true;
    }
}
