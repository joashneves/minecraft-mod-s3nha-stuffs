package net.joashneves.s3nhastuff;

import net.fabricmc.api.ClientModInitializer;
import net.joashneves.s3nhastuff.block.entity.ModBlockEntities;
import net.joashneves.s3nhastuff.block.entity.client.MugBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class S3nhaStuffClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(ModBlockEntities.MUG_BE, MugBlockEntityRenderer::new);
    }
}