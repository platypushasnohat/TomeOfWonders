package com.platypushasnohat.tome_of_wonders.effects;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipe;

import javax.annotation.Nonnull;

public class TOWBrewingRecipe extends BrewingRecipe {

    private final Ingredient input;

    public TOWBrewingRecipe(Ingredient input, Ingredient ingredient, ItemStack output) {
        super(input, ingredient, output);
        this.input = input;
    }

    @Override
    public boolean isInput(@Nonnull ItemStack stack) {
        ItemStack[] matchingStacks = input.getItems();
        if (matchingStacks.length == 0) {
            return stack.isEmpty();
        } else {
            for (ItemStack itemstack : matchingStacks) {
                if (ItemStack.isSameItemSameTags(itemstack, stack)) {
                    return true;
                }
            }
            return false;
        }
    }

}
