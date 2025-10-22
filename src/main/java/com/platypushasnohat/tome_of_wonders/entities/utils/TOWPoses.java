package com.platypushasnohat.tome_of_wonders.entities.utils;

import net.minecraft.world.entity.Pose;

public enum TOWPoses {

    BEAM_START,
    BEAM,
    BEAM_END;

    public Pose get() {
        return Pose.valueOf(this.name());
    }
}
