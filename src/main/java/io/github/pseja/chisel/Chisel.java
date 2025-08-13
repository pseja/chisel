package io.github.pseja.chisel;

import io.github.pseja.chisel.item.ChiselItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Chisel implements ModInitializer {
    public static final String MOD_ID = "chisel";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ChiselItems.registerChiselItems();
    }
}
