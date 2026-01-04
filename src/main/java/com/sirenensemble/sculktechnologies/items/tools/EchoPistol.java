package com.sirenensemble.sculktechnologies.items.tools;

import com.sirenensemble.sculktechnologies.items.ModItems;
import com.sirenensemble.sculktechnologies.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class EchoPistol extends ProjectileWeaponItem {
    private static final int DEFAULT_PROJECTILE_RANGE = 20;
    private final Predicate<ItemStack> supportedprojectile = (ModItem)-> ModItem.is(ModTags.Items.ECHO_PISTOL_ITEM);


    public EchoPistol(Properties properties, Predicate<ItemStack> stackPredicate) {
        super(properties);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return supportedprojectile;
    }

    @Override
    public int getDefaultProjectileRange() {
        return DEFAULT_PROJECTILE_RANGE;
    }

    @Override
    protected void shootProjectile(LivingEntity livingEntity, Projectile projectile, int i, float v, float v1, float v2, @Nullable LivingEntity livingEntity1) {
     Level level = livingEntity.level();
        BlockPos pos = livingEntity.getOnPos();

     if(!level.isClientSide){

     }
    }
}

