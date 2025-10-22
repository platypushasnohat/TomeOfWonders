package com.platypushasnohat.tome_of_wonders;

import com.platypushasnohat.tome_of_wonders.registry.TOWBlocks;
import com.platypushasnohat.tome_of_wonders.registry.TOWBrewingRecipes;
import com.platypushasnohat.tome_of_wonders.registry.TOWPotions;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.platypushasnohat.tome_of_wonders.registry.TOWItems.*;

public class TomeOfWondersTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TomeOfWonders.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TOME_OF_WONDERS_TAB = CREATIVE_TAB.register("tome_of_wonders",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(WHIRLICAP.get()))
                    .title(Component.translatable("itemGroup.tome_of_wonders"))
                    .displayItems((parameters, output) -> {

                        output.accept(SQUILL_SPAWN_EGG.get());
                        output.accept(SQUILL_TOOTH.get());
                        output.accept(TOOTHED_SNOWBALL.get());
                        output.accept(TOWBrewingRecipes.registerPotion(TOWPotions.LEVITATION_POTION.get()));
                        output.accept(TOWBrewingRecipes.registerSplashPotion(TOWPotions.LEVITATION_POTION.get()));
                        output.accept(TOWBrewingRecipes.registerLingeringPotion(TOWPotions.LEVITATION_POTION.get()));
                        output.accept(TOWBrewingRecipes.registerTippedArrow(TOWPotions.LEVITATION_POTION.get()));
                        output.accept(TOWBlocks.WHIRLIGIG.get());
                        output.accept(WHIRLICAP.get());
                        output.accept(TOWBlocks.WHIRLIBOX.get());
                        output.accept(LOLLIPOP.get());
                        output.accept(SQUILL_BUCKET.get());

                        output.accept(BAITFISH_SPAWN_EGG.get());
                        output.accept(RAW_BAITFISH.get());
                        output.accept(COOKED_BAITFISH.get());
                        output.accept(BAITFISH_BUCKET.get());

                        output.accept(FLYING_FISH_SPAWN_EGG.get());
                        output.accept(FLYING_FISH.get());
                        output.accept(FLYING_FISH_BUCKET.get());

                    }).build());
}