package net.akki.magnetismmod.block;

import net.akki.magnetismmod.MagnetismMod;
import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModBlocks {



    public static final class_2248 MAGNET_ORE = registerBlock("magnet_ore",
            new class_2431(class_6019.method_35017(2, 5),
                    class_4970.class_2251.method_9637().method_9632(3f).method_29292()));


    public static final class_2248 MAGNET_DEEPSLATE_ORE = registerBlock("magnet_deepslate_ore",
            new class_2431(class_6019.method_35017(3, 6),
                    class_4970.class_2251.method_9637().method_9632(4f).method_29292().method_9626(class_2498.field_29033)));

    private static class_2248 registerBlock(String name, class_2248 block){
        registerBlockItem(name, block);
        return class_2378.method_10230(class_7923.field_41175, class_2960.method_60655(MagnetismMod.MOD_ID, name), block);
    }


    private static void registerBlockItem(String name, class_2248 block) {
        class_2378.method_10230(class_7923.field_41178, class_2960.method_60655(MagnetismMod.MOD_ID, name),
                new class_1747(block, new class_1792.class_1793()));
    }
    public static void registerModBlocks() {
    MagnetismMod.LOGGER.info("Registering Mod Blocks for " + MagnetismMod.MOD_ID);



    }
}