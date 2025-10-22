package com.platypushasnohat.tome_of_wonders;

import net.minecraftforge.common.ForgeConfigSpec;

public class TomeOfWondersConfig {

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    // common
    public static ForgeConfigSpec.ConfigValue<Integer> SQUILL_SPAWN_HEIGHT;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        SQUILL_SPAWN_HEIGHT = COMMON_BUILDER
                .comment("Spawn height for Squills")
                .define("squillSpawnHeight", 128);

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
