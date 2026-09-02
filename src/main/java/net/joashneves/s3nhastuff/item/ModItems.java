package net.joashneves.s3nhastuff.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.joashneves.s3nhastuff.S3nhaStuffs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item RAW_COBALT = registerItem("raw_cobalt", new Item(new Item.Settings()));
    public static final Item COBALT_INGOT = registerItem("cobalt_ingot", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(S3nhaStuffs.MOD_ID, name), item);
    }

    public static void registerItems() {
        S3nhaStuffs.LOGGER.info("Registering Items : " + S3nhaStuffs.MOD_ID);

        // Coloca os itens que criamos na aba do creativo no grupo de Itens de ingrediente
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(RAW_COBALT);
            entries.add(COBALT_INGOT);
        });
    }

}
