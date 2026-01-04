package com.sirenensemble.sculktechnologies.worldgeneration;

import com.sirenensemble.sculktechnologies.SculkTechnologies;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_SCULK_ORE = registerKey("add_sculk_ore");
    public static final ResourceKey<BiomeModifier> ADD_SCULKWOOD = registerKey("add_sculkwood");
    public static final ResourceKey<BiomeModifier> ADD_SCULK_BERRY = registerKey("add_sculk_berry");
    public static final ResourceKey<BiomeModifier> ADD_SCULK_EX = registerKey("add_sculk_ex");


    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
        var structures = context.lookup(Registries.STRUCTURE);

        context.register(ADD_SCULK_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SCULK_ORE_PLACED_KEY)), GenerationStep.Decoration.UNDERGROUND_ORES));

        //  context.register(ADD_SCULK_EX, new BiomeModifiers.AddFeaturesBiomeModifier
        //          (HolderSet.direct(structures.getOrThrow(StructureType.BURIED_TREASURE)),
        //  HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SCULK_EX_PLACED_KEY)),
        //  GenerationStep.Decoration.UNDERGROUND_STRUCTURES));

        //context.register(ADD_SCULKWOOD, new BiomeModifiers.AddFeaturesBiomeModifier(
        //        HolderSet.direct(biomes.getOrThrow(Biomes.BIRCH_FOREST), biomes.getOrThrow(Biomes.SAVANNA)),
        //        HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SCULKWOOD_PLACED_KEY)),
        //        GenerationStep.Decoration.VEGETAL_DECORATION));


        context.register(ADD_SCULK_BERRY, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.BIRCH_FOREST), biomes.getOrThrow(Biomes.SNOWY_TAIGA)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SCULK_BERRY_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

    }
        private static ResourceKey<BiomeModifier> registerKey (String name){
            return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, name));
        }
    }



