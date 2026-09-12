package net.joashneves.s3nhastuff.block.entity.client;

import net.joashneves.s3nhastuff.block.entity.MugBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.RotationAxis;

/**
 * Renderer da caneca (mug).
 *
 * Rota o modelo 3D da caneca em torno do seu centro para imitar o comportamente de
 * cabeças de mob: a propriedade ROTATION (0-15) é transformada em ângulo (rotation * 22.5).
 * O bloco em si é renderizado como INVISÍVEL no pass normal de blocos (BlockRenderType.INVISIBLE);
 * quem desenha a caneca é este renderer.
 */
public class MugBlockEntityRenderer implements BlockEntityRenderer<MugBlockEntity> {

    public MugBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    @Override
    public void render(MugBlockEntity blockEntity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (blockEntity.getWorld() == null) {
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        BlockState state = blockEntity.getCachedState();
        int rotation = state.get(Properties.ROTATION);

        BakedModel mugModel = client.getBlockRenderManager().getModel(state);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getCutout());

        matrices.push();
        // Rotaciona a caneca em torno do eixo vertical no centro do bloco, assentada no chão
        matrices.translate(0.5, 0.0, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotation * 22.5f));
        matrices.translate(-0.5, 0.0, -0.5);

        int lightAtPosition = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), state, blockEntity.getPos());
        client.getBlockRenderManager().getModelRenderer().render(
                matrices.peek(), vertexConsumer, state, mugModel,
                1.0F, 1.0F, 1.0F, lightAtPosition, OverlayTexture.DEFAULT_UV);

        matrices.pop();
    }
}