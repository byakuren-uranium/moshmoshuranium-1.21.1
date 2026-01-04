package com.sirenensemble.sculktechnologies.entity.projectile;

import com.sirenensemble.sculktechnologies.entity.ModEntity;
import com.sirenensemble.sculktechnologies.items.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;



public class ThrowTormentedSoul extends ThrowableItemProjectile {

    public ThrowTormentedSoul(EntityType<? extends ThrowTormentedSoul> entityType, Level level) {
        super(entityType, level);
        }

    public ThrowTormentedSoul(Level level, LivingEntity shooter) {
        super(EntityType.EXPERIENCE_BOTTLE, shooter, level);
    }

    public ThrowTormentedSoul(Level level, double x, double y, double z) {
        super(EntityType.EXPERIENCE_BOTTLE, x, y, z, level);
    }




    protected Item getDefaultItem() {
            return ModItems.TORMENTED_SOUL.asItem();
        }

        protected double getDefaultGravity() {
            return 0.05;
        }

        protected void onHit(HitResult result) {
            super.onHit(result);
            if (this.level() instanceof ServerLevel) {
                Vec3 vec3 = new Vec3(getX(), getY(), getZ());
                ((ServerLevel) this.level()).sendParticles(ParticleTypes.SOUL.getType(), vec3.x, vec3.y, vec3.z, 7, 0, 0, 0, 1);
                int i = 12 + this.level().random.nextInt(20) + this.level().random.nextInt(20);
                ExperienceOrb.award((ServerLevel)this.level(), this.position(), i);this.discard();
            }

        }

}



