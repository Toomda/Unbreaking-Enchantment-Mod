package unbreakable.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import unbreakable.custom.UnbreakableEnchanter;
import unbreakable.mod.Unbreakable;

public class ModBlocks {
    public static final UnbreakableEnchanter UnbreakableEnchanterBlock = registerBlock("unbreakable_enchanter", new UnbreakableEnchanter(AbstractBlock.Settings.create().mapColor(MapColor.RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().luminance(state -> 7).strength(5.0F, 1200.0F)));


    private static <T extends Block> T registerBlock(String name, T block) {
        registerBlockItem(name, block);
        return register(name, block);
    }

    public static <T extends Block> T register(String name, T block) {
        return Registry.register(Registries.BLOCK, Identifier.of(Unbreakable.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Unbreakable.MOD_ID, name),new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Unbreakable.LOGGER.info("TEST INFO");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModBlocks.UnbreakableEnchanterBlock);
        });
    }
}
