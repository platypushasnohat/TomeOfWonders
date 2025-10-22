package com.platypushasnohat.tome_of_wonders.events;

import com.platypushasnohat.tome_of_wonders.TomeOfWonders;
import com.platypushasnohat.tome_of_wonders.entities.Baitfish;
import com.platypushasnohat.tome_of_wonders.entities.FlyingFish;
import com.platypushasnohat.tome_of_wonders.entities.Squill;
import com.platypushasnohat.tome_of_wonders.registry.TOWEntities;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TomeOfWonders.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobEvents {

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(TOWEntities.BAITFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Baitfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(TOWEntities.FLYING_FISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FlyingFish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(TOWEntities.SQUILL.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, Squill::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(TOWEntities.BAITFISH.get(), Baitfish.createAttributes().build());
        event.put(TOWEntities.FLYING_FISH.get(), FlyingFish.createAttributes().build());
        event.put(TOWEntities.SQUILL.get(), Squill.createAttributes().build());
    }
}
