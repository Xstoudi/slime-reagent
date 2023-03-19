package io.stouder.slimereagent;

import com.mojang.logging.LogUtils;
import io.stouder.slimereagent.blockentities.ModBlockEntities;
import io.stouder.slimereagent.blocks.ModBlocks;
import io.stouder.slimereagent.items.ModItems;
import io.stouder.slimereagent.items.custom.SlimeDetector;
import io.stouder.slimereagent.networking.Packets;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Random;

@Mod(SlimeReagent.MODID)
public class SlimeReagent {

    public static final String MODID = "slimereagent";
    public static final Random RANDOM = new Random();
    private static final Logger LOGGER = LogUtils.getLogger();

    public SlimeReagent() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);


        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(this::onClientSetup);
        modEventBus.addListener(this::onServerSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("SLIMY Common setup");
        event.enqueueWork(Packets::register);
    }

    public void onClientSetup(FMLClientSetupEvent event) {
        LOGGER.info("SLIMY Client setup");
    }

    public void onServerSetup(FMLDedicatedServerSetupEvent event) {
        LOGGER.info("SLIMY Server setup");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                net.minecraft.client.renderer.item.ItemProperties.register(ModItems.SLIME_DETECTOR.get(), SlimeDetector.ENABLED, new io.stouder.slimereagent.properties.SlimeDetectorItemPropertyFunction());
            });
        }
    }
}
