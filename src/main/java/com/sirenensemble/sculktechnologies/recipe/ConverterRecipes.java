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

public record ConverterRecipes(Ingredient inputItem, ItemStack output) implements Recipe<ConverterRecipesInput> {
 /*
 input and output =>
 Read in a JSON file

ConverterRecipesInput = INVENTORY of the blockEntity


  */


    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);

        return list;
    }

    @Override
    public boolean matches(ConverterRecipesInput converterRecipesInput, Level level) {
        if(level.isClientSide){
        return false;
        }
        return inputItem.test(converterRecipesInput.getItem(0));
    }

    @Override
    public ItemStack assemble(ConverterRecipesInput converterRecipesInput, HolderLookup.Provider provider) {
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
        return ModRecipes.CONVERTER_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.CONVERTER_TYPE.get();
    }



    public static class Serializer implements RecipeSerializer<ConverterRecipes>{
        public static final MapCodec<ConverterRecipes> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(ConverterRecipes::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(ConverterRecipes::output)
        ).apply(inst, ConverterRecipes::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ConverterRecipes> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, ConverterRecipes::inputItem,
                        ItemStack.STREAM_CODEC, ConverterRecipes::output,
                        ConverterRecipes::new);

        @Override
        public MapCodec<ConverterRecipes> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ConverterRecipes> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
