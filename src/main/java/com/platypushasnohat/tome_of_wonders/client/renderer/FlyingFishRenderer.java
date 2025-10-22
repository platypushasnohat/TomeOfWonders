package com.platypushasnohat.tome_of_wonders.client.renderer;

import com.platypushasnohat.tome_of_wonders.client.models.FlyingFishModel;
import com.platypushasnohat.tome_of_wonders.entities.FlyingFish;
import com.platypushasnohat.tome_of_wonders.registry.TOWModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import static com.platypushasnohat.tome_of_wonders.TomeOfWonders.modPrefix;

@OnlyIn(Dist.CLIENT)
public class FlyingFishRenderer extends MobRenderer<FlyingFish, FlyingFishModel> {

    private static final ResourceLocation TEXTURE = modPrefix("textures/entity/flying_fish/flying_fish.png");

    public FlyingFishRenderer(EntityRendererProvider.Context context) {
        super(context, new FlyingFishModel(context.bakeLayer(TOWModelLayers.FLYING_FISH)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(FlyingFish entity) {
        return TEXTURE;
    }

    @Override
    protected @Nullable RenderType getRenderType(FlyingFish entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        return RenderType.entityCutout(getTextureLocation(entity));
    }
}
