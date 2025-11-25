package com.platypushasnohat.tome_of_wonders.registry;

import com.platypushasnohat.tome_of_wonders.TomeOfWonders;
import com.platypushasnohat.tome_of_wonders.blocks.blockentity.WhirliboxBlockEntity;
import com.platypushasnohat.tome_of_wonders.blocks.blockentity.WhirligigBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TOWBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TomeOfWonders.MOD_ID);

    public static final RegistryObject<BlockEntityType<WhirligigBlockEntity>> WHIRLIGIG_BLOCK_ENTITY = BLOCK_ENTITIES.register("whirligig", () -> BlockEntityType.Builder.of(WhirligigBlockEntity::new, TOWBlocks.WHIRLIGIG.get()).build(null));

    public static final RegistryObject<BlockEntityType<WhirliboxBlockEntity>> WHIRLIBOX_BLOCK_ENTITY = BLOCK_ENTITIES.register("whirlibox", () -> BlockEntityType.Builder.of(WhirliboxBlockEntity::new, TOWBlocks.WHIRLIBOX.get()).build(null));
}
