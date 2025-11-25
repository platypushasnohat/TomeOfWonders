package com.platypushasnohat.tome_of_wonders;

import net.minecraftforge.common.ForgeConfigSpec;

public class TomeOfWondersConfig {

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static final String CATEGORY_ITEMS = "items";

    public static final String CATEGORY_MOBS = "mobs";
    public static final String CATEGORY_BAITFISH = "baitfish";
    public static final String CATEGORY_FLYING_FISH = "flying_fish";
    public static final String CATEGORY_SQUILL = "squill";

    // common
    public static ForgeConfigSpec.IntValue SQUILL_SPAWN_HEIGHT;
    public static ForgeConfigSpec.BooleanValue SQUILL_SCHOOL_SPAWNING;

    public static ForgeConfigSpec.BooleanValue BAITFISH_SCHOOL_SPAWNING;
    public static ForgeConfigSpec.BooleanValue FLYING_FISH_SCHOOL_SPAWNING;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_BUILDER.push(CATEGORY_MOBS);

        COMMON_BUILDER.push(CATEGORY_BAITFISH);
        BAITFISH_SCHOOL_SPAWNING = COMMON_BUILDER.comment("Whether baitfish spawn in schools").define("baitfishSchoolSpawning", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push(CATEGORY_FLYING_FISH);
        FLYING_FISH_SCHOOL_SPAWNING = COMMON_BUILDER.comment("Whether flying fish spawn in schools").define("flyingFishSchoolSpawning", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.push(CATEGORY_SQUILL);
        SQUILL_SPAWN_HEIGHT = COMMON_BUILDER.comment("Spawn height for squills").defineInRange("squillSpawnHeight", 128, 0, 320);
        SQUILL_SCHOOL_SPAWNING = COMMON_BUILDER.comment("Whether squills spawn in schools").define("squillSchoolSpawning", true);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
