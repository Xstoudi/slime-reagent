package io.stouder.slimereagent.helpers;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.WorldgenRandom;

public class Slimy {
    public static boolean checkSlimePresence(ServerLevel level, BlockPos blockPos) {
        ChunkPos chunkPos = new ChunkPos(blockPos);
        return WorldgenRandom.seedSlimeChunk(chunkPos.x, chunkPos.z, ((WorldGenLevel)level).getSeed(), 987234911L).nextInt(10) == 0;
    }
}
