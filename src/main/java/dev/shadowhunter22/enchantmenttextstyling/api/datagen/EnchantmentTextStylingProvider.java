//
// Copyright (c) 2024 by ShadowHunter22. All rights reserved.
// See LICENSE file in the project root for details.
//

package dev.shadowhunter22.enchantmenttextstyling.api.datagen;

import dev.shadowhunter22.enchantmenttextstyling.EnchantmentTextStyling;
import dev.shadowhunter22.enchantmenttextstyling.api.registry.ModRegistryKeys;
import dev.shadowhunter22.enchantmenttextstyling.api.EnchantmentStyling;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public abstract class EnchantmentTextStylingProvider extends FabricCodecDataProvider<List<EnchantmentStyling>> {
    protected EnchantmentTextStylingProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(dataOutput, registriesFuture, ModRegistryKeys.STYLING, EnchantmentStyling.CODEC.listOf());
    }

    @Override
    protected void configure(BiConsumer<Identifier, List<EnchantmentStyling>> provider, RegistryWrapper.WrapperLookup lookup) {
        this.generate(lookup);

        HashMap<Identifier, List<EnchantmentStyling>> hashmap = new HashMap<>();

        for (EnchantmentStyling enchantmentStyling : EnchantmentTextStylingProviderBuilder.entries) {
            Identifier key = Identifier.of(
                    EnchantmentTextStyling.MOD_ID,
                    enchantmentStyling.id().getNamespace() + "/" + enchantmentStyling.id().getPath()
            );

            hashmap.computeIfAbsent(key, k -> new ArrayList<>()).add(enchantmentStyling);
        }

        hashmap.forEach(provider);
    }

    /**
     * Generate JSON files for enchantments.  Here is an example of how to generate a JSON file for an enchantment:
     *
     * <pre>
     * {@code
     * public class EnchantmentTextData extends EnchantmentTextProvider {
     *     protected EnchantmentTextData(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
     *      super(dataOutput, registriesFuture);
     * }
     *
     *  @Override
     *  public void generate() {
     *      this.addEntry(Enchantments.PROTECTION)
     *              // It is required to call this or a default value will apply.
     * 	        .color(Formatting.AQUA.getColorValue())
     *
     * 	        // These chained methods are optional.  See further documentation below concerning these values
     * 	        .specificCondition(0)
     * 	        .min(0)
     * 	        .max(2)
     *
     * 	        // This is required to be called in order to generate the JSON file
     * 	        .add();
     *  }
     * }
     * }
     * </pre>
     *
     * @see EnchantmentTextStylingProviderBuilder#color(int)
     * @see EnchantmentTextStylingProviderBuilder#specificCondition(int)
     * @see EnchantmentTextStylingProviderBuilder#min(int)
     * @see EnchantmentTextStylingProviderBuilder#max(int)
     * @see EnchantmentTextStylingProviderBuilder#add()
     */
    public abstract void generate(RegistryWrapper.WrapperLookup lookup);

    /**
     * Add an entry to {@link EnchantmentTextStylingProvider} to generate a JSON file.  See {@link EnchantmentTextStylingProvider#generate}
     * for implementation details.
     *
     * @param enchantment the RegistryKey of the enchantment to provide styling for
     * @return {@link EnchantmentTextStylingProviderBuilder} a builder to modify text color and set conditions for the enchantment.
     * See {@link EnchantmentTextStylingProvider#generate} for implementation details.
     */
    public EnchantmentTextStylingProviderBuilder addEntry(RegistryKey<Enchantment> enchantment) {
        return new EnchantmentTextStylingProviderBuilder(enchantment);
    }

    @Override
    public String getName() {
        return "EnchantmentTextProvider";
    }
}