package io.github.pseja.chisel.item;

import io.github.pseja.chisel.Chisel;
import io.github.pseja.chisel.item.custom.ChiselItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.stream.Stream;

public class ChiselItems {
    public static final Item WOODEN_CHISEL = registerItem("wooden_chisel", new ChiselItem(new Item.Settings().maxDamage(59)));
    public static final Item STONE_CHISEL = registerItem("stone_chisel", new ChiselItem(new Item.Settings().maxDamage(131)));
    public static final Item IRON_CHISEL = registerItem("iron_chisel", new ChiselItem(new Item.Settings().maxDamage(250)));
    public static final Item GOLDEN_CHISEL = registerItem("golden_chisel", new ChiselItem(new Item.Settings().maxDamage(32)));
    public static final Item DIAMOND_CHISEL = registerItem("diamond_chisel", new ChiselItem(new Item.Settings().maxDamage(1561)));
    public static final Item NETHERITE_CHISEL = registerItem("netherite_chisel", new ChiselItem(new Item.Settings().maxDamage(2031).fireproof()));

    public static void registerChiselItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.addAll(Stream.of(WOODEN_CHISEL, STONE_CHISEL, IRON_CHISEL, GOLDEN_CHISEL, DIAMOND_CHISEL, NETHERITE_CHISEL).map(ItemStack::new).toList());
        });
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Chisel.MOD_ID, name), item);
    }
}
