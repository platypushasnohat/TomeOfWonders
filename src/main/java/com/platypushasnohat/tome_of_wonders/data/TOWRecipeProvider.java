package com.platypushasnohat.tome_of_wonders.data;

import com.platypushasnohat.tome_of_wonders.TomeOfWonders;
import com.platypushasnohat.tome_of_wonders.registry.TOWBlocks;
import com.platypushasnohat.tome_of_wonders.registry.TOWItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static com.platypushasnohat.tome_of_wonders.TomeOfWonders.modPrefix;
import static net.minecraft.data.recipes.RecipeCategory.COMBAT;
import static net.minecraft.data.recipes.RecipeCategory.REDSTONE;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.SimpleCookingRecipeBuilder.*;

public class TOWRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public TOWRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        cookingRecipes(TOWItems.RAW_BAITFISH.get(), TOWItems.COOKED_BAITFISH.get(), consumer);

        shaped(COMBAT, TOWItems.WHIRLICAP.get()).define('#', Items.LEATHER_HELMET).define('X', TOWBlocks.WHIRLIGIG.get()).pattern(" X ").pattern(" # ").unlockedBy("has_whirligig", has(TOWBlocks.WHIRLIGIG.get())).save(consumer);
        shaped(COMBAT, TOWItems.TOOTHED_SNOWBALL.get(), 4).define('#', Items.SNOWBALL).define('X', TOWItems.SQUILL_TOOTH.get()).pattern(" # ").pattern("#X#").pattern(" # ").unlockedBy("has_squill_tooth", has(TOWItems.SQUILL_TOOTH.get())).save(consumer);
        shaped(REDSTONE, TOWBlocks.WHIRLIBOX.get()).define('#', ItemTags.STONE_TOOL_MATERIALS).define('X', TOWBlocks.WHIRLIGIG.get()).define('Y', Tags.Items.DUSTS_REDSTONE).pattern("###").pattern("#X#").pattern("#Y#").unlockedBy("has_whirligig", has(TOWBlocks.WHIRLIGIG.get())).save(consumer);
    }

    private static void cookingRecipes(ItemLike ingredient, ItemLike result, Consumer<FinishedRecipe> consumer) {
        smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, 0.35F, 200).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, getSaveLocation(result));
        campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, 0.35F, 600).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, getSaveLocation(getName(result) + "_from_campfire_cooking"));
        smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, 0.35F, 100).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer, getSaveLocation(getName(result) + "_from_smoking"));
    }

    private static String getName(ItemLike object) {
        return ForgeRegistries.ITEMS.getKey(object.asItem()).getPath();
    }

    private static ResourceLocation getSaveLocation(ItemLike item) {
        return ForgeRegistries.ITEMS.getKey(item.asItem());
    }

    private static ResourceLocation getSaveLocation(String name) {
        return modPrefix(name);
    }

    private void wrap(RecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conditions) {
        wrap(builder, TomeOfWonders.MOD_ID, name, consumer, conditions);
    }

    private void wrap(RecipeBuilder builder, String modid, String name, Consumer<FinishedRecipe> consumer, ICondition... conditions) {
        ResourceLocation loc = new ResourceLocation(modid, name);
        ConditionalRecipe.Builder cond;
        if (conditions.length > 1) {
            cond = ConditionalRecipe.builder().addCondition(and(conditions));
        } else if (conditions.length == 1) {
            cond = ConditionalRecipe.builder().addCondition(conditions[0]);
        } else {
            cond = ConditionalRecipe.builder();
        }
        FinishedRecipe[] recipe = new FinishedRecipe[1];
        builder.save(f -> recipe[0] = f, loc);
        cond.addRecipe(recipe[0]).generateAdvancement().build(consumer, loc);
    }
}
