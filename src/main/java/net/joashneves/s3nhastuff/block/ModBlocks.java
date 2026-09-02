package net.joashneves.s3nhastuff.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.joashneves.s3nhastuff.S3nhaStuffs;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    public static final Block COBALT_ORE = registerBlock("cobalt_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2,5),
                    AbstractBlock.Settings.create().strength(3f).requiresTool().sounds(BlockSoundGroup.STONE)));

    public static final Block COBALT_DEEPSLATE_ORE = registerBlock("cobalt_deepslate_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(4,8),
                    AbstractBlock.Settings.create().strength(3f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(S3nhaStuffs.MOD_ID, name), block);
    }
    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(S3nhaStuffs.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(S3nhaStuffs.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }

    public static void registerBlocks(){
        S3nhaStuffs.LOGGER.info("Registering modblock for : " + S3nhaStuffs.MOD_ID);
//
//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
//            entries.add()
//        });
    }

}
