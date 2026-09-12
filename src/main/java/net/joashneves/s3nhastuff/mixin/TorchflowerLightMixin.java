package net.joashneves.s3nhastuff.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TorchflowerBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Faz a plantoíba (torchflower) emitir luz que aumenta conforme o crescimento:
 * - muda (torchflower_crop, age 0): luz 9
 * - muda (torchflower_crop, age 1): luz 11
 * - flor madura (torchflower): luz 13 (entre tocha=14 e fraca, mas forte que tocha de redstone=7)
 */
@Mixin(AbstractBlock.AbstractBlockState.class)
public class TorchflowerLightMixin {

    @Inject(method = "getLuminance", at = @At("HEAD"), cancellable = true)
    private void s3nha_giveTorchflowerLight(CallbackInfoReturnable<Integer> cir) {
        BlockState state = (BlockState) (Object) this;
        Block block = state.getBlock();

        if (block == Blocks.TORCHFLOWER) {
            cir.setReturnValue(13);
        } else if (block == Blocks.TORCHFLOWER_CROP) {
            cir.setReturnValue(9 + state.get(TorchflowerBlock.AGE) * 2);
        }
    }
}