package net.joashneves.s3nhastuff.block.custom;

import com.mojang.serialization.MapCodec;
import net.joashneves.s3nhastuff.block.entity.MugBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class MugBlock extends BlockWithEntity {
    public static final MapCodec<MugBlock> CODEC = createCodec(MugBlock::new);
    public static final VoxelShape SHAPE = Block.createCuboidShape(5.0, 0, 3.0, 11.0, 6.0, 11.0);

    public MugBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MugBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        int rotation = Math.floorMod(MathHelper.floor(ctx.getPlayerYaw() * 16.0F / 360.0F + 0.5D), 16);
        return this.getDefaultState().with(Properties.ROTATION, rotation);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.ROTATION);
    }
}