package net.joashneves.s3nhastuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.loader.impl.discovery.ModCandidateImpl;
import net.joashneves.s3nhastuff.block.ModBlocks;
import net.joashneves.s3nhastuff.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

// Em vez de você escrever manualmente dezenas de arquivos .json para cada receita, você escreve esse código Java e, ao rodar o comando do Gradle, o Fabric gera todos os arquivos JSON automaticamente para você.
public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        // Cria lista de itens que podem ser assados
        List<ItemConvertible> COBALT_SMELTABLES = List.of(ModItems.RAW_COBALT, ModBlocks.COBALT_ORE);

        offerSmelting(exporter, COBALT_SMELTABLES, RecipeCategory.MISC, ModItems.COBALT_INGOT, 0.25f, 200, "cobalt");
        offerBlasting(exporter, COBALT_SMELTABLES, RecipeCategory.MISC, ModItems.COBALT_INGOT, 0.25f, 200, "cobalt");

        offerCobaltSwordRecipe(exporter);
        offerCobaltPickaxeRecipe(exporter);
        offerCobaltAxeRecipe(exporter);
        offerCobaltShovelRecipe(exporter);
        offerCobaltHoeRecipe(exporter);

        offerCobaltHelmetRecipe(exporter);
        offerCobaltChestplateRecipe(exporter);
        offerCobaltLeggingsRecipe(exporter);
        offerCobaltBootsRecipe(exporter);
    }

    private void offerCobaltSwordRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COBALT_SWORD)
                .pattern(" R ")
                .pattern(" R ")
                .pattern(" S ")
                .input('R', ModItems.COBALT_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter);
    }

    private void offerCobaltPickaxeRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COBALT_PICKAXE)
                .pattern("RRR")
                .pattern(" S ")
                .pattern(" S ")
                .input('R', ModItems.COBALT_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter);
    }

    private void offerCobaltAxeRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COBALT_AXE)
                .pattern("RR ")
                .pattern("RS ")
                .pattern(" S ")
                .input('R', ModItems.COBALT_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter);
    }

    private void offerCobaltShovelRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COBALT_SHOVEL)
                .pattern("R")
                .pattern("S")
                .pattern("S")
                .input('R', ModItems.COBALT_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter);
    }

    private void offerCobaltHoeRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.COBALT_HOE)
                .pattern("RR ")
                .pattern(" S ")
                .pattern(" S ")
                .input('R', ModItems.COBALT_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter);
    }

    private void offerCobaltHelmetRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COBALT_HELMET)
                .pattern("RRR")
                .pattern("R R")
                .input('R', ModItems.COBALT_INGOT)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter);
    }

    private void offerCobaltChestplateRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COBALT_CHESTPLATE)
                .pattern("R R")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.COBALT_INGOT)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter);
    }

    private void offerCobaltLeggingsRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COBALT_LEGGINGS)
                .pattern("RRR")
                .pattern("R R")
                .pattern("R R")
                .input('R', ModItems.COBALT_INGOT)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter);
    }

    private void offerCobaltBootsRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COBALT_BOOTS)
                .pattern("R R")
                .pattern("R R")
                .input('R', ModItems.COBALT_INGOT)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter);
    }

}