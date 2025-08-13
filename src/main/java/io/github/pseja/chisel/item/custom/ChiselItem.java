package io.github.pseja.chisel.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ChiselItem extends Item {
    public ChiselItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return super.isEnchantable(stack);
    }
}
