package unbreakable.data.generator;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.value.RemoveBinomialEnchantmentEffect;
import net.minecraft.item.Item;
import net.minecraft.loot.condition.InvertedLootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import unbreakable.enchantments.effects.UnbreakableEnchantmentEffect;
import unbreakable.init.EnchantmentInit;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentGenerator extends FabricDynamicRegistryProvider {
    public ModEnchantmentGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        RegistryWrapper<Item> itemLookup = registries.getWrapperOrThrow(RegistryKeys.ITEM);

        register(entries, EnchantmentInit.UNBREAKABLE_KEY, Enchantment.builder(
                Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                        0, //probability of showing up in enchantment table
                        1, //max level
                        Enchantment.leveledCost(1, 1),
                        Enchantment.leveledCost(1,1),
                        1,
                        AttributeModifierSlot.ANY
                )
        ).addEffect(
                        EnchantmentEffectComponentTypes.ITEM_DAMAGE,
                        new UnbreakableEnchantmentEffect(
                                new EnchantmentLevelBasedValue.Fraction(EnchantmentLevelBasedValue.linear(0.0F), EnchantmentLevelBasedValue.linear(0.0F, 0.0F))
                        ),
                        MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.ARMOR_ENCHANTABLE))
                )
                .addEffect(
                        EnchantmentEffectComponentTypes.ITEM_DAMAGE,
                        new UnbreakableEnchantmentEffect(
                                new EnchantmentLevelBasedValue.Fraction(EnchantmentLevelBasedValue.linear(0.0F), EnchantmentLevelBasedValue.linear(0.0F, 0.0F))
                        ),
                        InvertedLootCondition.builder(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.ARMOR_ENCHANTABLE)))
                ));
    }

    private static void register(Entries entries, RegistryKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.getValue()), resourceConditions);
    }

    @Override
    public String getName() {
        return "Enchantment Generator";
    }
}
