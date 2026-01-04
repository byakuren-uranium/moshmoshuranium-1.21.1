package com.sirenensemble.sculktechnologies.items;

import com.sirenensemble.sculktechnologies.util.ModTags;
import com.sirenensemble.sculktechnologies.util.ModTags.*;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier SCULK = new SimpleTier(Blocks.INCORRECT_FOR_SCULK_TOOL, 1600, 10f, 1f, 30, ()-> Ingredient.of(ModItems.SCULK_ORE));



}
