package net.joashneves.s3nhastuff.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent MUG_COCOA = new FoodComponent.Builder().nutrition(2).saturationModifier(0.24f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100), 0.9F).build();
}
