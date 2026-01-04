package com.sirenensemble.sculktechnologies.worldgeneration.tree;

import com.sirenensemble.sculktechnologies.SculkTechnologies;
import com.sirenensemble.sculktechnologies.worldgeneration.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower SCULKWOOD = new TreeGrower(SculkTechnologies.MODID + ":sculkwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.SCULKWOOD_KEY), Optional.empty());
}
