package com.sirenensemble.sculktechnologies.jei;

import com.sirenensemble.sculktechnologies.SculkTechnologies;
import com.sirenensemble.sculktechnologies.blocks.ModBlock;
import com.sirenensemble.sculktechnologies.recipe.ConverterRecipes;
import com.sirenensemble.sculktechnologies.recipe.SculkInjectorRecipes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

public class InjectorCategory implements IRecipeCategory<SculkInjectorRecipes> {
    private final IDrawable background;
    private final IDrawable icon;

    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, "sculk_injector");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID,
            "textures/gui/sculk_injector/injector_gui.png");


    public static final RecipeType<SculkInjectorRecipes> SCULK_INJECTOR_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, SculkInjectorRecipes.class);

    public InjectorCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 3, 3, 171, 80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlock.SCULK_INJECTOR));
    }

    @Override
    public RecipeType<SculkInjectorRecipes> getRecipeType() {
        return SCULK_INJECTOR_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Sculk Injector");
    }


    @Override
    public int getWidth() {
        return 171;
    }

    @Override
    public int getHeight() {
        return 80;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SculkInjectorRecipes recipe, IFocusGroup focuses) {
builder.addSlot(RecipeIngredientRole.INPUT, 45, 32).addIngredients(recipe.getIngredients().get(0));
builder.addSlot(RecipeIngredientRole.OUTPUT, 95, 32).addItemStack(recipe.getResultItem(null));
builder.addSlot(RecipeIngredientRole.CATALYST, 70, 6).addItemStack(Items.SCULK.getDefaultInstance());
    }

    @Override
    public void draw(SculkInjectorRecipes recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        background.draw(guiGraphics);
    }
}
