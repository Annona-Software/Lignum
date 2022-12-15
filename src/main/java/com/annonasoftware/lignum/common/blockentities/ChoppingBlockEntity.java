package com.annonasoftware.lignum.common.blockentities;

import net.dries007.tfc.common.blockentities.InventoryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public class ChoppingBlockEntity extends InventoryBlockEntity<ItemStackHandler>
{

    public ChoppingBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, InventoryFactory<ItemStackHandler> inventoryFactory, Component defaultName)
    {
        super(type, pos, state, inventoryFactory, defaultName);
    }
}
