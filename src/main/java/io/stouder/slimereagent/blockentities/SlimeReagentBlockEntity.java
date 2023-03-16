package io.stouder.slimereagent.blockentities;

import io.stouder.slimereagent.Registration;
import io.stouder.slimereagent.SlimeReagent;
import io.stouder.slimereagent.helpers.Slimy;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.WorldgenRandom;

public class SlimeReagentBlockEntity extends BlockEntity {
    private int timer = 0;
    private int resultTime =  20 * (5 + SlimeReagent.RANDOM.nextInt(6));
    private int particleUntil = resultTime + 20;
    private boolean checked = false;
    private boolean slimePresent = false;

    public SlimeReagentBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.SLIME_REAGENT_BLOCK_ENTITY.get(), pos, state);
    }

    private void checkSlimePresence(ServerLevel level, BlockPos pos) {
        if(this.checked) return;

        this.slimePresent = Slimy.checkSlimePresence(level, pos);
        this.checked = true;
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        SlimeReagentBlockEntity tile = (SlimeReagentBlockEntity) be;
        if(level.isClientSide()) return;

        ServerLevel serverLevel = (ServerLevel) level;

        tile.timer++;
        if(tile.timer > tile.resultTime) {
            tile.checkSlimePresence(serverLevel, pos);
        }

        if(tile.slimePresent) {
            serverLevel.sendParticles(ParticleTypes.ITEM_SLIME, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5, 0.25, 0.25, 0.25, 0.05);
        } else {
            serverLevel.sendParticles(ParticleTypes.ASH, pos.getX() + 0.5, pos.getY() + 0.15, pos.getZ() + 0.5, 5, 0.25, 0.25, 0.25, 0.05);
        }


        if(tile.timer > tile.particleUntil) {
            if(tile.slimePresent) {
                serverLevel.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.SLIME_SQUISH, SoundSource.BLOCKS, 1, 1);
            }
            serverLevel.removeBlock(pos, false);

        }
    }
}
