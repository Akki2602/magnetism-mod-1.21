package net.akki.magnetismmod.item;

import net.akki.magnetismmod.MagnetismMod;
import net.akki.magnetismmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.item.ItemGroup;

public class ModItemGroups {
    public static final ItemGroup MAGNETISM_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(MagnetismMod.MOD_ID, "magnetism"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.Magnet_Ingot))
                    .displayName(Text.translatable("itemgroup.magnetismmod.magnetism"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.Magnet_Ingot);
                        entries.add(ModBlocks.MAGNET_ORE);
                        entries.add(ModBlocks.MAGNET_DEEPSLATE_ORE);
                        entries.add(ModItems.Repulsion_Ingot);
                        entries.add(ModItems.Entity_Magnet_Ingot);
                    })
                    .build()
    );

    public static void registerItemGroups(){
        MagnetismMod.LOGGER.info("Registering Item Groups for " + MagnetismMod.MOD_ID);
    }
}