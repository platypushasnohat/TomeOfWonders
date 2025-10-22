package com.platypushasnohat.tome_of_wonders.registry;

import com.platypushasnohat.tome_of_wonders.effects.TOWBrewingRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class TOWBrewingRecipes {

    public static void registerPotionRecipes() {
        BrewingRecipeRegistry.addRecipe(new TOWBrewingRecipe(Ingredient.of(registerPotion(Potions.SLOW_FALLING)), Ingredient.of(TOWItems.SQUILL_TOOTH.get()), registerPotion(TOWPotions.LEVITATION_POTION)));
    }

    public static ItemStack registerPotion(RegistryObject<Potion> potion) {
        return registerPotion(potion.get());
    }

    public static ItemStack registerPotion(Potion potion) {
        return PotionUtils.setPotion(new ItemStack(Items.POTION), potion);
    }

    public static ItemStack registerSplashPotion(Potion potion) {
        return PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion);
    }

    public static ItemStack registerLingeringPotion(Potion potion) {
        return PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), potion);
    }

    public static ItemStack registerTippedArrow(Potion potion) {
        return PotionUtils.setPotion(new ItemStack(Items.TIPPED_ARROW), potion);
    }
}
