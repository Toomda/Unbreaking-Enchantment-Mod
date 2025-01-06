package unbreakable.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import unbreakable.init.BlockEntityTypeInit;
import unbreakable.mod.Unbreakable;
import unbreakable.network.BlockPosPayload;
import unbreakable.screenhandler.UnbreakableEnchanterScreenHandler;
import unbreakable.util.TickableBlockEntity;

import static unbreakable.init.EnchantmentInit.UNBREAKABLE_KEY;

public class UnbreakableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPosPayload>, Inventory, TickableBlockEntity {
    public static final Text TITLE = Text.translatable("container." + Unbreakable.MOD_ID + ".unbreakable_enchanter");
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);

    @Override
    public void tick() {
        ItemStack inputStack = items.get(0); // Left slot
        ItemStack outputStack = items.get(1); // Right slot

        if (!inputStack.isEmpty() && outputStack.isEmpty()) {
            // Only process if input is not empty and output is empty
            ItemStack enchantedStack = inputStack.copy();
            DynamicRegistryManager manager = world.getRegistryManager();
            RegistryEntry<Enchantment> unbreakableEntry = manager.get(RegistryKeys.ENCHANTMENT).getEntry(UNBREAKABLE_KEY).orElseThrow();
            enchantedStack.addEnchantment(unbreakableEntry, 1);

            items.set(1, enchantedStack); // Set enchanted item in the right slot
            markDirty(); // Mark the inventory as dirty to sync with the client
        } else if (inputStack.isEmpty() && !outputStack.isEmpty()) {
            // Clear the output if input is removed
            items.set(1, ItemStack.EMPTY);
            markDirty();
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, this.items, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, this.items, registryLookup);
    }

    public UnbreakableBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypeInit.UNBREAKABLE_BLOCK_ENTITY, pos, state);
    }


    @Override
    public BlockPosPayload getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return new BlockPosPayload(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return TITLE;
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new UnbreakableEnchanterScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        return  items.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(items, slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(items, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        items.set(slot, stack);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        items.clear();
    }
}
