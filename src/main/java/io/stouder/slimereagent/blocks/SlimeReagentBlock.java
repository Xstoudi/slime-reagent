package io.stouder.slimereagent.blocks;

import io.stouder.slimereagent.Registration;
import io.stouder.slimereagent.blockentities.SlimeReagentBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SlimeReagentBlock extends Block implements EntityBlock {

    public SlimeReagentBlock() {
        super(Properties.of(Material.DIRT)
                .noOcclusion()
                .strength(0)
        );
    }

    private static final VoxelShape SHAPE = Shapes.join(Block.box(5, 1, 5, 11, 2, 11), Block.box(3, 0, 3, 13, 1, 13), BooleanOp.OR);

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState state) {
        return Registration.SLIME_REAGENT_BLOCK_ENTITY.get().create(blockPos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return type == Registration.SLIME_REAGENT_BLOCK_ENTITY.get() ? SlimeReagentBlockEntity::tick : null;
    }
}
