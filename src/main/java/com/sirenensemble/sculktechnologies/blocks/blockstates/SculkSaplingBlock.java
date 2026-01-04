package com.sirenensemble.sculktechnologies.blocks.blockstates;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class SculkSaplingBlock extends SaplingBlock {
    private final Supplier<Block> blocktosurviveon;
    public SculkSaplingBlock(TreeGrower treeGrower, Properties properties, Supplier<Block> block) {
        super(treeGrower, properties);
        this.blocktosurviveon = block;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return blocktosurviveon.get() == state.getBlock() || state.is(BlockTags.DIRT);
    }
}
