package net.joashneves.s3nhastuff.block;

import net.joashneves.s3nhastuff.S3nhaStuffs;
import net.joashneves.s3nhastuff.block.custom.MugBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

/**
 * Classe responsável por registrar todos os blocos do mod.
 * Cada bloco é registrado tanto no registry de blocos quanto no registry de itens (como BlockItem).
 * Os blocos são acessados por outras classes através das constantes públicas (ex: ModBlocks.COBALT_ORE).
 */
public class ModBlocks {

    // ==================== BLOCOS DE MINÉRIO ====================

    /**
     * Bloco de minério de cobalt - spawning normal no mundo.
     * Dropa raw_cobalt ao ser minerado (definido no loot table).
     * Dá 2-5 de XP ao minerar (como o minério de ouro/diamante).
     * Resiste 3f (mesmo que pedra), requer ferramenta para dropar.
     */
    public static final Block COBALT_ORE = registerBlock("cobalt_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5),
                    AbstractBlock.Settings.create().strength(3f).requiresTool().sounds(BlockSoundGroup.STONE)));

    /**
     * Bloco de minério de cobalt no deepslate - spawning nas camadas profundas.
     * Dropa mais raw_cobalt que o minério normal (2-6, definido no loot table).
     * Dá 4-8 de XP ao minerar (mais que o minério de pedra).
     */
    public static final Block COBALT_DEEPSLATE_ORE = registerBlock("cobalt_deepslate_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(4, 8),
                    AbstractBlock.Settings.create().strength(3f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE)));

    // ==================== BLOCOS DE ARMAZENAMENTO ====================

    /**
     * Bloco de cobalt compactado (storage block) - feito com 9 lingots de cobalt na crafting.
     * Igual ao bloco de ouro/ferro: 9 lingots = 1 bloco, 1 bloco = 9 lingots.
     * Resiste 5f (mais resistente que minério), som metálico.
     */
    public static final Block COBALT_BLOCK = registerBlock("cobalt_block",
            new Block(AbstractBlock.Settings.create().strength(5f).requiresTool().sounds(BlockSoundGroup.METAL)));

    /**
     * Bloco de cobalt bruto (raw storage block) - feito com 9 raw_cobalt na crafting.
     * Igual ao bloco de raw_iron/raw_gold: 9 raw = 1 bloco, 1 bloco = 9 raw.
     * Pode ser derretido para obter 9 lingots de cobalt.
     * Resiste 5f, som metálico.
     */
    public static final Block RAW_COBALT_BLOCK = registerBlock("raw_cobalt_block",
            new Block(AbstractBlock.Settings.create().strength(5f).requiresTool().sounds(BlockSoundGroup.METAL)));

    // ==================== BLOCOS DE DECORAÇÃO ====================

    /**
     * Caneca (mug) - bloco colocado no mundo quando o jogador clica com o botão direito
     * segurando o item mug. Usa modelo personalizado (Blockbench) e gira para encarar
     * o jogador ao ser colocada (comportamento parecido com cabeça de mob).
     * O BlockItem é registrado manualmente em ModItems (item "mug").
     * Quebra facilmente com a mão.
     */
    public static final Block MUG_BLOCK = registerBlockWithoutBlockItem("mug",
            new MugBlock(AbstractBlock.Settings.create().strength(0.5f).sounds(BlockSoundGroup.STONE)));

    // ==================== MÉTODOS DE REGISTRO ====================

    /**
     * Registra um bloco E o seu BlockItem correspondente.
     * O BlockItem permite que o bloco seja segurado no inventário e colocado no mundo.
     *
     * @param name  ID do bloco (ex: "cobalt_ore")
     * @param block Instância do bloco a registrar
     * @return O bloco registrado (para poder atribuir à constante)
     */
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(S3nhaStuffs.MOD_ID, name), block);
    }

    /**
     * Registra apenas o bloco, SEM criar um BlockItem.
     * Usado para o bloco mug, cujo BlockItem é registrado manualmente em ModItems
     * (para permitir configurar a caneca como item personalizado).
     */
    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(S3nhaStuffs.MOD_ID, name), block);
    }

    /**
     * Cria e regista o BlockItem correspondente a um bloco.
     * O BlockItem é o que aparece no inventário do jogador e permite colocar o bloco.
     *
     * @param name  ID do item (deve ser igual ao ID do bloco)
     * @param block O bloco ao qual o BlockItem pertence
     */
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(S3nhaStuffs.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    /**
     * Método chamado durante a inicialização do mod para registar os blocos.
     * Na verdade, os blocos já são registados quando as constantes estáticas são carregadas.
     * Este método serve apenas para garantir que a classe é carregada e para logar uma mensagem.
     */
    public static void registerBlocks() {
        S3nhaStuffs.LOGGER.info("Registering modblock for : " + S3nhaStuffs.MOD_ID);
    }

}
