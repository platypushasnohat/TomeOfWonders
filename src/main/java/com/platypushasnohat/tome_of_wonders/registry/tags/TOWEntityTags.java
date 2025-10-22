package com.platypushasnohat.tome_of_wonders.registry.tags;

import com.platypushasnohat.tome_of_wonders.TomeOfWonders;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class TOWEntityTags {

    public static final TagKey<EntityType<?>> SQUILL_TARGETS = modEntityTag("squill_targets");

    private static TagKey<EntityType<?>> modEntityTag(String name) {
        return entityTag(TomeOfWonders.MOD_ID, name);
    }

    private static TagKey<EntityType<?>> forgeEntityTag(String name) {
        return entityTag("forge", name);
    }

    public static TagKey<EntityType<?>> entityTag(String modid, String name) {
        return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(modid, name));
    }
}
