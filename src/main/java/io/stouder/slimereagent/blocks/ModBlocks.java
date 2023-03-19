package io.stouder.slimereagent.blocks;

import io.stouder.slimereagent.SlimeReagent;
import io.stouder.slimereagent.blocks.custom.SlimeReagentBlock;
import io.stouder.slimereagent.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SlimeReagent.MODID);

    public static final RegistryObject<Block> SLIME_REAGENT = BLOCKS.register("slime_reagent", SlimeReagentBlock::new);

}
