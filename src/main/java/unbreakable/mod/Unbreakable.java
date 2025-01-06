package unbreakable.mod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import unbreakable.block.ModBlocks;
import unbreakable.init.BlockEntityTypeInit;
import unbreakable.init.EnchantmentInit;
import unbreakable.init.ScreenHandlerTypeInit;

import static unbreakable.init.EnchantmentInit.UNBREAKABLE_KEY;

public class Unbreakable implements ModInitializer {
	public static final String MOD_ID = "unbreakable";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
		BlockEntityTypeInit.load();
		ScreenHandlerTypeInit.load();
		EnchantmentInit.load();
		LOGGER.info("Hello Fabric world!");
	}
}