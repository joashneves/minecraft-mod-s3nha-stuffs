package net.joashneves.s3nhastuff;

import net.fabricmc.api.ModInitializer;

import net.joashneves.s3nhastuff.block.ModBlocks;
import net.joashneves.s3nhastuff.item.ModItemGroups;
import net.joashneves.s3nhastuff.item.ModItems;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class S3nhaStuffs implements ModInitializer {
	public static final String MOD_ID = "s3nha-stuffs";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerItems();
		ModBlocks.registerBlocks();

		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
