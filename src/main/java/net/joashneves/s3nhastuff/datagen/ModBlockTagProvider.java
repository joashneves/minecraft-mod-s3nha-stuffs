package net.joashneves.s3nhastuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.joashneves.s3nhastuff.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

/**
 * Classe de geração de tags de blocos (datagen).
 * Tags definem propriedades compartilhadas entre múltiplos blocos.
 *
 * Tags principais:
 * - PICKAXE_MINEABLE: blocos que só podem ser minerados com picareta
 * - NEEDS_IRON_TOOL: blocos que precisam de pelo menos picareta de ferro para dropar items
 * - NEEDS_STONE_TOOL: blocos que precisam de picareta de pedra para dropar
 *
 * Sem estas tags, os blocos não dropam nada ao serem minerados com ferramentas incorretas.
 */
public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        // ==================== PICKAXE_MINEABLE ====================
        // Todos os blocos de cobalt precisam de picareta para serem minerados
        // (sem picareta, o bloco é destruído mas não dropa nada)
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.COBALT_ORE)           // Minério de cobalt na pedra
                .add(ModBlocks.COBALT_DEEPSLATE_ORE)  // Minério de cobalt no deepslate
                .add(ModBlocks.COBALT_BLOCK)           // Bloco de lingots de cobalt
                .add(ModBlocks.RAW_COBALT_BLOCK);      // Bloco de raw cobalt

        // ==================== NEEDS_IRON_TOOL ====================
        // Minérios de cobalt precisam de picareta de ferro ou superior para dropar
        // (minérios usam NEEDS_IRON_TOOL como os minérios de diamante)
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.COBALT_ORE)
                .add(ModBlocks.COBALT_DEEPSLATE_ORE);

        // ==================== NEEDS_STONE_TOOL ====================
        // Blocos de armazenamento precisam de picareta de pedra para dropar
        // (como vanilla: iron_block e raw_iron_block usam stone tool)
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.COBALT_BLOCK)
                .add(ModBlocks.RAW_COBALT_BLOCK);
    }
}