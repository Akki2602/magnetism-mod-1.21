package net.akki.magnetismmod.item;

import net.akki.magnetismmod.MagnetismMod;
import net.akki.magnetismmod.item.custom.EntityMagnetIngotItem;
import net.akki.magnetismmod.item.custom.MagnetIngotItem;
import net.akki.magnetismmod.item.custom.RepulsionIngotItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Item.Settings;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item Magnet_Ingot = registerItem("magnet_ingot",
            new MagnetIngotItem(new Item.Settings().maxCount(1)));

    public static final Item Repulsion_Ingot = registerItem("repulsion_ingot",
            new RepulsionIngotItem(new Item.Settings().maxCount(1)));

    public static final Item Entity_Magnet_Ingot = registerItem("entity_magnet_ingot",
            new EntityMagnetIngotItem(new Item.Settings().maxCount(1).fireproof()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MagnetismMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MagnetismMod.LOGGER.info("Registering Mod Items for " + MagnetismMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(Magnet_Ingot);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(Entity_Magnet_Ingot);
        });
    }
}