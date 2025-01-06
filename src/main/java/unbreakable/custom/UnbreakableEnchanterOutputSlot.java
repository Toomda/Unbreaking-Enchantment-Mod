package unbreakable.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import unbreakable.block.entity.UnbreakableBlockEntity;

public class UnbreakableEnchanterOutputSlot extends Slot {
    public UnbreakableEnchanterOutputSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        super.onTakeItem(player, stack);

        // Optionally, clear the input slot (if desired behavior)
        Inventory inventory = this.inventory;
        if (inventory instanceof UnbreakableBlockEntity blockEntity) {
            blockEntity.setStack(0, ItemStack.EMPTY); // Clear the input slot
            blockEntity.markDirty(); // Save changes
        }
    }
}
