package io.stouder.slimereagent.items;

import ca.lukegrahamlandry.lib.network.NetworkWrapper;
import io.stouder.slimereagent.SlimeReagent;
import io.stouder.slimereagent.helpers.Slimy;
import io.stouder.slimereagent.networking.SignalSlimeChunkPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.stream.StreamSupport;

public class SlimeDetector extends Item {
    public static ResourceLocation ENABLED = new ResourceLocation(SlimeReagent.MODID, "enabled");

    public SlimeDetector() {
        super(new Item.Properties().tab(CreativeModeTab.TAB_MISC));
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        if(level.isClientSide) {
            super.inventoryTick(itemStack, level, entity, p_41407_, p_41408_);
            return;
        }

        ServerLevel serverLevel = (ServerLevel) level;

        if(entity instanceof ServerPlayer player) {
            boolean inHand = StreamSupport.stream(player.getHandSlots().spliterator(), false).anyMatch(handItem -> handItem.is(this));
            if(!inHand) return;

            BlockPos blockPos = entity.getOnPos();
            boolean hasSlime = Slimy.checkSlimePresence(serverLevel, blockPos);
            if(hasSlime) {
                NetworkWrapper.sendToClient(player, new SignalSlimeChunkPacket());
            }
        }
    }

}
