package net.joashneves.s3nhastuff.block.entity;

import net.joashneves.s3nhastuff.S3nhaStuffs;
import net.joashneves.s3nhastuff.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

/**
 * Registra os BlockEntityTypes do mod.
 *
 * O BlockEntityType do mug é necessário para renderizar a caneca com rotação de
 * cabeça de mob (16 direções). O molde vanilla de cabeças gira o modelo no renderer
 * (BlockEntityRenderer) porque o blockstate só suporta rotações de 90 em 90 graus.
 */
public class ModBlockEntities {

    /**
     * BlockEntityType da caneca (mug).
     * Associado ao bloco MUG_BLOCK: cada caneca colocada no mundo ganha um MugBlockEntity.
     */
    public static final BlockEntityType<MugBlockEntity> MUG_BE =
            BlockEntityType.Builder.create(MugBlockEntity::new, ModBlocks.MUG_BLOCK).build(null);

    /**
     * Regista os BlockEntityTypes dos blocos.
     * Chamado durante a inicialização do mod.
     */
    public static void registerBlockEntities() {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, S3nhaStuffs.id("mug_block_entity"), MUG_BE);
        S3nhaStuffs.LOGGER.info("Registering Block Entities for : " + S3nhaStuffs.MOD_ID);
    }
}