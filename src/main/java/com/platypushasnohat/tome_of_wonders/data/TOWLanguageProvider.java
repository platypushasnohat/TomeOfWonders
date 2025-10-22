package com.platypushasnohat.tome_of_wonders.data;

import com.platypushasnohat.tome_of_wonders.TomeOfWonders;
import com.platypushasnohat.tome_of_wonders.TomeOfWondersTab;
import com.platypushasnohat.tome_of_wonders.registry.*;
import com.platypushasnohat.tome_of_wonders.utils.TOWTextUtils;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;
import java.util.function.Supplier;

public class TOWLanguageProvider extends LanguageProvider {

    public TOWLanguageProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), TomeOfWonders.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        this.addTab(TomeOfWondersTab.TOME_OF_WONDERS_TAB.get(), "Tome of Wonders");

        TOWItems.AUTO_TRANSLATE.forEach(this::forItems);
        TOWBlocks.AUTO_TRANSLATE.forEach(this::forBlocks);
        TOWPaintings.PAINTING_TRANSLATIONS.forEach(this::painting);

        this.addItem(TOWItems.RAW_BAITFISH, "Raw Baitfish");

        this.addItem(TOWItems.FLYING_FISH_BUCKET, "Bucket of Flying Fish");
        this.addItem(TOWItems.SQUILL_BUCKET, "Bucket of Squill");
        this.addItem(TOWItems.BAITFISH_BUCKET, "Bucket of Baitfish");

        this.forEntity(TOWEntities.BAITFISH);
        this.forEntity(TOWEntities.FLYING_FISH);
        this.forEntity(TOWEntities.SQUILL);

        this.sound(TOWSoundEvents.FISH_DEATH, "Fish dies");
        this.sound(TOWSoundEvents.FISH_HURT, "Fish hurts");
        this.sound(TOWSoundEvents.FISH_FLOP, "Fish flops");

        this.sound(TOWSoundEvents.SQUILL_DEATH, "Squill dies");
        this.sound(TOWSoundEvents.SQUILL_HURT, "Squill hurts");
        this.sound(TOWSoundEvents.SQUILL_SQUIRT, "Squill squirts");
        this.sound(TOWSoundEvents.SQUILL_CHATTER, "Squill chatters");

        this.potion(TOWPotions.LEVITATION_POTION, "Levitation", "levitation");
    }

    @Override
    public String getName() {
        return  TomeOfWonders.MOD_ID + " Languages: en_us";
    }

    private void forBlocks(Supplier<? extends Block> block) {
        addBlock(block, TOWTextUtils.createTranslation(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath()));
    }

    private void forItems(Supplier<? extends Item> item) {
        addItem(item, TOWTextUtils.createTranslation(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item.get())).getPath()));
    }

    private void forEntity(Supplier<? extends EntityType<?>> entity) {
        addEntityType(entity, TOWTextUtils.createTranslation(Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(entity.get())).getPath()));
    }

    public void sound(Supplier<? extends SoundEvent> key, String subtitle){
        add("subtitles.shifted_lens." + key.get().getLocation().getPath(), subtitle);
    }

    private void addEnchantmentWithDesc(Enchantment enchantment, String description) {
        String name = ForgeRegistries.ENCHANTMENTS.getKey(enchantment).getPath();
        this.add(enchantment, formatEnchantment(name));
        this.add(enchantment.getDescriptionId() + ".desc", description);
    }

    private String formatEnchantment(String path) {
        return WordUtils.capitalizeFully(path.replace("_", " ")).replace("Of ", "of ");
    }

    public void potion(Supplier<? extends Potion> key, String name, String regName) {
        potions(key.get(), name, regName);
    }

    public void potions(Potion key, String name, String regName) {
        add("item.minecraft.potion.effect." + regName, "Potion of " + name);
        add("item.minecraft.splash_potion.effect." + regName, "Splash Potion of " + name);
        add("item.minecraft.lingering_potion.effect." + regName, "Lingering Potion of " + name);
        add("item.minecraft.tipped_arrow.effect." + regName, "Arrow of " + name);
    }

    protected void painting(String name, String author) {
        add("painting." + TomeOfWonders.MOD_ID + "." + name + ".title",  TOWTextUtils.createTranslation(name));
        add("painting." + TomeOfWonders.MOD_ID + "." + name + ".author",  author);
    }

    public void addTab(CreativeModeTab key, String name){
        add(key.getDisplayName().getString(), name);
    }

    public void addAdvancement(String key, String name) {
        this.add("advancement." + TomeOfWonders.MOD_ID + "." + key, name);
    }

    public void addAdvancementDesc(String key, String name) {
        this.add("advancement." + TomeOfWonders.MOD_ID + "." + key + ".desc", name);
    }
}
