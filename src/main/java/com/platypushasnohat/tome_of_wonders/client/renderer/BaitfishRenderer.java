package com.platypushasnohat.tome_of_wonders.client.renderer;

import com.platypushasnohat.tome_of_wonders.client.models.BaitfishModel;
import com.platypushasnohat.tome_of_wonders.entities.Baitfish;
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
public class BaitfishRenderer extends MobRenderer<Baitfish, BaitfishModel> {

    private static final ResourceLocation TEXTURE_RIVER = modPrefix("textures/entity/baitfish/baitfish.png");
    private static final ResourceLocation TEXTURE_OCEAN = modPrefix("textures/entity/baitfish/baitfish.png");

    public BaitfishRenderer(EntityRendererProvider.Context context) {
        super(context, new BaitfishModel(context.bakeLayer(TOWModelLayers.BAITFISH)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(Baitfish entity) {
        if (entity.getVariant() == 1) return TEXTURE_OCEAN;
        else return TEXTURE_RIVER;
    }

    @Override
    protected @Nullable RenderType getRenderType(Baitfish entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        return RenderType.entityCutout(getTextureLocation(entity));
    }
}
