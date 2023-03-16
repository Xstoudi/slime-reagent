package io.stouder.slimereagent.networking;


import ca.lukegrahamlandry.lib.network.ClientSideHandler;
import io.stouder.slimereagent.Registration;
import net.minecraft.client.Minecraft;

public class SignalSlimeChunkPacket implements ClientSideHandler {

    @Override
    public void handle() {
        Minecraft.getInstance().player.getInventory().
    }
}
