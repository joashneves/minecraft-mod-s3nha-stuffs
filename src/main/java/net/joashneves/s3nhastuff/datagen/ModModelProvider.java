package net.joashneves.s3nhastuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.joashneves.s3nhastuff.block.ModBlocks;
import net.joashneves.s3nhastuff.item.ModItems;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;

/**
 * Classe de geração de modelos (datagen).
 * Gera automaticamente os arquivos JSON de blockstates, modelos de blocos e modelos de itens.
 *
 * - Blockstate: define como o bloco é renderizado (rotação, modelo, etc.)
 * - Modelo do bloco: define a geometria e texturas do bloco no mundo
 * - Modelo do item: define como o item aparece no inventário e na mão do jogador
 */
public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    /**
     * Gera os modelos e blockstates dos BLOCOS.
     *
     * - registerCubeAllModelTexturePool: cria um bloco com textura igual em todas as faces
     *   (para minérios que têm textura própria, tipo stone com vein)
     * - registerSimpleCubeAll: cria um bloco simples com textura em todas as faces
     *   (para blocos uniformes como deepslate ore)
     */
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // Minério de cobalt na pedra - textura em todas as faces
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.COBALT_ORE);
        // Minério de cobalt no deepslate - textura em todas as faces
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COBALT_DEEPSLATE_ORE);
        // Bloco de cobalt (storage block) - textura uniforme em todas as faces
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COBALT_BLOCK);
        // Bloco de raw cobalt (storage block raw) - textura uniforme em todas as faces
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_COBALT_BLOCK);
    }

    /**
     * Gera os modelos dos ITENS.
     *
     * - Models.GENERATED: modelo de item "gerado" (plano, como lingote/ingot)
     * - Models.HANDHELD: modelo de item "segurado" (ferramentas, com rotação na mão)
     * - registerArmor: modelo de armadura (renderiza no boneco do jogador)
     *
     * Os blocos NÃO precisam ser registados aqui porque o datagen gera
     * automaticamente os modelos de BlockItem a partir dos modelos de bloco.
     */
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // Itens de material (lingote e raw) - modelo plano gerado
        itemModelGenerator.register(ModItems.COBALT_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_COBALT, Models.GENERATED);

        // Ferramentas - modelo handheld (aparece na mão do jogador com rotação correta)
        itemModelGenerator.register(ModItems.COBALT_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_PICKAXE, Models.HANDHELD);

        // Armaduras - modelos de armadura que são renderizados no boneco do jogador
        itemModelGenerator.registerArmor((ArmorItem) ModItems.COBALT_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.COBALT_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.COBALT_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.COBALT_BOOTS);
    }
}