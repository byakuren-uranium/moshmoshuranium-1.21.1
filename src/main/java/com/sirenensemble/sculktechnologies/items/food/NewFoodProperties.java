package com.sirenensemble.sculktechnologies.items.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class NewFoodProperties {
  //  public static final FoodProperties SCULK_GRASS = new FoodProperties.Builder().nutrition(2).saturationModifier(1f).effect(()-> new MobEffectInstance(MobEffects.REGENERATION, 40, 0), 1).build();
    public static final FoodProperties SCULK_CARROT = new FoodProperties.Builder().nutrition(4).saturationModifier(0.8f).effect(()-> new MobEffectInstance(MobEffects.REGENERATION, 100, 1), 0.8f).build();
    public static final FoodProperties SCULK_SWEET_BERRY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.5f).effect(()-> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 60, 1), 1).fast().alwaysEdible().build();
    public static final FoodProperties SCULK_POTATO = new FoodProperties.Builder().nutrition(2).saturationModifier(0.5f).effect(()-> new MobEffectInstance(MobEffects.REGENERATION, 100, 1), 0.8f).build();

    public static final FoodProperties BAKED_SCULK_POTATO = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).effect(()-> new MobEffectInstance(MobEffects.REGENERATION, 100, 1), 0.8f).build();


    public static final FoodProperties SCULK_BEETROOT = new FoodProperties.Builder().nutrition(3).saturationModifier(0.8f).effect(()-> new MobEffectInstance(MobEffects.REGENERATION, 100, 1), 0.8f).build();

}
