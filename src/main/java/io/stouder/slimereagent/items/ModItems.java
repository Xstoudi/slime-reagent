package io.stouder.slimereagent.items;

import io.stouder.slimereagent.SlimeReagent;
import io.stouder.slimereagent.blocks.ModBlocks;
import io.stouder.slimereagent.items.custom.SlimeDetector;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SlimeReagent.MODID);

    public static final RegistryObject<Item> SLIME_DETECTOR = ITEMS.register("slime_detector", SlimeDetector::new);
    public static final RegistryObject<Item> SLIME_REAGENT_ITEM = blockItem(ModBlocks.SLIME_REAGENT);

    private static <B extends Block> RegistryObject<Item> blockItem(RegistryObject<B> block) {
        return ModItems.ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    }

}
