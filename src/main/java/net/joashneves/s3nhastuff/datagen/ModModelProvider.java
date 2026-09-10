package net.joashneves.s3nhastuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.joashneves.s3nhastuff.block.ModBlocks;
import net.joashneves.s3nhastuff.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool CobaltPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.COBALT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COBALT_DEEPSLATE_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.COBALT_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_COBALT, Models.GENERATED);
        // Handheld para mão
        itemModelGenerator.register(ModItems.COBALT_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_PICKAXE, Models.HANDHELD);
        //itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.COBALT_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.COBALT_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.COBALT_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.COBALT_BOOTS);
    }
}