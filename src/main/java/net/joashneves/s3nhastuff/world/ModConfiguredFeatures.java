package net.joashneves.s3nhastuff.world;

import net.joashneves.s3nhastuff.S3nhaStuffs;
import net.joashneves.s3nhastuff.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

/**
 * Classe que define as CONFIGURED FEATURES (features configuradas) do mod.
 *
 * A configured feature define O QUE é gerado no mundo:
 * - Que tipo de feature é (Feature.ORE = minério)
 * - Quais blocos substituir (stone, deepslate)
 * - Que bloco colocar no lugar (cobalt_ore, cobalt_deepslate_ore)
 * - O tamanho da veio de minério
 *
 * A bootstrap() é chamada pelo data generator (S3nhaStuffsDataGenerator.buildRegistry)
 * que regista estas features no registry dinâmico do jogo.
 *
 * NOTA IMPORTANTE: aqui usamos o Cobalt_ore e o cobalt_deepslate_ore (NÃO o
 * raw_cobalt_block). A configuração de targets faz com que:
 * - pedra (stone)      -> vira cobalt_ore
 * - deepslate          -> vira cobalt_deepslate_ore
 * Isto é exatamente como o vanilla faz com ferro/ouro (que têm variante em deepslate).
 */
public class ModConfiguredFeatures {

    /**
     * Chave de registo da configured feature de cobalt.
     * Identifica a feature no registry: "s3nha-stuffs:cobalt_ore".
     */
    public static final RegistryKey<ConfiguredFeature<?, ?>> COBALT_ORE_KEY = registerKey("cobalt_ore");

    /**
     * Método bootstrap chamado pelo data generator.
     * Regista a configured feature de cobalt no registry CONFIGURED_FEATURE.
     *
     * @param context O contexto de registo (fornece o registry onde registar)
     */
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        // RuleTest: define QUAIS blocos serão substituíveis pelo minério
        // BlockMatchRuleTest = combina blocos específicos
        RuleTest stoneReplacables = new BlockMatchRuleTest(Blocks.STONE);      // substitui pedra
        RuleTest deepslateReplacables = new BlockMatchRuleTest(Blocks.DEEPSLATE); // substitui deepslate

        // Lista de targets do minério:
        // - na pedra    -> coloca COBALT_ORE (minério normal)
        // - no deepslate-> coloca COBALT_DEEPSLATE_ORE (minério escuro)
        List<OreFeatureConfig.Target> overworldCobaltOres = List.of(
                OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.COBALT_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.COBALT_DEEPSLATE_ORE.getDefaultState())
        );

        // Regista a feature "cobalt_ore" com configuração de minério:
        // - targets: os blocos que podem ser substituídos (stone e deepslate)
        // - size = 5: tamanho médio da veio (diamante=1, ferro=9; cobalt fica no meio)
        register(context, COBALT_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldCobaltOres, 5));
    }

    /**
     * Cria uma RegistryKey para a configured feature.
     *
     * @param name O nome da feature (ex: "cobalt_ore")
     * @return Chave completa com namespace do mod: "s3nha-stuffs:<name>"
     */
    private static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(S3nhaStuffs.MOD_ID, name));
    }

    /**
     * Regista a configured feature no registry.
     *
     * @param context Contexto de registo
     * @param key     A chave que identifica a feature
     * @param feature O tipo de feature (Feature.ORE para minério)
     * @param config  A configuração da feature (OreFeatureConfig para minério)
     */
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
            Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key,
            F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}