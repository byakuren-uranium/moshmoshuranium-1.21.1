package com.sirenensemble.sculktechnologies.blocks.blockstates;

import com.mojang.serialization.MapCodec;
import com.sirenensemble.sculktechnologies.blocks.blockentity.ModBlockEntity;
import com.sirenensemble.sculktechnologies.blocks.blockentity.SculkInfectorEntity;
import com.sirenensemble.sculktechnologies.blocks.blockentity.SculkInjectorEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SculkInfectorBlock extends BaseEntityBlock {
    private static final MapCodec<SculkInfectorBlock> CODEC = simpleCodec(SculkInfectorBlock::new);


    public SculkInfectorBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.getBlockEntity(pos) instanceof SculkInfectorEntity sculkInfector){
            if(!level.isClientSide){
                ((ServerPlayer)player).openMenu(new SimpleMenuProvider(sculkInfector, Component.literal("Sculk Infector")), pos);
                return ItemInteractionResult.SUCCESS;
            }
        }

        return ItemInteractionResult.FAIL;
    }



    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SculkInfectorEntity(blockPos, blockState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if(state.getBlock() != newState.getBlock()){
            if(level.getBlockEntity(pos) instanceof SculkInfectorEntity sculkInfector){
                sculkInfector.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }

        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }
    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if(level.isClientSide){
            return null;
        }


        return createTickerHelper(blockEntityType, ModBlockEntity.SCULK_INFECTOR_BE.get(),
                (uwu, blockPos, blockState, blockEntity) -> blockEntity.tick(uwu, blockPos, blockState));
    }
}
