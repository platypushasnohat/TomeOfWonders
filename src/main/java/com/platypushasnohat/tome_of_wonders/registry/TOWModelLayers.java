package com.platypushasnohat.tome_of_wonders.registry;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.platypushasnohat.tome_of_wonders.TomeOfWonders.modPrefix;

@OnlyIn(Dist.CLIENT)
public class TOWModelLayers {

    public static final ModelLayerLocation BAITFISH = main("baitfish");
    public static final ModelLayerLocation FLYING_FISH = main("flying_fish");
    public static final ModelLayerLocation SQUILL = main("squill");

    public static final ModelLayerLocation WHIRLICAP = main("whirlicap");
    public static final ModelLayerLocation WHIRLIGIG = main("whirligig");

    private static ModelLayerLocation register(String id, String name) {
        return new ModelLayerLocation(modPrefix(id), name);
    }

    private static ModelLayerLocation main(String id) {
        return register(id, "main");
    }
}
