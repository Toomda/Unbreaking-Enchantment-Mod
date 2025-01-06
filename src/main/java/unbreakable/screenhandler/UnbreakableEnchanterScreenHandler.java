package unbreakable.screenhandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import unbreakable.block.ModBlocks;
import unbreakable.block.entity.UnbreakableBlockEntity;
import unbreakable.custom.UnbreakableEnchanterOutputSlot;
import unbreakable.custom.UnbreakableEnchanterSlot;
import unbreakable.init.ScreenHandlerTypeInit;
import unbreakable.network.BlockPosPayload;

public class UnbreakableEnchanterScreenHandler extends ScreenHandler {
    private final UnbreakableBlockEntity blockEntity;
    private final ScreenHandlerContext context;

    //Client Constructor
    public UnbreakableEnchanterScreenHandler(int syncId, PlayerInventory playerInventory, BlockPosPayload payload) {
        this(syncId, playerInventory, (UnbreakableBlockEntity) playerInventory.player.getWorld().getBlockEntity(payload.pos()));
    }
    //Main Constructor
    public UnbreakableEnchanterScreenHandler(int syncId, PlayerInventory playerInventory, UnbreakableBlockEntity blockEntity) {
        super(ScreenHandlerTypeInit.UNBREAKABLE_ENCHANTER, syncId);

        this.blockEntity = blockEntity;
        this.context = ScreenHandlerContext.create(this.blockEntity.getWorld(), this.blockEntity.getPos());

        addSlot(new UnbreakableEnchanterSlot(blockEntity,0, 44, 30));
        addSlot(new UnbreakableEnchanterOutputSlot(blockEntity,1, 116, 30));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInventory, column, 8 + (column * 18), 142));
        }
    }

    private void addPlayerInventory(PlayerInventory playerInventory){
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInventory, 9 + (column + row * 9), 8 + (column * 18), 84 + (row * 18)));
            }
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        Slot slot = this.slots.get(slotIndex);

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            ItemStack newStack = originalStack.copy();

            // Handle output slot logic (slot 1)
            if (slot.inventory == this.blockEntity && slot.getIndex() == 1) {
                // Move the item from the output slot to the player's inventory/hotbar
                if (!this.insertItem(originalStack, 2, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }

                // Clear the input slot when the output item is moved
                Slot inputSlot = this.slots.get(0);
                if (inputSlot != null && inputSlot.hasStack()) {
                    inputSlot.setStack(ItemStack.EMPTY);
                }

                slot.onQuickTransfer(originalStack, newStack);
                slot.setStack(ItemStack.EMPTY); // Clear the output slot
                return newStack;
            }

            // Handle input slot logic (slot 0)
            if (slot.inventory == this.blockEntity && slot.getIndex() == 0) {
                // Move the input item to the player's inventory/hotbar
                if (!this.insertItem(originalStack, 2, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }

                slot.setStack(ItemStack.EMPTY); // Clear the input slot
                return newStack;
            }

            // Handle player inventory/hotbar to block entity slots
            if (!this.insertItem(originalStack, 0, 1, false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            return newStack;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);

        ItemStack stack = this.blockEntity.getStack(0);

        if(!stack.isEmpty()){
            player.dropItem(stack, false);
            this.blockEntity.setStack(0, ItemStack.EMPTY);
            this.blockEntity.setStack(1, ItemStack.EMPTY);
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, ModBlocks.UnbreakableEnchanterBlock);
    }

    public UnbreakableBlockEntity getBlockEntity() {
        return this.blockEntity;
    }
}
