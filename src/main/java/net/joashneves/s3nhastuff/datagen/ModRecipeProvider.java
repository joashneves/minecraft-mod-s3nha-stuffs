package net.joashneves.s3nhastuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
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


    }

}