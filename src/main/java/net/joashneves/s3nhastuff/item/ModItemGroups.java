package net.joashneves.s3nhastuff.item;

import net.joashneves.s3nhastuff.S3nhaStuffs;
import net.joashneves.s3nhastuff.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * Classe que define os grupos de itens (abas) no menu de criativo.
 *
 * Cada grupo aparece como uma aba separada no inventário de criativo:
 * - mod_s3nha_items: contém itens, ferramentas, armaduras e materiais
 * - mod_s3nha_blocks: contém blocos (minérios, blocos de armazenamento)
 *
 * Os itens/blocos são adicionados na ordem que aparecem no código.
 */
public class ModItemGroups {

    /**
     * Grupo de itens - segunda aba no criativo.
     * Contém: lingotes, raw, ferramentas e armaduras de cobalt.
     */
    public static final ItemGroup MOD_S3NHA_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(S3nhaStuffs.MOD_ID, "mod_s3nha_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.COBALT_INGOT)).displayName(
                    Text.translatable("itemGroup.s3nha-stuffs.mod_s3nha_items")).entries((displayContext, entries) -> {
                        // Materiais
                        entries.add(ModItems.COBALT_INGOT);
                        entries.add(ModItems.RAW_COBALT);
                        // Ferramentas
                        entries.add(ModItems.COBALT_AXE);
                        entries.add(ModItems.COBALT_HOE);
                        entries.add(ModItems.COBALT_PICKAXE);
                        entries.add(ModItems.COBALT_SHOVEL);
                        entries.add(ModItems.COBALT_SWORD);
                        // Armaduras
                        entries.add(ModItems.COBALT_HELMET);
                        entries.add(ModItems.COBALT_CHESTPLATE);
                        entries.add(ModItems.COBALT_LEGGINGS);
                        entries.add(ModItems.COBALT_BOOTS);
                    }).build());

    /**
     * Grupo de blocos - primeira aba no criativo.
     * Contém: minérios e blocos de armazenamento de cobalt.
     */
    public static final ItemGroup MOD_S3NHA_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(S3nhaStuffs.MOD_ID, "mod_s3nha_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.COBALT_ORE)).displayName(
                    Text.translatable("itemGroup.s3nha-stuffs.mod_s3nha_blocks")).entries((displayContext, entries) -> {
                // Minérios
                entries.add(ModBlocks.COBALT_ORE);
                entries.add(ModBlocks.COBALT_DEEPSLATE_ORE);
                // Blocos de armazenamento
                entries.add(ModBlocks.COBALT_BLOCK);
                entries.add(ModBlocks.RAW_COBALT_BLOCK);
            }).build());

    /**
     * Método chamado durante a inicialização do mod para registar os grupos.
     * Os grupos são registados quando as constantes estáticas são carregadas.
     */
    public static void registerItemGroups() {
        S3nhaStuffs.LOGGER.info("Registering ModItemGroups");
    }
}
