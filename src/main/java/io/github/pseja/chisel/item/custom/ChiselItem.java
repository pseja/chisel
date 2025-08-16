package io.github.pseja.chisel.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChiselItem extends Item {
    public ChiselItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return super.isEnchantable(stack);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        if (player == null) {
            return ActionResult.PASS;
        }

        ItemStack offhandStack = player.getOffHandStack();
        if (offhandStack.isEmpty() || !(offhandStack.getItem() instanceof BlockItem blockItem)) {
            return ActionResult.PASS;
        }

        World world = context.getWorld();
        if (world.isClient()) {
            return ActionResult.SUCCESS;
        }

        BlockPos clickedBlockPos = context.getBlockPos();

        Block clickedBlock = world.getBlockState(clickedBlockPos).getBlock();
        if (clickedBlock == Blocks.BEDROCK && !player.isCreative()) {
            return ActionResult.FAIL;
        }

        if (!world.breakBlock(clickedBlockPos, !player.isCreative(), player)) {
            return ActionResult.FAIL;
        }
        blockItem.place(
                new ItemPlacementContext(context) {
                    @Override
                    public ItemStack getStack() {
                        return offhandStack;
                    }
                }
        );

        if (world instanceof ServerWorld serverWorld && player instanceof ServerPlayerEntity serverPlayer) {
            context.getStack().damage(1, serverWorld, serverPlayer,
                    item -> serverPlayer.sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));
        }

        return ActionResult.CONSUME;
    }
}
