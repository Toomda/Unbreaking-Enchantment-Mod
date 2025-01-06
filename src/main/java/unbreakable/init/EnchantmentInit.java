package unbreakable.init;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentValueEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import unbreakable.enchantments.effects.UnbreakableEnchantmentEffect;
import unbreakable.mod.Unbreakable;

public class EnchantmentInit {
    public static final RegistryKey<Enchantment> UNBREAKABLE_KEY = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(Unbreakable.MOD_ID, "unbreakable"));

    public static final MapCodec<UnbreakableEnchantmentEffect> UNBREAKABLE_EFFECT = register("unbreakable", UnbreakableEnchantmentEffect.CODEC);

    private static <T extends EnchantmentValueEffect>MapCodec<T> register(String name, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_VALUE_EFFECT_TYPE, Identifier.of(Unbreakable.MOD_ID, name), codec);
    }

    public static void load() {}
}
