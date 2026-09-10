package net.joashneves.s3nhastuff;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.joashneves.s3nhastuff.datagen.*;
import net.joashneves.s3nhastuff.world.ModConfiguredFeatures;
import net.joashneves.s3nhastuff.world.ModPlacedFeatures;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class S3nhaStuffsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRegistryDataGenerator::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModLootTableProvider::new);

	}

	/**
	 * Regista as configured/placed features do mod nos registries dinâmicos.
	 * Sem isto, o jogo não conhece as features "s3nha-stuffs:cobalt_ore" e a
	 * geração de mundo não funciona.
	 *
	 * @param registryBuilder O construtor de registries dinâmicos
	 */
	public void buildRegistry(RegistryBuilder registryBuilder) {
		// Regista a configured feature "cobalt_ore" (o que é gerado)
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);

		// Regista a placed feature "cobalt_ore" (onde/quão frequentemente é gerado)
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}
