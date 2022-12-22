package com.annonasoftware.lignum.common.blockentities;

import com.annonasoftware.lignum.Lignum;
import com.annonasoftware.lignum.client.LignumSounds;
import com.annonasoftware.lignum.common.LignumHelpers;
import com.annonasoftware.lignum.common.blocks.devices.ChoppingBlock;
import com.annonasoftware.lignum.common.items.LignumItems;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blockentities.InventoryBlockEntity;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.capabilities.SidedHandler;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import java.util.Random;

public class ChoppingBlockEntity extends InventoryBlockEntity<ItemStackHandler>
{
    private static final int SLOT = 0;
    private static final Direction [] DIRECTIONS = new Direction[]{Direction.NORTH,Direction.SOUTH,Direction.EAST,Direction.WEST};

    public ChoppingBlockEntity(BlockPos pos, BlockState state) {
        this(LignumBlockEntities.CHOPPING_BLOCK.get(), pos, state);
    }

    public ChoppingBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, defaultInventory(1), LignumHelpers.blockEntityName("chopping_block"));
    }

    public InteractionResult onRightClick(Player player)
    {
        assert level != null;
        final ItemStack heldItem = player.getMainHandItem();
        final boolean inventoryFilled = !inventory.getStackInSlot(1).isEmpty();
        final boolean shouldInsert = !heldItem.isEmpty() && isItemValid(heldItem);

        if (inventoryFilled)
        {
            //if clicking with empty hand, remove log
            if (heldItem.isEmpty() && !level.isClientSide)
            {
                ItemHandlerHelper.giveItemToPlayer(player, inventory.extractItem(SLOT, 1, false),
                        player.getInventory().selected);
                markForBlockUpdate();
                return InteractionResult.sidedSuccess(level.isClientSide);
            }

            //if hand filled with appropriate tool, split the wood
            if(!heldItem.isEmpty() && isToolValid(heldItem))
            {
                Random rand = new Random();
                BlockPos entityPos = this.getBlockPos();
                Helpers.playSound(level, this.getBlockPos(), LignumSounds.LOG_SPLIT.get());

                //remove the log item and get its key
                TFCBlocks.WOODS.getOrDefault()

                ItemStack logItem = inventory.extractItem(SLOT, 1, false);

                ItemStack splitItem = LignumItems.SPLIT_LOGS.get(Wood.ACACIA).get();

                //spawn four split logs, scattered in the cardinal directions between 0 and 2 blocks away
                for (int i = 0; i < 4; i++)
                {

                    Helpers.spawnItem(level, entityPos.relative(DIRECTIONS[i], rand.nextInt(3)), splitItem);
                }



            }

        }

        else //chopping block empty
        {
            if(shouldInsert)
            {
                insertLog(heldItem.split(1));
                markForBlockUpdate();
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        if (!held.isEmpty() && Helpers.isItem(held, TFCTags.Items.LOG_PILE_LOGS))
        {
            level.getBlockEntity(pos, LignumBlockEntities.CHOPPING_BLOCK.get()).ifPresent(choppingBlock -> {
                boolean isFull = choppingBlock.isInventoryFull();
                if(!isFull)
                {
                    choppingBlock.insertLog(held);
                    held.shrink(1);
                }
                choppingBlock.updateState();
            });
            res = InteractionResult.sidedSuccess(level.isClientSide);
        }



        return InteractionResult.PASS;
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return Helpers.isItem(stack, TFCTags.Items.LOG_PILE_LOGS);
    }

    public boolean isToolValid(ItemStack item)
    {
        return Helpers.isItem(item, TFCTags.Items.AXES_THAT_LOG);
    }

    //Determines whether the held item is a log.
    public boolean isInventoryFull() {
        final ItemStack current = inventory.getStackInSlot(inventory.getSlots());
        if (current.isEmpty()) return false;
        else return true;
    }

    public void insertLog(ItemStack item) {
        inventory.insertItem(1, item, false);
    }

    public ItemStack removeLog() {
        final ItemStack current = inventory.getStackInSlot(0);
        if (!current.isEmpty()) {
            markForSync();
            return inventory.extractItem(1, Integer.MAX_VALUE, false);
        } else return ItemStack.EMPTY;
    }

    public void updateState() {
        assert level != null;
        boolean hasLog = hasLog();
        BlockState state = level.getBlockState(worldPosition);
        if (hasLog != state.getValue(ChoppingBlock.LOG_PLACED)) {
            level.setBlockAndUpdate(worldPosition, state.setValue(ChoppingBlock.LOG_PLACED, hasLog));
            markForSync();
        }
    }

    private boolean hasLog() {
        if (inventory.getStackInSlot(0).isEmpty()) return false;
        else return true;
    }
}

