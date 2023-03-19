package io.stouder.slimereagent.networking;

import io.stouder.slimereagent.SlimeReagent;
import io.stouder.slimereagent.networking.packet.SignalSlimeChunkS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class Packets {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        Packets.INSTANCE = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(SlimeReagent.MODID, "messages"))
                .networkProtocolVersion(() -> "1")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        Packets.INSTANCE.messageBuilder(SignalSlimeChunkS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SignalSlimeChunkS2CPacket::new)
                .encoder(SignalSlimeChunkS2CPacket::toBytes)
                .consumerMainThread(SignalSlimeChunkS2CPacket::handle)
                .add();
    }

    public static <T> void sendToServer(T message) {
        Packets.INSTANCE.sendToServer(message);
    }

    public static <T> void sendToPlayer(T message, ServerPlayer player) {
        Packets.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
