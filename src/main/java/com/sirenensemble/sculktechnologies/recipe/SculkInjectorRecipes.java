package com.sirenensemble.sculktechnologies.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record SculkInjectorRecipes(Ingredient input, ItemStack output) implements Recipe<SculkInjectorRecipesInput> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(input);

        return list;
    }

    @Override
    public boolean matches(SculkInjectorRecipesInput sculkInjectorRecipesInput, Level level) {
        if(level.isClientSide){
            return false;
        }
        return input.test(sculkInjectorRecipesInput.getItem(0));
    }

    @Override
    public ItemStack assemble(SculkInjectorRecipesInput sculkInjectorRecipesInput, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.INJECTOR_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.INJECTOR_TYPE.get();
    }


    public static class Serializer implements RecipeSerializer<SculkInjectorRecipes>{
        public static final MapCodec<SculkInjectorRecipes> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(SculkInjectorRecipes::input),
                ItemStack.CODEC.fieldOf("result").forGetter(SculkInjectorRecipes::output)
        ).apply(inst, SculkInjectorRecipes::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, SculkInjectorRecipes> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, SculkInjectorRecipes::input,
                        ItemStack.STREAM_CODEC, SculkInjectorRecipes::output,
                        SculkInjectorRecipes::new);

        @Override
        public MapCodec<SculkInjectorRecipes> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, SculkInjectorRecipes> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
