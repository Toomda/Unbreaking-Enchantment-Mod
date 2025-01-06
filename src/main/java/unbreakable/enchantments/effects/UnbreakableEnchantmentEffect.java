package unbreakable.enchantments.effects;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.enchantment.effect.EnchantmentValueEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import unbreakable.mod.Unbreakable;

public record UnbreakableEnchantmentEffect(EnchantmentLevelBasedValue amount) implements EnchantmentValueEffect {
    public static final MapCodec<UnbreakableEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("amount").forGetter(UnbreakableEnchantmentEffect::amount)).apply(instance, UnbreakableEnchantmentEffect::new)
    );

    @Override
    public float apply(int level, Random random, float inputValue) {
        Unbreakable.LOGGER.info("Triggered Effect!");
        return 0;
    }

    @Override
    public MapCodec<? extends UnbreakableEnchantmentEffect> getCodec() {
        return CODEC;
    }
}
