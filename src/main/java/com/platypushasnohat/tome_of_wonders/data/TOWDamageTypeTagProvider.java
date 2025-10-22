package com.platypushasnohat.tome_of_wonders.data;

import com.platypushasnohat.tome_of_wonders.TomeOfWonders;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class TOWDamageTypeTagProvider extends TagsProvider<DamageType> {

    public TOWDamageTypeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, Registries.DAMAGE_TYPE, provider, TomeOfWonders.MOD_ID, helper);
    }

    protected void addTags(HolderLookup.Provider provider) {

    }
}
