package com.platypushasnohat.tome_of_wonders.entities;

import com.platypushasnohat.tome_of_wonders.TomeOfWondersConfig;
import com.platypushasnohat.tome_of_wonders.entities.ai.goals.CustomRandomSwimGoal;
import com.platypushasnohat.tome_of_wonders.registry.TOWEntities;
import com.platypushasnohat.tome_of_wonders.registry.TOWItems;
import com.platypushasnohat.tome_of_wonders.registry.TOWSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FollowFlockLeaderGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Baitfish extends AbstractSchoolingFish {

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Baitfish.class, EntityDataSerializers.INT);

    public final AnimationState flopAnimationState = new AnimationState();
    public final AnimationState swimmingAnimationState = new AnimationState();

    public float prevOnLandProgress;
    public float onLandProgress;

    public Baitfish(EntityType<? extends AbstractSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 5, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
        return new WaterBoundPathNavigation(this, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(1, new CustomRandomSwimGoal(this, 1, 1, 16, 16, 3));
        this.goalSelector.addGoal(4, new FollowFlockLeaderGoal(this));
    }

    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.0F);
    }

    @Override
    public @NotNull ItemStack getBucketItemStack() {
        return new ItemStack(TOWItems.BAITFISH_BUCKET.get());
    }

    @Override
    public void travel(@NotNull Vec3 travelVec) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), travelVec);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        } else {
            super.travel(travelVec);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.prevOnLandProgress = onLandProgress;
        boolean onLand = !this.isInWaterOrBubble();
        if (onLand && onLandProgress < 5F) {
            onLandProgress++;
        }
        if (!onLand && onLandProgress > 0F) {
            onLandProgress--;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) this.setupAnimationStates();
    }

    private void setupAnimationStates() {
        this.flopAnimationState.animateWhen(!this.isInWaterOrBubble(), this.tickCount);
        this.swimmingAnimationState.animateWhen(this.isInWaterOrBubble(), this.tickCount);
    }

    @Override
    public void calculateEntityAnimation(boolean flying) {
        float f1 = (float) Mth.length(this.getX() - this.xo, this.getY() - this.yo, this.getZ() - this.zo);
        float f2 = Math.min(f1 * 10.0F, 1.0F);
        this.walkAnimation.update(f2, 0.4F);
    }

    public static boolean canSpawn(EntityType<Baitfish> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(entityType, level, spawnType, pos, random);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Variant", this.getVariant());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setVariant(compoundTag.getInt("Variant"));
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    @Override
    protected float getStandingEyeHeight(@NotNull Pose pose, EntityDimensions size) {
        return size.height * 0.5F;
    }

    @Override
    public int getMaxSchoolSize() {
        return 72;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag compoundTag) {
        this.setVariant(level().getRandom().nextInt(1));
        if ((spawnType == MobSpawnType.CHUNK_GENERATION || spawnType == MobSpawnType.NATURAL) && TomeOfWondersConfig.BAITFISH_SCHOOL_SPAWNING.get()) {
            int schoolCount = (int) (this.getMaxSchoolSize() * this.getRandom().nextFloat());
            if (schoolCount > 0 && !this.level().isClientSide()) {
                for (int i = 0; i < schoolCount; i++) {
                    float distance = 1.5F;
                    Baitfish entity = new Baitfish(TOWEntities.BAITFISH.get(), this.level());
                    entity.setVariant(this.getVariant());
                    entity.moveTo(this.getX() + this.getRandom().nextFloat() * distance, this.getY() + this.getRandom().nextFloat() * distance, this.getZ() + this.getRandom().nextFloat() * distance);
                    entity.startFollowing(this);
                    this.level().addFreshEntity(entity);
                }
            }
        }
        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound() {
        return TOWSoundEvents.FISH_DEATH.get();
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(@NotNull DamageSource source) {
        return TOWSoundEvents.FISH_HURT.get();
    }

    @Override
    protected @NotNull SoundEvent getFlopSound() {
        return TOWSoundEvents.FISH_FLOP.get();
    }
}
