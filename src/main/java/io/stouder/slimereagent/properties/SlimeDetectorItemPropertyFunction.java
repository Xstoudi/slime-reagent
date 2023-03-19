package io.stouder.slimereagent.properties;

import io.stouder.slimereagent.items.custom.SlimeDetector;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class SlimeDetectorItemPropertyFunction implements ClampedItemPropertyFunction {
    @Override
    public float unclampedCall(ItemStack itemStack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int p_174567_) {
        return itemStack.getOrCreateTag().getBoolean(SlimeDetector.TAG_DETECTOR_ENABLED) ? 1 : 0;
    }
}
