package com.platypushasnohat.tome_of_wonders.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class TOWFoodValues {

    public static final FoodProperties RAW_BAITFISH = (new FoodProperties.Builder())
            .nutrition(1).saturationMod(0.1F)
            .fast()
            .build();

    public static final FoodProperties COOKED_BAITFISH = (new FoodProperties.Builder())
            .nutrition(3).saturationMod(0.5F)
            .fast()
            .build();

    public static final FoodProperties FLYING_FISH = (new FoodProperties.Builder())
            .nutrition(1).saturationMod(0.2F)
            .build();

    public static final FoodProperties LOLLIPOP = (new FoodProperties.Builder())
            .nutrition(3).saturationMod(0.15F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 1), 1)
            .alwaysEat()
            .build();

}
