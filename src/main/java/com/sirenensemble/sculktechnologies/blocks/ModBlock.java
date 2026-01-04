package com.sirenensemble.sculktechnologies.blocks;

import com.sirenensemble.sculktechnologies.SculkTechnologies;
import com.sirenensemble.sculktechnologies.blocks.blockstates.*;
import com.sirenensemble.sculktechnologies.worldgeneration.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlock {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(SculkTechnologies.MODID);
    public static final DeferredBlock<Block> BLOCK_OF_SCULK = registerBlock("block_of_sculk", () -> new Block(BlockBehaviour.Properties.of()
            .strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)
            .lightLevel(state -> 5)));
    public static final DeferredBlock<Block>SCULK_WOOD_LOG = registerBlock("sculkwood_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LOG)));
    public static final DeferredBlock<Block> STRIPPED_SCULK_WOOD_WOOD = registerBlock("stripped_sculkwood_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_BIRCH_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_SCULK_WOOD_LOG = registerBlock("stripped_sculkwood_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_BIRCH_LOG)));
    public static final DeferredBlock<Block> SCULK_WOOD_PLANKS = registerBlock("sculkwood_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 6;
        }
        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }


    });
    public static final DeferredBlock<Block> SCULK_WOOD_LEAVES = registerBlock("sculkwood_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LEAVES)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 35;
        }
        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 70;
        }


    });
    public static final DeferredBlock<Block> SCULK_WOOD_SAPLING = registerBlock("sculkwood_sapling", () -> new SculkSaplingBlock(ModTreeGrowers.SCULKWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SAPLING), () -> Blocks.SCULK));
    public static final DeferredBlock<Block> SCULK_WOOD_WOOD = registerBlock("sculkwood_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_WOOD)));
    public static final DeferredBlock<Block> SCULK_INFECTOR = registerBlock("sculk_infector", () -> new SculkInfectorBlock(BlockBehaviour.Properties.of().strength(2f, 2f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> SCULK_ORE_BLOCK = registerBlock("sculk_ore_block", () -> new Block(BlockBehaviour.Properties.of()
            .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)
            .lightLevel(state -> 0)));
    public static final DeferredBlock<Block> SCULK_INJECTOR = registerBlock("sculk_injector", () -> new SculkInjector(BlockBehaviour.Properties.of().strength(1.5f, 50f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> EXPSCULKCONVERTER = registerBlock("exp_sculk_converter", () -> new ExpSculkConverter(BlockBehaviour.Properties.of().strength(4f, 50f).requiresCorrectToolForDrops().lightLevel(state -> 1).noOcclusion() ));
    public static final DeferredBlock<Block> DEEPSLATE_SCULK_ORE_BLOCK = registerBlock("deepslate_sculk_ore_block", () -> new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.of()
            .strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)
            .lightLevel(state -> 1)));
    public static final DeferredBlock<Block> SCULK_EX_BLOCK = registerBlock("soul_infused_sculk_block", () -> new SculkExBlock(BlockBehaviour.Properties.of()
            .strength(0.5f).requiresCorrectToolForDrops().sound(SoundType.SCULK).lightLevel(state -> 1)));
    public static final DeferredBlock<Block> SCULK_SHORT_GRASS = registerBlock("sculk_short_grass", ()-> new TallGrassBlock(BlockBehaviour.Properties.of().replaceable().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).ignitedByLava().pushReaction(PushReaction.DESTROY).noOcclusion()){
        @Override
        protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
            return state.is(BlockTags.DIRT) || state.is(Blocks.SCULK);
        }
    });




    //public static final DeferredBlock<Block> POTTED_SCULK_TORCHFLOWER = registerBlock("potted_sculk_torchflower", () -> flowerPot(ModBlock.SCULK_TORCHFLOWER.get()));
        public static final DeferredBlock<Block> SCULK_TORCHFLOWER_CROP = registerBlock("sculk_torchflower_crop", () -> new SculkTorchFlowerCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCHFLOWER_CROP)));
        public static final DeferredBlock<Block> SCULK_TORCHFLOWER = registerBlock("sculk_torchflower", () -> new SculkTorchflower(MobEffects.DAMAGE_RESISTANCE, 0.5f, BlockBehaviour.Properties.ofFullCopy(Blocks.TORCHFLOWER)));
        public static final DeferredBlock<Block> SCULK_BEETROOT_CROP = registerBlock("sculk_beetroot_crop", () -> new SculkBeetrootBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS)));
        public static final DeferredBlock<Block> SCULK_POTATO_CROP = registerBlock("sculk_potato_crop", ()-> new SculkPotatoBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS)));
        public static final DeferredBlock<Block> SCULK_CARROT_CROP = registerBlock("sculk_carrot_crop", () -> new SculkCarrotBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS)));
        public static final DeferredBlock<Block> SCULK_SWEET_BERRY_BUSH = registerBlock("sculk_sweet_berry_bush", () -> new SculkSweetBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH), () -> Blocks.SCULK));






    private static <T extends Block> DeferredBlock<T>  registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = (DeferredBlock<T>) BLOCKS.register(name, block);
        return toReturn;
    }
    public static void register(IEventBus modEventBus){
        BLOCKS.register(modEventBus);
    }

    private static Block flowerPot(Block potted) {
        return new FlowerPotBlock(potted, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
    }

}

