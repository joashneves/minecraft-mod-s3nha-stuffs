package net.joashneves.s3nhastuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.joashneves.s3nhastuff.block.ModBlocks;
import net.joashneves.s3nhastuff.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Classe de geração de receitas (datagen).
 * Em vez de escrever manualmente dezenas de arquivos .json para cada receita,
 * escrevemos este código Java e, ao rodar o comando do Gradle (runData),
 * o Fabric gera todos os arquivos JSON automaticamente em src/main/generated/.
 *
 * Receitas incluem:
 * - Derretimento (smelting/blasting) de matérias-primas em lingots
 * - Crafting de ferramentas e armaduras
 * - Crafting de blocos de armazenamento (9 lingots = 1 bloco, 1 bloco = 9 lingots)
 * - Crafting de blocos raw (9 raw = 1 bloco, 1 bloco = 9 raw)
 */
public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        // ==================== DERRETIMENTO (SMELTING/BLASTING) ====================
        // Lista de itens que podem ser derretidos para obter lingote de cobalt
        // raw_cobalt e cobalt_ore ambos dão 1 lingote ao serem derretidos
        List<ItemConvertible> COBALT_SMELTABLES = List.of(ModItems.RAW_COBALT, ModBlocks.COBALT_ORE);
        // Smelting: usa forno normal, 200 ticks (10 segundos), 0.25 XP
        offerSmelting(exporter, COBALT_SMELTABLES, RecipeCategory.MISC, ModItems.COBALT_INGOT, 0.25f, 200, "cobalt");
        // Blasting: usa forno de lascador, metade do tempo (100 ticks = 5 segundos)
        offerBlasting(exporter, COBALT_SMELTABLES, RecipeCategory.MISC, ModItems.COBALT_INGOT, 0.25f, 200, "cobalt");
        // Derretimento do bloco raw de cobalt: 1 bloco raw = 9 lingots
        // (igual ao raw_iron_block que dá 9 iron_ingots)
        // NOTA: o offerSmelting não suporta count, então criamos os JSONs manualmente
        // em src/main/resources/data/s3nha-stuffs/recipe/ com "count": 9
        // ==================== BLOCOS DE ARMAZENAMENTO (9→1 e 1→9) ====================
        // Recepção de bloco de lingots: 9 lingots = 1 bloco (como ouro/ferro)
        offerCobaltBlockRecipe(exporter);
        // Reversão: 1 bloco = 9 lingots (shapeless, sem formato específico)
        offerCobaltBlockReverseRecipe(exporter);
        // Recepção de bloco raw: 9 raw_cobalt = 1 raw_cobalt_block
        offerRawCobaltBlockRecipe(exporter);
        // Reversão: 1 raw_cobalt_block = 9 raw_cobalt
        offerRawCobaltBlockReverseRecipe(exporter);
        // ==================== FERRAMENTAS ====================
        offerCobaltSwordRecipe(exporter);
        offerCobaltPickaxeRecipe(exporter);
        offerCobaltAxeRecipe(exporter);
        offerCobaltShovelRecipe(exporter);
        offerCobaltHoeRecipe(exporter);
        // ==================== ARMADURAS ====================
        offerCobaltHelmetRecipe(exporter);
        offerCobaltChestplateRecipe(exporter);
        offerCobaltLeggingsRecipe(exporter);
        offerCobaltBootsRecipe(exporter);
        // ==================== BEBIDAS ====================
        offerMugCocoaRecipe(exporter);
        offerMugrecipe(exporter);
    }

    // ==================== RECEITAS DE BLOCOS DE ARMAZENAMENTO ====================

    /**
     * Receita shaped: 9 lingots de cobalt = 1 bloco de cobalt.
     * Formato: preencher todo o 3x3 da crafting table.
     * Igual à receita de bloco de ouro/ferro no Minecraft vanilla.
     */
    private void offerCobaltBlockRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBALT_BLOCK)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.COBALT_INGOT)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter);
    }

    /**
     * Receita shapeless: 1 bloco de cobalt = 9 lingots de cobalt.
     * Shapeless significa que a posição dos itens não importa.
     * Permite "descompactar" o bloco de volta em lingots.
     */
    private void offerCobaltBlockReverseRecipe(RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_INGOT, 9)
                .input(ModBlocks.COBALT_BLOCK)
                .criterion(hasItem(ModBlocks.COBALT_BLOCK), conditionsFromItem(ModBlocks.COBALT_BLOCK))
                .offerTo(exporter);
    }

    /**
     * Receita shaped: 9 raw_cobalt = 1 raw_cobalt_block.
     * Formato: preencher todo o 3x3 da crafting table.
     * Igual à receita de raw_iron_block no vanilla.
     */
    private void offerRawCobaltBlockRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_COBALT_BLOCK)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.RAW_COBALT)
                .criterion(hasItem(ModItems.RAW_COBALT), conditionsFromItem(ModItems.RAW_COBALT))
                .offerTo(exporter);
    }

    /**
     * Receita shapeless: 1 raw_cobalt_block = 9 raw_cobalt.
     * Permite "descompactar" o bloco raw de volta em matérias-primas.
     * Alternativa ao derretimento: pode craftar em vez de usar forno.
     */
    private void offerRawCobaltBlockReverseRecipe(RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_COBALT, 9)
                .input(ModBlocks.RAW_COBALT_BLOCK)
                .criterion(hasItem(ModBlocks.RAW_COBALT_BLOCK), conditionsFromItem(ModBlocks.RAW_COBALT_BLOCK))
                .offerTo(exporter);
    }

    // ==================== RECEITAS DE FERRAMENTAS ====================

    /**
     * Espada de cobalt: 2 lingots + 1 pau.
     * Categoria COMBAT para aparecer na aba de combate do livro de receitas.
     */
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

    /**
     * Picareta de cobalt: 3 lingots + 2 paus.
     * Categoria TOOLS para aparecer na aba de ferramentas.
     */
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

    /**
     * Machado de cobalt: 2 lingots + 2 paus (formato em L).
     */
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

    /**
     * Pá de cobalt: 1 lingote + 2 paus (coluna vertical).
     */
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

    /**
     * Enxada de cobalt: 2 lingots + 2 paus (formato em L espelhado).
     */
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

    // ==================== RECEITAS DE ARMADURAS ====================

    /**
     * Capacete de cobalt: 5 lingots (3 em cima + 2 nos lados).
     */
    private void offerCobaltHelmetRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COBALT_HELMET)
                .pattern("RRR")
                .pattern("R R")
                .input('R', ModItems.COBALT_INGOT)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter);
    }

    /**
     * Peitoral de cobalt: 8 lingots (formato de camiseta sem alças).
     */
    private void offerCobaltChestplateRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COBALT_CHESTPLATE)
                .pattern("R R")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.COBALT_INGOT)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter);
    }

    /**
     * Calças de cobalt: 7 lingots (formato de calça com buraco nas pernas).
     */
    private void offerCobaltLeggingsRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COBALT_LEGGINGS)
                .pattern("RRR")
                .pattern("R R")
                .pattern("R R")
                .input('R', ModItems.COBALT_INGOT)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter);
    }

    /**
     * Botas de cobalt: 4 lingots (2 pares de L).
     */
    private void offerCobaltBootsRecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COBALT_BOOTS)
                .pattern("R R")
                .pattern("R R")
                .input('R', ModItems.COBALT_INGOT)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(exporter);
    }

    // ==================== RECEITAS DE BEBIDAS ====================

    /**
     * Caneca de cacau: caneca vazia + coco.
     * Receita shapeless (a posição dos itens não importa).
     */
    private void offerMugCocoaRecipe(RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MUG_COCOA)
                .input(ModItems.MUG)
                .input(Items.COCOA_BEANS)
                .criterion(hasItem(ModItems.MUG), conditionsFromItem(ModItems.MUG))
                .offerTo(exporter);
    }
    private void offerMugrecipe(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModItems.MUG)
                .pattern("R R")
                .pattern(" R ")
                .input('R', Items.QUARTZ_BLOCK)
                .criterion(hasItem(Items.QUARTZ_BLOCK), conditionsFromItem(Items.QUARTZ_BLOCK))
                .offerTo(exporter);
    }

}