package unbreakable.init;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import unbreakable.block.ModBlocks;
import unbreakable.block.entity.UnbreakableBlockEntity;
import unbreakable.mod.Unbreakable;

public class BlockEntityTypeInit {
    public static final BlockEntityType<UnbreakableBlockEntity> UNBREAKABLE_BLOCK_ENTITY = register("unbreakable_block_entity",
            BlockEntityType.Builder.create(UnbreakableBlockEntity::new, ModBlocks.UnbreakableEnchanterBlock).build());

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Unbreakable.MOD_ID, name), type);
    }

    public static void load() {}
}
