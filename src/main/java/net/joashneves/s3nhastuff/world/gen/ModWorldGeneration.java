package net.joashneves.s3nhastuff.world.gen;

/**
 * Classe de ativação da geração de mundo do mod.
 *
 * Serve como ponto de entrada único para toda a geração de mundo do mod.
 * Chamada a partir de S3nhaStuffs.onInitialize().
 *
 * Se no futuro adicionar outras gerações de mundo (minérios novos, vegetação,
 * estruturas, etc.), basta acrescentar mais chamadas aqui num único lugar.
 */
public class ModWorldGeneration {

    /**
     * Ativa toda a geração de mundo do mod.
     * No momento apenas gera os minérios de cobalt nas cavernas.
     */
    public static void generateModWorldGen() {
        ModOreGeneration.generateOres();
    }
}