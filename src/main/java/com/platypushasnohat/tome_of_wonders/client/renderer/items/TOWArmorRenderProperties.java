package com.platypushasnohat.tome_of_wonders.client.renderer.items;

import com.mojang.blaze3d.vertex.PoseStack;
import com.platypushasnohat.tome_of_wonders.client.models.armor.WhirlicapModel;
import com.platypushasnohat.tome_of_wonders.items.WhirlicapItem;
import com.platypushasnohat.tome_of_wonders.registry.TOWModelLayers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class TOWArmorRenderProperties implements IClientItemExtensions {

    private static boolean init;

    public static WhirlicapModel WHIRLICAP_MODEL;

    public static void initializeModels() {
        init = true;
        WHIRLICAP_MODEL = new WhirlicapModel(Minecraft.getInstance().getEntityModels().bakeLayer(TOWModelLayers.WHIRLICAP));
    }

    @Override
    public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> humanoidModel) {

        if (!init) {
            initializeModels();
        }

        final var item = itemStack.getItem();

        if (item instanceof WhirlicapItem) {
            return entity == null ? WHIRLICAP_MODEL : WHIRLICAP_MODEL.withAnimations(entity);
        }

        return humanoidModel;
    }

    public static void renderCustomArmor(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, ItemStack itemStack, ArmorItem armorItem, Model armorModel, boolean legs, ResourceLocation texture) {

    }
}
