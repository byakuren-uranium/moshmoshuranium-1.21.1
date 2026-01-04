package com.sirenensemble.sculktechnologies.jei;


import com.sirenensemble.sculktechnologies.SculkTechnologies;
import com.sirenensemble.sculktechnologies.blocks.ModBlock;
import com.sirenensemble.sculktechnologies.recipe.ConverterRecipes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ConverterCategory implements IRecipeCategory<ConverterRecipes> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, "converter");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID,
            "textures/gui/converter/converter_gui.png");

    public static final RecipeType<ConverterRecipes> CONVERTER_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, ConverterRecipes.class);

    private final IDrawable background;
    private final IDrawable icon;

    public ConverterCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 3, 3, 171, 80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlock.EXPSCULKCONVERTER));
    }


    @Override
    public RecipeType<ConverterRecipes> getRecipeType() {
        return CONVERTER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Converter");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void draw(ConverterRecipes recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        background.draw(guiGraphics);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ConverterRecipes recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 77, 13).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 77, 51).addItemStack(recipe.getResultItem(null));
    }

    @Override
    public int getHeight() {
        return 80;
    }

    @Override
    public int getWidth() {
        return 171;
    }

}
