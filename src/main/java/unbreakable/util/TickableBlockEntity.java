package unbreakable.util;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;

public interface TickableBlockEntity {
    void tick();

    static <T extends BlockEntity> BlockEntityTicker<T> getTicker() {
        return (world, pos, state, blockEntity) -> {
          if(blockEntity instanceof TickableBlockEntity tickableBlockEntity){
              tickableBlockEntity.tick();
          }
        };
    }
}
