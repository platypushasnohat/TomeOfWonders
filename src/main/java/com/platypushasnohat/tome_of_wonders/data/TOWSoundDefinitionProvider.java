package com.platypushasnohat.tome_of_wonders.data;

import com.platypushasnohat.tome_of_wonders.TomeOfWonders;
import com.platypushasnohat.tome_of_wonders.registry.TOWSoundEvents;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

import java.util.function.Supplier;

import static com.platypushasnohat.tome_of_wonders.TomeOfWonders.modPrefix;

@SuppressWarnings("SameParameterValue")
public class TOWSoundDefinitionProvider extends SoundDefinitionsProvider {

    public TOWSoundDefinitionProvider(PackOutput packOutput, ExistingFileHelper helper) {
        super(packOutput, TomeOfWonders.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        this.sound(TOWSoundEvents.FISH_HURT,
                sound("entity/fish/hurt1"),
                sound("entity/fish/hurt2"),
                sound("entity/fish/hurt3"),
                sound("entity/fish/hurt4")
        );

        this.sound(TOWSoundEvents.FISH_DEATH,
                sound("entity/fish/hurt1"),
                sound("entity/fish/hurt2"),
                sound("entity/fish/hurt3"),
                sound("entity/fish/hurt4")
        );

        this.sound(TOWSoundEvents.FISH_FLOP,
                sound("entity/fish/flop1").volume(0.3F),
                sound("entity/fish/flop2").volume(0.3F),
                sound("entity/fish/flop3").volume(0.3F),
                sound("entity/fish/flop4").volume(0.3F)
        );

        this.sound(TOWSoundEvents.SQUILL_HURT,
                sound("entity/squid/hurt1").pitch(1.5F),
                sound("entity/squid/hurt2").pitch(1.5F),
                sound("entity/squid/hurt3").pitch(1.5F),
                sound("entity/squid/hurt4").pitch(1.5F)
        );

        this.sound(TOWSoundEvents.SQUILL_DEATH,
                sound("entity/squid/death1").pitch(1.5F),
                sound("entity/squid/death2").pitch(1.5F),
                sound("entity/squid/death3").pitch(1.5F)
        );

        this.sound(TOWSoundEvents.SQUILL_SQUIRT,
                sound("entity/squid/squirt1").pitch(1.5F),
                sound("entity/squid/squirt2").pitch(1.5F),
                sound("entity/squid/squirt3").pitch(1.5F)
        );

        this.sound(TOWSoundEvents.SQUILL_CHATTER,
                sound(modPrefix("entity/squill/chatter1")).volume(0.9F)
        );
    }

    private void soundDefinition(Supplier<SoundEvent> soundEvent, String subtitle, SoundDefinition.Sound... sounds) {
        this.add(soundEvent.get(), SoundDefinition.definition().subtitle("subtitles.shifted_lens." + subtitle).with(sounds));
    }

    private void sound(Supplier<SoundEvent> soundEvent, SoundDefinition.Sound... sounds){
        this.soundDefinition(soundEvent, soundEvent.get().getLocation().getPath(), sounds);
    }
}
