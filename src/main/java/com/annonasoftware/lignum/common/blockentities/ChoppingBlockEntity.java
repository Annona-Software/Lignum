package com.annonasoftware.lignum.common.blockentities;

import com.annonasoftware.lignum.common.LignumHelpers;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blockentities.InventoryBlockEntity;
import net.dries007.tfc.common.capabilities.SidedHandler;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class ChoppingBlockEntity extends InventoryBlockEntity<ItemStackHandler>
{
    public ChoppingBlockEntity(BlockPos pos, BlockState state)
    {
        this(LignumBlockEntities.CHOPPING_BLOCK.get(), pos, state);
    }

    public ChoppingBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state, defaultInventory(1), LignumHelpers.blockEntityName("chopping_block"));
    }

    @Override
    //Determines whether the held item is a log.
    public boolean isItemValid(int slot, ItemStack stack)
    {
        final ItemStack current = inventory.getStackInSlot(slot);
        if (!current.isEmpty())
        {
            if (Helpers.isItem(current, TFCTags.Items.LOG_PILE_LOGS)) return true;
        }
        return false;
    }

    public InteractionResult use(Player player, InteractionHand hand) {
        assert level != null;
        var res = InteractionResult.PASS;
        final ItemStack held = player.getItemInHand(hand);

        //if holding log and there is no log in the chopping block, put in entity inventory
        if (!held.isEmpty() && isItemValid(0, held) && inventory.getStackInSlot(1).isEmpty()) {
            inventory.insertItem(1, held, false);
            res = InteractionResult.sidedSuccess(level.isClientSide);
        }

        //if holding axe, split
        else if (!held.isEmpty() && held.is(TFCTags.Items.AXES_THAT_LOG))
        {
            ItemStack extracted = inventory.extractItem(1, Integer.MAX_VALUE, false); //remove log from inventory but do not place in player's
            Helpers.spawnItem(level, this.getBlockPos(), extracted); //right now just drop the log
        }

        //remove log from entity inventory and return to player
        else if (held.isEmpty() && player.isShiftKeyDown())
        {
            ItemStack stack = inventory.extractItem(0, 1, false);
            if (stack.isEmpty()) return InteractionResult.PASS;

            ItemHandlerHelper.giveItemToPlayer(player, stack);
            res = InteractionResult.sidedSuccess(level.isClientSide);
        }
        markForSync();
        return res;
    }


}
