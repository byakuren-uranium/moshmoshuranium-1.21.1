package com.sirenensemble.sculktechnologies.blocks.blockstates;

import com.mojang.serialization.MapCodec;
import com.sirenensemble.sculktechnologies.blocks.ModBlock;
import com.sirenensemble.sculktechnologies.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.TorchflowerCropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SculkTorchFlowerCropBlock extends CropBlock {
    public static final MapCodec<SculkTorchFlowerCropBlock> CODEC = simpleCodec(SculkTorchFlowerCropBlock::new);
    public static final int MAX_AGE = 2;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 2);
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box((double)5.0F, (double)0.0F, (double)5.0F, (double)11.0F, (double)6.0F, (double)11.0F),
            Block.box((double)5.0F, (double)0.0F, (double)5.0F, (double)11.0F, (double)10.0F, (double)11.0F)};




    public SculkTorchFlowerCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public MapCodec<? extends CropBlock> codec() {
        return CODEC;
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

   @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.SCULK_TORCHFLOWER_SEEDS;
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

   @Override
   public BlockState getStateForAge(int age) {
            return age == 2 ? ModBlock.SCULK_TORCHFLOWER.get().defaultBlockState() : super.getStateForAge(age);

   }
}
