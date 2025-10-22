package com.platypushasnohat.tome_of_wonders.registry;

import com.platypushasnohat.tome_of_wonders.TomeOfWonders;
import com.platypushasnohat.tome_of_wonders.items.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class TOWItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TomeOfWonders.MOD_ID);
    public static List<RegistryObject<? extends Item>> AUTO_TRANSLATE = new ArrayList<>();

    public static final RegistryObject<Item> SQUILL_TOOTH = registerItem("squill_tooth", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TOOTHED_SNOWBALL = registerItem("toothed_snowball", () -> new ToothedSnowballItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> WHIRLICAP = registerItem("whirlicap", () -> new WhirlicapItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> LOLLIPOP = registerItem("lollipop", () -> new LollipopItem(foodItem(TOWFoodValues.LOLLIPOP).stacksTo(1)));

    public static final RegistryObject<Item> RAW_BAITFISH = registerItemNoLang("baitfish", () -> new Item(foodItem(TOWFoodValues.RAW_BAITFISH)));
    public static final RegistryObject<Item> COOKED_BAITFISH = registerItem("cooked_baitfish", () -> new Item(foodItem(TOWFoodValues.COOKED_BAITFISH)));
    public static final RegistryObject<Item> BAITFISH_BUCKET = registerItemNoLang("baitfish_bucket", () -> new TOWMobBucketItem(TOWEntities.BAITFISH, Fluids.WATER, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> FLYING_FISH = registerItem("flying_fish", () -> new Item(foodItem(TOWFoodValues.FLYING_FISH)));
    public static final RegistryObject<Item> FLYING_FISH_BUCKET = registerItemNoLang("flying_fish_bucket", () -> new TOWMobBucketItem(TOWEntities.FLYING_FISH, Fluids.WATER, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SQUILL_BUCKET = registerItemNoLang("squill_bucket", () -> new SquillBucketItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> BAITFISH_SPAWN_EGG = registerSpawnEggItem("baitfish", TOWEntities.BAITFISH, 0xe0e0e0, 0x757575);
    public static final RegistryObject<Item> FLYING_FISH_SPAWN_EGG = registerSpawnEggItem("flying_fish", TOWEntities.FLYING_FISH, 0x147bb5, 0xffe0db);
    public static final RegistryObject<Item> SQUILL_SPAWN_EGG = registerSpawnEggItem("squill", TOWEntities.SQUILL, 0xe1f7fe, 0x95c0d7);

    private static <I extends Item> RegistryObject<I> registerItem(String name, Supplier<? extends I> supplier) {
        RegistryObject<I> item = ITEMS.register(name, supplier);
        AUTO_TRANSLATE.add(item);
        return item;
    }

    private static <I extends Item> RegistryObject<I> registerItemNoLang(String name, Supplier<? extends I> supplier) {
        RegistryObject<I> item = ITEMS.register(name, supplier);
        return item;
    }

    private static RegistryObject<Item> registerSpawnEggItem(String name, RegistryObject type, int baseColor, int spotColor) {
        return registerItem(name + "_spawn_egg", () -> new ForgeSpawnEggItem(type, baseColor, spotColor, new Item.Properties()));
    }

    public static Item.Properties foodItem(FoodProperties food) {
        return new Item.Properties().food(food);
    }
}
