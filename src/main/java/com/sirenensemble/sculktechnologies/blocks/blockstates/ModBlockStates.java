package com.sirenensemble.sculktechnologies.blocks.blockstates;


import com.sirenensemble.sculktechnologies.blocks.ModBlock;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import java.util.function.Supplier;
import static com.sirenensemble.sculktechnologies.blocks.ModBlock.BLOCKS;


public class ModBlockStates {
    public static final DeferredBlock<StairBlock> SCULK_STAIR_BLOCK = registerBlock("sculk_stair_block",
            ()-> new StairBlock(ModBlock.SCULK_WOOD_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.WOOD)));

    public static final DeferredBlock<SlabBlock> SCULK_SLAB_BLOCK = registerBlock("sculk_slab_block",
            ()-> new SlabBlock(BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.WOOD)));

    public static final DeferredBlock<PressurePlateBlock> SCULK_PRESSUREPLATE_BLOCK = registerBlock("sculk_pressure_plate_block",
            ()-> new PressurePlateBlock(BlockSetType.BIRCH, BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.WOOD)));

    public static final DeferredBlock<ButtonBlock> SCULK_BUTTON_BLOCK = registerBlock("sculk_button_block",
            ()-> new ButtonBlock(BlockSetType.BIRCH, 20, BlockBehaviour.Properties.of().strength(0.5f).noCollission().sound(SoundType.WOOD)));

    public static final DeferredBlock<FenceBlock> SCULK_FENCE_BLOCK = registerBlock("sculk_fence_block",
            ()-> new FenceBlock(BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.WOOD)));

    public static final DeferredBlock<FenceGateBlock> SCULK_FENCE_GATE_BLOCK = registerBlock("sculk_fence_gate_block",
            ()-> new FenceGateBlock(WoodType.BIRCH, BlockBehaviour.Properties.of().strength(0.5f).requiresCorrectToolForDrops().sound(SoundType.WOOD)));

    public static final DeferredBlock<DoorBlock> SCULK_DOOR_BLOCK = registerBlock("sculk_door_block",
            ()-> new DoorBlock(BlockSetType.BIRCH, BlockBehaviour.Properties.of().strength(0.5f).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.WOOD)));

    public static final DeferredBlock<TrapDoorBlock> SCULK_TRAPDOOR_BLOCK = registerBlock("sculk_trapdoor_block",
            ()-> new TrapDoorBlock(BlockSetType.BIRCH, BlockBehaviour.Properties.of().strength(0.5f).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.WOOD)));

    private static <T extends Block> DeferredBlock<T>  registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = (DeferredBlock<T>) BLOCKS.register(name, block);
        return toReturn;
    }
    public static void register(IEventBus modEventBus){
        BLOCKS.register(modEventBus);
    }
}

