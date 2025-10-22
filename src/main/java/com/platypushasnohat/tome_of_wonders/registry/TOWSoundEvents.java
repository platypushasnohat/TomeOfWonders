package com.platypushasnohat.tome_of_wonders.registry;

import com.platypushasnohat.tome_of_wonders.TomeOfWonders;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.platypushasnohat.tome_of_wonders.TomeOfWonders.modPrefix;

@Mod.EventBusSubscriber(modid = TomeOfWonders.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TOWSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TomeOfWonders.MOD_ID);

    public static final RegistryObject<SoundEvent> FISH_HURT = registerSoundEvent("fish_hurt");
    public static final RegistryObject<SoundEvent> FISH_DEATH = registerSoundEvent("fish_death");
    public static final RegistryObject<SoundEvent> FISH_FLOP = registerSoundEvent("fish_flop");

    public static final RegistryObject<SoundEvent> SQUILL_HURT = registerSoundEvent("squill_hurt");
    public static final RegistryObject<SoundEvent> SQUILL_DEATH = registerSoundEvent("squill_death");
    public static final RegistryObject<SoundEvent> SQUILL_SQUIRT = registerSoundEvent("squill_squirt");
    public static final RegistryObject<SoundEvent> SQUILL_CHATTER = registerSoundEvent("squill_chatter");

    private static RegistryObject<SoundEvent> registerSoundEvent(final String soundName) {
        return SOUND_EVENTS.register(soundName, () -> SoundEvent.createVariableRangeEvent(modPrefix(soundName)));
    }
}
