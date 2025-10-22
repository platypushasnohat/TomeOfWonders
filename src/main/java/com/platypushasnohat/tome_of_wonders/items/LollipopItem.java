package com.platypushasnohat.tome_of_wonders.items;

import com.platypushasnohat.tome_of_wonders.registry.enums.TOWItemTiers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class LollipopItem extends SwordItem {

    public LollipopItem(Properties properties) {
        super(TOWItemTiers.CANDY, 2, -2.4F, properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        InteractionHand hand = InteractionHand.MAIN_HAND;
        if (isEdible()) {
            entity.eat(level, stack.copy());
            stack.hurtAndBreak(1, entity, (entity1) -> entity1.broadcastBreakEvent(hand));
            ((Player) entity).getCooldowns().addCooldown(this, 300);
        }
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 48;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.category.canEnchant(stack.getItem()) && enchantment != Enchantments.MENDING;
    }
}
