package net.akki.magnetismmod.item;

import net.akki.magnetismmod.MagnetismMod;
import net.akki.magnetismmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.class_1761;
import net.minecraft.class_1799;
import net.minecraft.class_2378;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_7923;

public class ModItemGroups {
    public static final class_1761 MAGNETISM_GROUP = class_2378.method_10230(class_7923.field_44687,
            class_2960.method_60655(MagnetismMod.MOD_ID, "magnetism"),
            FabricItemGroup.builder().method_47320(() -> new class_1799(ModItems.Magnet_Ingot))
                    .method_47321(class_2561.method_43471("itemgroup.magnetismmod.magnetism"))
                    .method_47317((displayContext, entries) -> {
                        entries.method_45421(ModItems.Magnet_Ingot);
                        entries.method_45421(ModBlocks.MAGNET_ORE);
                        entries.method_45421(ModBlocks.MAGNET_DEEPSLATE_ORE);
                        entries.method_45421(ModItems.Repulsion_Ingot);
                        entries.method_45421(ModItems.Entity_Magnet_Ingot);

                    }).method_47324());

    public static void registerItemGroups(){
        MagnetismMod.LOGGER.info("Registering Item Groups for " + MagnetismMod.MOD_ID);
    }
}
