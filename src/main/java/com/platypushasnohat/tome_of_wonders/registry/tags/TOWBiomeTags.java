package com.platypushasnohat.tome_of_wonders.registry.tags;

import com.platypushasnohat.tome_of_wonders.TomeOfWonders;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class TOWBiomeTags {

    public static final TagKey<Biome> HAS_SQUILL = modBiomeTag("has_mob/squill");
    public static final TagKey<Biome> HAS_BAITFISH = modBiomeTag("has_mob/baitfish");
    public static final TagKey<Biome> HAS_FLYING_FISH = modBiomeTag("has_mob/flying_fish");

    private static TagKey<Biome> modBiomeTag(String name) {
        return biomeTag(TomeOfWonders.MOD_ID, name);
    }

    private static TagKey<Biome> forgeBiomeTag(String name) {
        return biomeTag("forge", name);
    }

    public static TagKey<Biome> biomeTag(String modid, String name) {
        return TagKey.create(Registries.BIOME, new ResourceLocation(modid, name));
    }
}
