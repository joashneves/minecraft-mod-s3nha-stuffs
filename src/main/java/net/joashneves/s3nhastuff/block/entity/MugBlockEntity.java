package net.joashneves.s3nhastuff.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class MugBlockEntity extends BlockEntity {

    public MugBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MUG_BE, pos, state);
    }
}