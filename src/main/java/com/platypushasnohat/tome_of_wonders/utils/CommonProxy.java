package com.platypushasnohat.tome_of_wonders.utils;

import com.platypushasnohat.tome_of_wonders.TomeOfWonders;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TomeOfWonders.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {
    public void init() {
    }

    public void clientInit() {
    }

    public Object getArmorRenderProperties() {
        return null;
    }
}
