package com.sirenensemble.sculktechnologies.blocks.blockentity;

import com.sirenensemble.sculktechnologies.SculkTechnologies;
import com.sirenensemble.sculktechnologies.blocks.ModBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, SculkTechnologies.MODID);

public static final Supplier<BlockEntityType<ExpSculkConverterEntity>> EXPSCULKCONVERTER_BE =
        BLOCK_ENTITY.register("expsculkconverter_be", () ->
                BlockEntityType.Builder.of(ExpSculkConverterEntity::new,
                        ModBlock.EXPSCULKCONVERTER.get()).build(null));

public static final Supplier<BlockEntityType<SculkInjectorEntity>> SCULK_INJECTOR_BE =
        BLOCK_ENTITY.register("sculk_injector_be", () ->
                BlockEntityType.Builder.of(SculkInjectorEntity::new,
                        ModBlock.SCULK_INJECTOR.get()).build(null));


public static final Supplier<BlockEntityType<SculkInfectorEntity>> SCULK_INFECTOR_BE =
        BLOCK_ENTITY.register("sculk_infector", () ->
                BlockEntityType.Builder.of(SculkInfectorEntity::new,
                        ModBlock.SCULK_INFECTOR.get()).build(null));

public static void register(IEventBus eventBus){
    BLOCK_ENTITY.register(eventBus);
}
}
