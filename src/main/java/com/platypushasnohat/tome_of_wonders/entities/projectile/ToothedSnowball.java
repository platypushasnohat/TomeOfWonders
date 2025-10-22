package com.platypushasnohat.tome_of_wonders.entities.projectile;

import com.platypushasnohat.tome_of_wonders.registry.TOWEntities;
import com.platypushasnohat.tome_of_wonders.registry.TOWItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ToothedSnowball extends ThrowableItemProjectile {

    public ToothedSnowball(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public ToothedSnowball(Level level, LivingEntity entity) {
        super(TOWEntities.TOOTHED_SNOWBALL.get(), entity, level);
    }

    public ToothedSnowball(Level level, double x, double y, double z) {
        super(TOWEntities.TOOTHED_SNOWBALL.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return TOWItems.TOOTHED_SNOWBALL.get();
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity entity = hitResult.getEntity();
        int damage = entity instanceof Blaze ? 5 : 2;
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float) damage);
        entity.setTicksFrozen(Math.min(entity.getTicksRequiredToFreeze() * 4, entity.getTicksFrozen() + 120));
    }

    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItemRaw();
        return itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, itemstack);
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            ParticleOptions particleoptions = this.getParticle();
            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
