package com.platypushasnohat.tome_of_wonders.events;

import com.platypushasnohat.tome_of_wonders.TomeOfWonders;
import com.platypushasnohat.tome_of_wonders.client.models.BaitfishModel;
import com.platypushasnohat.tome_of_wonders.client.models.FlyingFishModel;
import com.platypushasnohat.tome_of_wonders.client.models.SquillModel;
import com.platypushasnohat.tome_of_wonders.client.models.armor.WhirlicapModel;
import com.platypushasnohat.tome_of_wonders.client.particles.WhirlibubbleParticle;
import com.platypushasnohat.tome_of_wonders.client.particles.WhirliwindParticle;
import com.platypushasnohat.tome_of_wonders.client.renderer.BaitfishRenderer;
import com.platypushasnohat.tome_of_wonders.client.renderer.FlyingFishRenderer;
import com.platypushasnohat.tome_of_wonders.client.renderer.SquillRenderer;
import com.platypushasnohat.tome_of_wonders.client.renderer.blockentity.WhirligigRenderer;
import com.platypushasnohat.tome_of_wonders.registry.TOWBlockEntities;
import com.platypushasnohat.tome_of_wonders.registry.TOWEntities;
import com.platypushasnohat.tome_of_wonders.registry.TOWModelLayers;
import com.platypushasnohat.tome_of_wonders.registry.TOWParticles;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = TomeOfWonders.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientEvents {

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(TOWParticles.WHIRLIWIND.get(), WhirliwindParticle.Factory::new);
        event.registerSpriteSet(TOWParticles.WHIRLIBUBBLE.get(), WhirlibubbleParticle.Factory::new);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(TOWEntities.BAITFISH.get(), BaitfishRenderer::new);
        event.registerEntityRenderer(TOWEntities.FLYING_FISH.get(), FlyingFishRenderer::new);
        event.registerEntityRenderer(TOWEntities.SQUILL.get(), SquillRenderer::new);
        event.registerEntityRenderer(TOWEntities.TOOTHED_SNOWBALL.get(), ThrownItemRenderer::new);
    }

    @SubscribeEvent
    public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(TOWBlockEntities.WHIRLIGIG_BLOCK_ENTITY.get(), WhirligigRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TOWModelLayers.BAITFISH, BaitfishModel::createBodyLayer);
        event.registerLayerDefinition(TOWModelLayers.FLYING_FISH, FlyingFishModel::createBodyLayer);
        event.registerLayerDefinition(TOWModelLayers.SQUILL, SquillModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerArmorLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TOWModelLayers.WHIRLICAP, WhirlicapModel::createArmorLayer);
    }

    @SubscribeEvent
    public static void registerBlockEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TOWModelLayers.WHIRLIGIG, WhirligigRenderer::createMesh);
    }
}
