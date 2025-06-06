package net.akki.magnetismmod;

import net.akki.magnetismmod.block.ModBlocks;

import net.akki.magnetismmod.item.ModItemGroups;
import net.akki.magnetismmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.util.Identifier;

public class MagnetismMod implements ModInitializer {
	public static final String MOD_ID = "magnetismmod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

	}
}