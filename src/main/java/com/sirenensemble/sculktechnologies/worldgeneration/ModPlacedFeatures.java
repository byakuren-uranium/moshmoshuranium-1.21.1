package com.sirenensemble.sculktechnologies.worldgeneration;

import com.sirenensemble.sculktechnologies.SculkTechnologies;
import com.sirenensemble.sculktechnologies.blocks.ModBlock;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> SCULK_ORE_PLACED_KEY = registerKey("sculk_ore_placed");
public static final ResourceKey<PlacedFeature> SCULKWOOD_PLACED_KEY = registerKey("sculkwood_placed");
    public static final ResourceKey<PlacedFeature> SCULK_BERRY_PLACED_KEY = registerKey("sculk_berry_placed");
public static final ResourceKey<PlacedFeature> SCULK_EX_PLACED_KEY = registerKey("sculk_ex_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

      //  register(context, SCULK_EX_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SCULK_INF_KEY),
             //   Structure.getMeanFirstOccupiedHeight(configuredFeatures.getOrThrow()))));

        register(context, SCULK_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SCULK_ORE_KEY),
                commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(50))));

register(context, SCULKWOOD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SCULKWOOD_KEY),
        VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.2f, 1), ModBlock.SCULK_WOOD_SAPLING.get()));

register(context, SCULK_BERRY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SCULK_BERRY_KEY),
        List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));


    }
    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }


    public static List<PlacementModifier> orePlacement(PlacementModifier pCountPlacement, PlacementModifier pHeightRange) {
        return List.of(pCountPlacement, InSquarePlacement.spread(), pHeightRange, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    public static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }
}
