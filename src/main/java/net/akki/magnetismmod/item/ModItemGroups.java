package net.akki.magnetismmod.item;

import net.akki.magnetismmod.MagnetismMod;
import net.akki.magnetismmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry; 
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.item.ItemGroups;

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
