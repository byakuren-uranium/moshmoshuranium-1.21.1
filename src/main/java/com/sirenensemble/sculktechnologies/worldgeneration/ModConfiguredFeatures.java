package com.sirenensemble.sculktechnologies.worldgeneration;

import com.sirenensemble.sculktechnologies.SculkTechnologies;
import com.sirenensemble.sculktechnologies.blocks.ModBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;


public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> SCULK_ORE_KEY = registerKey("sculk_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SCULK_INF_KEY = registerKey("sculk_inf");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SCULKWOOD_KEY = registerKey("sculkwood");

    public static final ResourceKey<ConfiguredFeature<?,?>> SCULK_BERRY_KEY = registerKey("sculk_berry_key");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest sculkReplaceables = new TagMatchTest(BlockTags.SCULK_REPLACEABLE);


        List<OreConfiguration.TargetBlockState> Sculk_ores = List.of(
                OreConfiguration.target(deepslateReplaceables, ModBlock.DEEPSLATE_SCULK_ORE_BLOCK.get().defaultBlockState()),
OreConfiguration.target(stoneReplaceables, ModBlock.SCULK_ORE_BLOCK.get().defaultBlockState())
        );
        register(context, SCULK_ORE_KEY, Feature.ORE, new OreConfiguration(Sculk_ores, 5));

     //   register(context, SCULK_INF_KEY, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlock.SCULK_EX_BLOCK.get())));

        register(context, SCULKWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlock.SCULK_WOOD_LOG.get()),
                new ForkingTrunkPlacer(5, 3, 2),
                BlockStateProvider.simple(ModBlock.SCULK_WOOD_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().dirt(BlockStateProvider.simple(Blocks.SCULK)).build());

        register(context, SCULK_BERRY_KEY, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlock.SCULK_SWEET_BERRY_BUSH.get().defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))), List.of(Blocks.GRASS_BLOCK)
        ));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, name));

    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
