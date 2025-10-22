package com.platypushasnohat.tome_of_wonders.registry;

import com.platypushasnohat.tome_of_wonders.TomeOfWonders;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TOWParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, TomeOfWonders.MOD_ID);

    public static final RegistryObject<SimpleParticleType> WHIRLIWIND = PARTICLE_TYPES.register("whirliwind", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> WHIRLIBUBBLE = PARTICLE_TYPES.register("whirlibubble", () -> new SimpleParticleType(false));

}
