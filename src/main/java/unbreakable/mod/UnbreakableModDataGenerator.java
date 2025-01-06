package unbreakable.mod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import unbreakable.data.generator.ModEnchantmentGenerator;

public class UnbreakableModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
     FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
     pack.addProvider(ModEnchantmentGenerator::new);
    }
}
