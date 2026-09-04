package net.joashneves.s3nhastuff.item;

import net.joashneves.s3nhastuff.S3nhaStuffs;
import net.joashneves.s3nhastuff.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup MOD_S3NHA_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(S3nhaStuffs.MOD_ID, "mod_s3nha_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.COBALT_INGOT)).displayName(
                    Text.translatable("itemGroup.s3nha-stuffs.mod_s3nha_items")).entries((displayContext, entries) ->{
                        entries.add(ModItems.COBALT_INGOT);
                        entries.add(ModItems.RAW_COBALT);
                        entries.add(ModItems.COBALT_AXE);
                        entries.add(ModItems.COBALT_HOE);
                        entries.add(ModItems.COBALT_PICKAXE);
                        entries.add(ModItems.COBALT_SHOVEL);
                        entries.add(ModItems.COBALT_SWORD);
                    }).build());

    public static final ItemGroup MOD_S3NHA_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(S3nhaStuffs.MOD_ID, "mod_s3nha_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.COBALT_ORE)).displayName(
                    Text.translatable("itemGroup.s3nha-stuffs.mod_s3nha_blocks")).entries((displayContext, entries) ->{
                entries.add(ModBlocks.COBALT_ORE);
                entries.add(ModBlocks.COBALT_DEEPSLATE_ORE);
            }).build());

    public static void registerItemGroups() {
        S3nhaStuffs.LOGGER.info("Registering ModItemGroups");


    }
}
