package com.sirenensemble.sculktechnologies.jei;

import com.sirenensemble.sculktechnologies.SculkTechnologies;
import com.sirenensemble.sculktechnologies.blocks.ModBlock;
import com.sirenensemble.sculktechnologies.recipe.ConverterRecipes;
import com.sirenensemble.sculktechnologies.recipe.ModRecipes;
import com.sirenensemble.sculktechnologies.recipe.SculkInjectorRecipes;
import com.sirenensemble.sculktechnologies.screen.realvamp.ConverterMenu;
import com.sirenensemble.sculktechnologies.screen.realvamp.ConverterScreen;
import com.sirenensemble.sculktechnologies.screen.realvamp.InjectorScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIModPlugin implements IModPlugin {


    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new ConverterCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new InjectorCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<ConverterRecipes> converterRecipes = recipeManager.getAllRecipesFor(ModRecipes.CONVERTER_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(ConverterCategory.CONVERTER_RECIPE_RECIPE_TYPE, converterRecipes);
        List<SculkInjectorRecipes> injectorRecipes = recipeManager.getAllRecipesFor(ModRecipes.INJECTOR_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(InjectorCategory.SCULK_INJECTOR_RECIPE_RECIPE_TYPE, injectorRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(ConverterScreen.class, 60, 57, 14, 21, ConverterCategory.CONVERTER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(InjectorScreen.class, 70, 30, 24, 16, InjectorCategory.SCULK_INJECTOR_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlock.EXPSCULKCONVERTER.asItem()), ConverterCategory.CONVERTER_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlock.SCULK_INJECTOR.asItem()), InjectorCategory.SCULK_INJECTOR_RECIPE_RECIPE_TYPE);
    }
    
}
