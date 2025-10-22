package com.platypushasnohat.tome_of_wonders.registry;

import com.platypushasnohat.tome_of_wonders.TomeOfWonders;
import com.platypushasnohat.tome_of_wonders.entities.Baitfish;
import com.platypushasnohat.tome_of_wonders.entities.FlyingFish;
import com.platypushasnohat.tome_of_wonders.entities.Squill;
import com.platypushasnohat.tome_of_wonders.entities.projectile.ToothedSnowball;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.platypushasnohat.tome_of_wonders.TomeOfWonders.modPrefix;

@Mod.EventBusSubscriber(modid = TomeOfWonders.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TOWEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TomeOfWonders.MOD_ID);

    public static final RegistryObject<EntityType<Baitfish>> BAITFISH = ENTITY_TYPES.register(
            "baitfish", () ->
            EntityType.Builder.of(Baitfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.4F, 0.2F)
                    .clientTrackingRange(10)
                    .build(modPrefix("baitfish").toString())
    );

    public static final RegistryObject<EntityType<FlyingFish>> FLYING_FISH = ENTITY_TYPES.register(
            "flying_fish", () ->
            EntityType.Builder.of(FlyingFish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.4F, 0.2F)
                    .clientTrackingRange(10)
                    .build(modPrefix("flying_fish").toString())
    );

    public static final RegistryObject<EntityType<Squill>> SQUILL = ENTITY_TYPES.register(
            "squill", () ->
            EntityType.Builder.of(Squill::new, MobCategory.CREATURE)
                    .sized(0.8F, 0.8F)
                    .clientTrackingRange(10)
                    .build(modPrefix("squill").toString())
    );

    public static final RegistryObject<EntityType<ToothedSnowball>> TOOTHED_SNOWBALL = ENTITY_TYPES.register(
            "toothed_snowball", () ->
            EntityType.Builder.<ToothedSnowball>of(ToothedSnowball::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build(modPrefix("toothed_snowball").toString())
    );
}
