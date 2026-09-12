package net.joashneves.s3nhastuff;

import net.fabricmc.api.ModInitializer;

import net.joashneves.s3nhastuff.block.ModBlocks;
import net.joashneves.s3nhastuff.block.entity.ModBlockEntities;
import net.joashneves.s3nhastuff.item.ModItemGroups;
import net.joashneves.s3nhastuff.item.ModItems;
import net.joashneves.s3nhastuff.world.gen.ModWorldGeneration;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe principal do mod s3nha-stuffs.
 * Esta é a entry point do mod - o Fabric chama onInitialize() quando o jogo arranca.
 *
 * A ordem de inicialização é importante:
 * 1. Item Groups (abas do criativo) - para que os itens possam ser adicionados
 * 2. Items (itens, ferramentas, armaduras) - para que existam antes dos blocos os referenciarem
 * 3. Blocks (blocos) - dependem dos itens para registar BlockItems
 * 4. World Gen (geração do mundo) - adiciona os minérios à geração do mundo
 */
public class S3nhaStuffs implements ModInitializer {
	public static final String MOD_ID = "s3nha-stuffs";

	// Logger para escrever mensagens no console e ficheiro de log
	// Usar o mod_id como nome facilita identificar de que mod vem cada mensagem
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Registar grupos de itens (abas do criativo) primeiro
		ModItemGroups.registerItemGroups();
		// Registar itens (lingotes, raw, ferramentas, armaduras)
		ModItems.registerItems();
		// Registar blocos (minérios, blocos de armazenamento)
		ModBlocks.registerBlocks();
		// Registar block entities (renderização da caneca estilo cabeça de mob)
		ModBlockEntities.registerBlockEntities();

		// Ativa a geração de minérios de cobalt no mundo (cavernas)
		ModWorldGeneration.generateModWorldGen();

		LOGGER.info("Hello Fabric world!");
	}

	/**
	 * Método utilitário para criar Identifier do mod.
	 * Facilita a criação de identificadores como "s3nha-stuffs:nome_do_item".
	 *
	 * @param path O caminho/ID do recurso (ex: "cobalt_ore")
	 * @return Identifier com o namespace do mod
	 */
	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
