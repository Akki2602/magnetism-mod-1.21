package net.akki.magnetismmod.block;

import net.akki.magnetismmod.MagnetismMod;
import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Item.Settings;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block MAGNET_ORE = registerBlock("magnet_ore",
            new Block(AbstractBlock.Settings.copy(Blocks.IRON_ORE).strength(3f)));

    public static final Block MAGNET_DEEPSLATE_ORE = registerBlock("magnet_deepslate_ore",
            new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_IRON_ORE).strength(4f)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new net.minecraft.util.Identifier(MagnetismMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MagnetismMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        MagnetismMod.LOGGER.info("Registering Mod Blocks for " + MagnetismMod.MOD_ID);
    }
}