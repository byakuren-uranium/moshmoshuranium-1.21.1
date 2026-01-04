package com.sirenensemble.sculktechnologies.entity;

import com.sirenensemble.sculktechnologies.SculkTechnologies;

import com.sirenensemble.sculktechnologies.entity.alive.InfectorEntity;
import com.sirenensemble.sculktechnologies.entity.projectile.AbstractEchoBullet;
import com.sirenensemble.sculktechnologies.entity.projectile.ThrowTormentedSoul;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntity {

    private static <T extends Entity> EntityType register(String key, EntityType.Builder<T> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, SculkTechnologies.MODID);

    public static final Supplier<EntityType<InfectorEntity>> INFECTOR = ENTITY_TYPES.register("infector", () -> EntityType.Builder.of(InfectorEntity::new, MobCategory.CREATURE)
            .sized(1f, 1f).build("infector"));

    public static final Supplier<EntityType<AbstractEchoBullet>> ABS_ECHO_BULLET = ENTITY_TYPES.register("abs_echo_bullet", () -> EntityType.Builder.<AbstractEchoBullet>of(AbstractEchoBullet::new, MobCategory.MISC)
            .sized(1f, 1f).build("abs_echo_bullet"));

    public static final Supplier<EntityType<ThrowTormentedSoul>> TORM_SOUL = ENTITY_TYPES.register("tormented_soul", () -> EntityType.Builder.<ThrowTormentedSoul>of(ThrowTormentedSoul::new, MobCategory.MISC)
            .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("tormented_soul"));




    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
