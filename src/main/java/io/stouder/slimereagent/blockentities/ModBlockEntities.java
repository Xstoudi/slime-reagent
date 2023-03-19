package io.stouder.slimereagent.blockentities;

import io.stouder.slimereagent.SlimeReagent;
import io.stouder.slimereagent.blockentities.custom.SlimeReagentBlockEntity;
import io.stouder.slimereagent.blocks.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SlimeReagent.MODID);

    public static final RegistryObject<BlockEntityType<SlimeReagentBlockEntity>> SLIME_REAGENT_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "slime_reagent",
            () -> BlockEntityType.Builder.of(SlimeReagentBlockEntity::new, ModBlocks.SLIME_REAGENT.get()).build(null)
    );
}
