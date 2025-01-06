package unbreakable.custom;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;

public class UnbreakableEnchanterSlot extends Slot {
    public UnbreakableEnchanterSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        Item item  = stack.getItem();

        return item instanceof ToolItem || item instanceof SwordItem || item instanceof ArmorItem;
    }
}
