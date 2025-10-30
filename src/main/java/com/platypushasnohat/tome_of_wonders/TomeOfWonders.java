package com.platypushasnohat.tome_of_wonders;

import com.platypushasnohat.tome_of_wonders.data.*;
import com.platypushasnohat.tome_of_wonders.events.*;
import com.platypushasnohat.tome_of_wonders.registry.*;
import com.platypushasnohat.tome_of_wonders.utils.ClientProxy;
import com.platypushasnohat.tome_of_wonders.utils.CommonProxy;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

@Mod(TomeOfWonders.MOD_ID)
public class TomeOfWonders {

    public static final String MOD_ID = "tome_of_wonders";
    public static final CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public TomeOfWonders() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext context = ModLoadingContext.get();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::dataSetup);

        context.registerConfig(ModConfig.Type.COMMON, TomeOfWondersConfig.COMMON_CONFIG);

        TOWItems.ITEMS.register(modEventBus);
        TOWBlocks.BLOCKS.register(modEventBus);
        TOWBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        TOWEntities.ENTITY_TYPES.register(modEventBus);
        TOWPotions.POTIONS.register(modEventBus);
        TOWSoundEvents.SOUND_EVENTS.register(modEventBus);
        TOWParticles.PARTICLE_TYPES.register(modEventBus);
        TOWPaintings.PAINTING_VARIANTS.register(modEventBus);
        TomeOfWondersTab.CREATIVE_TAB.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ForgeEvents());
    }

    public void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(TOWBrewingRecipes::registerPotionRecipes);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(PROXY::clientInit);
    }

    private void dataSetup(GatherDataEvent data) {
        DataGenerator generator = data.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = data.getLookupProvider();
        ExistingFileHelper helper = data.getExistingFileHelper();

        boolean server = data.includeServer();

        TOWDatapackProvider datapackEntries = new TOWDatapackProvider(output, provider);
        generator.addProvider(server, datapackEntries);
        provider = datapackEntries.getRegistryProvider();

        TOWBlockTagProvider blockTags = new TOWBlockTagProvider(output, provider, helper);
        generator.addProvider(server, blockTags);
        generator.addProvider(server, new TOWEntityTagProvider(output, provider, helper));
        generator.addProvider(server, new TOWBiomeTagProvider(output, provider, helper));
        generator.addProvider(server, new TOWDamageTypeTagProvider(output, provider, helper));
        generator.addProvider(server, new TOWRecipeProvider(output));
        generator.addProvider(server, new TOWPaintingTagProvider(output, provider, helper));
        generator.addProvider(server, TOWLootProvider.register(output));

        boolean client = data.includeClient();
        generator.addProvider(client, new TOWItemModelProvider(data));
        generator.addProvider(client, new TOWSoundDefinitionProvider(output, helper));
        generator.addProvider(client, new TOWLanguageProvider(data));
    }

    public static ResourceLocation modPrefix(String name) {
        return new ResourceLocation(MOD_ID, name.toLowerCase(Locale.ROOT));
    }
}

