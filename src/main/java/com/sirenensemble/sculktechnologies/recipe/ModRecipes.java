package com.sirenensemble.sculktechnologies.recipe;

import com.sirenensemble.sculktechnologies.SculkTechnologies;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, SculkTechnologies.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, SculkTechnologies.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ConverterRecipes>> CONVERTER_SERIALIZER =
            SERIALIZERS.register("converter", ConverterRecipes.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<ConverterRecipes>> CONVERTER_TYPE =
            TYPES.register("converter", () -> new RecipeType<ConverterRecipes>() {
                @Override
                public String toString() {
                    return "converter";
                }
            });

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SculkInjectorRecipes>> INJECTOR_SERIALIZER =
            SERIALIZERS.register("sculk_injector", SculkInjectorRecipes.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<SculkInjectorRecipes>> INJECTOR_TYPE =
            TYPES.register("sculk_injector", () -> new RecipeType<SculkInjectorRecipes>(){
                @Override
                public String toString() {
                    return "sculk_injector";
                }
            });



    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}