package net.joashneves.s3nhastuff.item.custom;

import net.joashneves.s3nhastuff.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

/**
 * Caneca de cacau bebível.
 * Funciona como o frasco de mel (HoneyBottleItem): ao beber, a caneca vazia
 * volta para o inventário do jogador (mesmo comportamento do frasco de poção).
 */
public class MugCocoaItem extends Item {

    private static final int MAX_USE_TIME = 40;

    public MugCocoaItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        if (stack.isEmpty()) {
            return new ItemStack(ModItems.MUG);
        }
        if (user instanceof PlayerEntity playerEntity && !playerEntity.isInCreativeMode()) {
            playerEntity.getInventory().offerOrDrop(new ItemStack(ModItems.MUG));
        }
        return itemStack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return MAX_USE_TIME;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }
}