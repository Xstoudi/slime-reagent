package io.stouder.slimereagent.client;

import io.stouder.slimereagent.items.ModItems;
import io.stouder.slimereagent.items.custom.SlimeDetector;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class SlimySignaling {
    public static void signalSlimy(boolean isSlimy) {
        Player player = Minecraft.getInstance().player;
        if(player == null) return;

        player.getInventory().items.stream()
                .filter(itemStack -> itemStack.is(ModItems.SLIME_DETECTOR.get()))
                .forEach(itemStack -> itemStack.getOrCreateTag().putBoolean(SlimeDetector.TAG_DETECTOR_ENABLED, isSlimy));
    }
}
