package io.stouder.slimereagent.blockentities;

import io.stouder.slimereagent.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.WorldgenRandom;

public class SlimeReagentBlockEntity extends BlockEntity {

    private int timer = 0;
    private boolean checked = false;
    private boolean slimePresent = false;

    public SlimeReagentBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.SLIME_REAGENT_BLOCK_ENTITY.get(), pos, state);
    }

    private void checkSlimePresence(ServerLevel level, BlockPos blockPos) {
        if(this.checked) return;

        ChunkPos chunkPos = new ChunkPos(blockPos);
        this.slimePresent = WorldgenRandom.seedSlimeChunk(chunkPos.x, chunkPos.z, ((WorldGenLevel)level).getSeed(), 987234911L).nextInt(10) == 0;
        this.checked = true;
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        SlimeReagentBlockEntity tile = (SlimeReagentBlockEntity) be;
        if(level.isClientSide()) return;

        ServerLevel serverLevel = (ServerLevel) level;

        tile.timer++;
        if(tile.timer > 20) {
            tile.checkSlimePresence(serverLevel, pos);
            serverLevel.sendParticles(ParticleTypes.ITEM_SLIME, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5, 0.25, 0.25, 0.25, 0.05);
        }
    }
}
