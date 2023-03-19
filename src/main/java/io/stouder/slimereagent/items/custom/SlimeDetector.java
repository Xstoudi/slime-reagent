package io.stouder.slimereagent.items.custom;

import io.stouder.slimereagent.SlimeReagent;
import io.stouder.slimereagent.helpers.Slimy;
import io.stouder.slimereagent.networking.Packets;
import io.stouder.slimereagent.networking.packet.SignalSlimeChunkS2CPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SlimeDetector extends Item {
    public static final ResourceLocation ENABLED = new ResourceLocation(SlimeReagent.MODID, "enabled");
    public static final String TAG_DETECTOR_ENABLED = "Enabled";

    public SlimeDetector() {
        super(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1));
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int amount, boolean inHand) {
        if(level.isClientSide) {
            super.inventoryTick(itemStack, level, entity, amount, inHand);
            return;
        }

        ServerLevel serverLevel = (ServerLevel) level;

        if(entity instanceof ServerPlayer player) {
            if(!inHand) return;

            BlockPos blockPos = entity.getOnPos();
            boolean hasSlime = Slimy.checkSlimePresence(serverLevel, blockPos);
            Packets.sendToPlayer(new SignalSlimeChunkS2CPacket(hasSlime), player);
        }
    }

}
