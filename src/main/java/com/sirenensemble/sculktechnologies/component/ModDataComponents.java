package com.sirenensemble.sculktechnologies.component;

import com.mojang.serialization.Codec;
import com.sirenensemble.sculktechnologies.SculkTechnologies;
import net.minecraft.advancements.critereon.DamagePredicate;
import net.minecraft.advancements.critereon.ItemDamagePredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, SculkTechnologies.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> COORDINATES = register("coordinates",
            builder -> builder.persistent(BlockPos.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<DamagePredicate>> DURABILITY = register("durability", builder -> builder.persistent(DamagePredicate.CODEC));

private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register (String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator){
    return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
}

public static void register(IEventBus eventBus){
    DATA_COMPONENT_TYPES.register(eventBus);
}
}
