package com.platypushasnohat.tome_of_wonders.registry.tags;

import com.platypushasnohat.tome_of_wonders.TomeOfWonders;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class TOWBlockTags {

    private static TagKey<Block> modBlockTag(String name) {
        return blockTag(TomeOfWonders.MOD_ID, name);
    }

    private static TagKey<Block> forgeBlockTag(String name) {
        return blockTag("forge", name);
    }

    public static TagKey<Block> blockTag(String modid, String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(modid, name));
    }
}
