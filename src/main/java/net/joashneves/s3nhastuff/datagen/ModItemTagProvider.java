package net.joashneves.s3nhastuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.joashneves.s3nhastuff.item.ModItems;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        getOrCreateTagBuilder(ItemTags.SWORDS).add(ModItems.COBALT_SWORD);
        getOrCreateTagBuilder(ItemTags.PICKAXES).add(ModItems.COBALT_PICKAXE);
        getOrCreateTagBuilder(ItemTags.AXES).add(ModItems.COBALT_AXE);
        getOrCreateTagBuilder(ItemTags.SHOVELS).add(ModItems.COBALT_SHOVEL);
        getOrCreateTagBuilder(ItemTags.HOES).add(ModItems.COBALT_HOE);

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR).add(ModItems.COBALT_HELMET);
        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR).add(ModItems.COBALT_CHESTPLATE);
        getOrCreateTagBuilder(ItemTags.LEG_ARMOR).add(ModItems.COBALT_LEGGINGS);
        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR).add(ModItems.COBALT_BOOTS);

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(
                ModItems.COBALT_HELMET,
                ModItems.COBALT_CHESTPLATE,
                ModItems.COBALT_LEGGINGS,
                ModItems.COBALT_BOOTS);
    }

}