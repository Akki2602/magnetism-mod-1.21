package net.akki.magnetismmod.item;

import net.akki.magnetismmod.MagnetismMod;
import net.akki.magnetismmod.item.custom.EntityMagnetIngotItem;
import net.akki.magnetismmod.item.custom.MagnetIngotItem;
import net.akki.magnetismmod.item.custom.RepulsionIngotItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.class_1792;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_7706;
import net.minecraft.class_7923;

public class ModItems {
    public static final class_1792 Magnet_Ingot = registerItem("magnet_ingot", new MagnetIngotItem(new class_1792.class_1793().method_7889(1)));

    public static final class_1792 Repulsion_Ingot = registerItem("repulsion_ingot",
            new RepulsionIngotItem(new class_1792.class_1793().method_7889(1)));

    public static final class_1792 Entity_Magnet_Ingot = registerItem("entity_magnet_ingot",
            new EntityMagnetIngotItem(new class_1792.class_1793().method_7889(1).method_24359()));



    private static class_1792 registerItem(String name, class_1792 item) {
        return class_2378.method_10230(class_7923.field_41178, class_2960.method_60655(MagnetismMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MagnetismMod.LOGGER.info("Registering Mod Items for " + MagnetismMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(class_7706.field_41062).register(entries -> {
            entries.method_45421(Magnet_Ingot);
        });
        ItemGroupEvents.modifyEntriesEvent(class_7706.field_41060).register(entries -> {
            entries.method_45421(ModItems.Entity_Magnet_Ingot);
        });

    }
}