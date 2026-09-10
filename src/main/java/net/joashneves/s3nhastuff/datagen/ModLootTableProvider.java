package net.joashneves.s3nhastuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.joashneves.s3nhastuff.block.ModBlocks;
import net.joashneves.s3nhastuff.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * Classe de geração de loot tables (datagen).
 * Define o que cada bloco dropa quando é minerado/destruído.
 *
 * Tipos de drop:
 * - oreDrops(): dropa 1 item raw (minério normal de cobalt)
 * - multipleOreDrops(): dropa entre 2-6 items raw (minério deepslate, mais generoso)
 * - addDrop(block): dropa o próprio bloco (blocos de armazenamento como iron_block)
 *
 * As loot tables suportam Silk Touch (toque de seda) e Fortune (fortuna).
 */
public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        // ==================== MINÉRIOS ====================
        // Minério de cobalt na pedra: dropa 1 raw_cobalt (com fortune chance)
        addDrop(ModBlocks.COBALT_ORE, oreDrops(ModBlocks.COBALT_ORE, ModItems.RAW_COBALT));
        // Minério de cobalt no deepslate: dropa 2-6 raw_cobalt (mais generoso que na pedra)
        addDrop(ModBlocks.COBALT_DEEPSLATE_ORE, multipleOreDrops(ModBlocks.COBALT_DEEPSLATE_ORE, ModItems.RAW_COBALT, 2, 6));

        // ==================== BLOCOS DE ARMAZENAMENTO ====================
        // Bloco de lingots: dropa o próprio bloco (igual iron_block, gold_block)
        addDrop(ModBlocks.COBALT_BLOCK);
        // Bloco raw: dropa o próprio bloco (igual raw_iron_block)
        addDrop(ModBlocks.RAW_COBALT_BLOCK);
    }

    /**
     * Cria uma loot table que dropa uma quantidade variável de itens.
     * Suporta Silk Touch (dropa o bloco inteiro) e Fortune (aumenta quantidade).
     *
     * @param drop     O bloco que está a ser dropado (para verificação de Silk Touch)
     * @param item     O item que é dropado
     * @param minDrops Quantidade mínima de drops (ex: 2)
     * @param maxDrops Quantidade máxima de drops (ex: 6)
     * @return LootTable.Builder configurado com drops variáveis
     */
    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        // dropsWithSilkTouch: se tiver Silk Touch, dropa o bloco; senão, dropa o item
        // applyExplosionDecay: reseta os drops se o bloco for explodido
        // SetCountLootFunction: define quantidade aleatória entre minDrops e maxDrops
        // ApplyBonusLootFunction.oreDrops: aplica bônus de Fortune (encantamento)
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

}