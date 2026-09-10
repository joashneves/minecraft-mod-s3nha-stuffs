package net.joashneves.s3nhastuff.world;

import net.joashneves.s3nhastuff.S3nhaStuffs;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

/**
 * Classe que define as PLACED FEATURES (features colocadas) do mod.
 *
 * A placed feature define ONDE e COM QUE FREQUÊNCIA uma configured feature é gerada:
 * - Qual configured feature usar (referencia esta)
 * - Quantas veios por chunk (CountPlacementModifier)
 * - A que altura y gerar (HeightRangePlacementModifier)
 * - Espalhamento aleatório no XZ (SquarePlacementModifier)
 * - Só em biomas válidos (BiomePlacementModifier)
 *
 * A bootstrap() é chamada pelo data generator (S3nhaStuffsDataGenerator.buildRegistry)
 * que regista estas features no registry dinâmico PLACED_FEATURE.
 *
 * COMPARAÇÃO COM VANILLA (raridade):
 * - Diamante: veio 1, ~1 por chunk, y=-64 a 16 (muito raro)
 * - COBALT:   veio 5, 6 por chunk,  y=-64 a 64 (mais comum que diamante, menos que ferro)
 * - Ferro:    veio 9, ~20 por chunk, y=-64 a 320 (comum)
 */
public class ModPlacedFeatures {

    /**
     * Chave de registo da placed feature de cobalt.
     * Identifica a feature no registry: "s3nha-stuffs:cobalt_ore".
     * Esta chave é usada por ModOreGeneration.generateOres() para adicionar a
     * feature aos biomas.
     */
    public static final RegistryKey<PlacedFeature> COBALT_ORE_PLACED_KEY = registerKey("cobalt_ore");

    /**
     * Método bootstrap chamado pelo data generator.
     * Regista a placed feature de cobalt no registry PLACED_FEATURE.
     *
     * @param context O contexto de registo
     */
    public static void bootstrap(Registerable<PlacedFeature> context) {
        // Obtém o lookup do registry das configured features para poder referenciar
        // a configured feature "cobalt_ore" criada em ModConfiguredFeatures
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        // Regista a placed feature "cobalt_ore":
        // - Referencia a configured feature COBALT_ORE_KEY
        // - modifiersWithCount(6, altura): 6 veios por chunk, entre y=-64 e y=64
        register(context, COBALT_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.COBALT_ORE_KEY),
                ModOreGeneration.modifiersWithCount(6,
                        HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
    }

    /**
     * Cria uma RegistryKey para a placed feature.
     *
     * @param name O nome da feature (ex: "cobalt_ore")
     * @return Chave completa com namespace do mod: "s3nha-stuffs:<name>"
     */
    private static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(S3nhaStuffs.MOD_ID, name));
    }

    /**
     * Regista a placed feature no registry.
     *
     * @param context        Contexto de registo
     * @param key            A chave que identifica a feature
     * @param configuration  A configured feature a usar (via RegistryEntry)
     * @param modifiers      Lista de modificadores de colocação (frequência, altura, etc.)
     */
    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                 RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    /**
     * Overload do register que aceita os modifiers como varargs.
     *
     * @param context        Contexto de registo
     * @param key            A chave que identifica a feature
     * @param configuration  A configured feature a usar
     * @param modifiers      Os modificadores de colocação (um ou mais)
     */
    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                 RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}