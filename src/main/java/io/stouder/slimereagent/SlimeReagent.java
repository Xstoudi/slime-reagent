package io.stouder.slimereagent;

import com.mojang.logging.LogUtils;
import io.stouder.slimereagent.blockentities.ModBlockEntities;
import io.stouder.slimereagent.blocks.ModBlocks;
import io.stouder.slimereagent.items.ModItems;
import io.stouder.slimereagent.items.custom.SlimeDetector;
import io.stouder.slimereagent.networking.Packets;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
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

        modEventBus.addListener(this::onRegisterCreativeTab);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("SLIMY Common setup");
        event.enqueueWork(Packets::register);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        LOGGER.info("SLIMY Client setup");
    }

    private void onServerSetup(FMLDedicatedServerSetupEvent event) {
        LOGGER.info("SLIMY Server setup");
    }

    private void onRegisterCreativeTab(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.SLIME_DETECTOR);
            event.accept(ModItems.SLIME_REAGENT_ITEM);
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    private static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                net.minecraft.client.renderer.item.ItemProperties.register(ModItems.SLIME_DETECTOR.get(), SlimeDetector.ENABLED, new io.stouder.slimereagent.properties.SlimeDetectorItemPropertyFunction());
            });
        }
    }
}
