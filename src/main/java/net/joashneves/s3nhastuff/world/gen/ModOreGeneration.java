package net.joashneves.s3nhastuff.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.joashneves.s3nhastuff.world.ModPlacedFeatures;
import net.minecraft.world.gen.GenerationStep;

/**
 * Classe responsável por ADICIONAR as placed features aos biomas do mundo.
 *
 * As configured/placed features são registadas no registry (pelo data generator),
 * mas isso por si só NÃO faz o minério spawnar. É preciso dizer ao jogo em quais
 * biomas a feature deve ser gerada e em que etapa da geração.
 *
 * BiomeModifications.addFeature() faz exatamente isso:
 * - BiomeSelectors.foundInOverworld(): aplica a todos os biomas do overworld
 *   (tanto nas cavernas subterrâneas como nas montanhas, planícies, etc.)
 * - GenerationStep.Feature.UNDERGROUND_ORES: a mesma etapa usada pelos minérios
 *   vanilla (ferro, ouro, diamante...) - isto é o que faz spawnar nas cavernas
 */
public class ModOreGeneration {

    /**
     * Regista a geração de minério de cobalt no mundo.
     * Chamado a partir de ModWorldGeneration.generateModWorldGen().
     */
    public static void generateOres() {
        // Adiciona a placed feature de cobalt a todos os biomas do overworld,
        // na etapa de geração de minérios subterrâneos (cavernas)
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.COBALT_ORE_PLACED_KEY);

        // Exemplo para biomas específicos (descomente se quiser limitar a alguns biomas):
        // BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FOREST, BiomeKeys.PLAINS),
        //         GenerationStep.Feature.UNDERGROUND_ORES,
        //         ModPlacedFeatures.COBALT_ORE_PLACED_KEY);
    }
}