package io.stouder.slimereagent;

import io.stouder.slimereagent.blockentities.SlimeReagentBlockEntity;
import io.stouder.slimereagent.blocks.SlimeReagentBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SlimeReagent.MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SlimeReagent.MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SlimeReagent.MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
    }

    public static final RegistryObject<Block> SLIME_REAGENT = BLOCKS.register("slime_reagent", SlimeReagentBlock::new);
    public static final RegistryObject<Item> SLIME_REAGENT_ITEM = fromBlock(SLIME_REAGENT);
    public static final RegistryObject<BlockEntityType<SlimeReagentBlockEntity>> SLIME_REAGENT_BLOCK_ENTITY = BLOCK_ENTITIES.register("slime_reagent", () -> BlockEntityType.Builder.of(SlimeReagentBlockEntity::new, SLIME_REAGENT.get()).build(null));

    private static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    }
}
