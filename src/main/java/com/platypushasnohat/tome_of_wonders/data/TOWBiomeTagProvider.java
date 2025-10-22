package com.platypushasnohat.tome_of_wonders.data;

import com.platypushasnohat.tome_of_wonders.TomeOfWonders;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.platypushasnohat.tome_of_wonders.registry.tags.TOWBiomeTags.*;

public class TOWBiomeTagProvider extends BiomeTagsProvider {

    public TOWBiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper helper) {
        super(output, provider, TomeOfWonders.MOD_ID, helper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {

        this.tag(HAS_BAITFISH).add(
                Biomes.OCEAN,
                Biomes.DEEP_OCEAN,
                Biomes.LUKEWARM_OCEAN,
                Biomes.DEEP_LUKEWARM_OCEAN
        );

        this.tag(HAS_FLYING_FISH).add(
                Biomes.OCEAN,
                Biomes.DEEP_OCEAN
        );

        this.tag(HAS_SQUILL).addTag(BiomeTags.IS_OVERWORLD);
    }
}
