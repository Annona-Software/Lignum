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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

public class ChoppingBlockEntity extends InventoryBlockEntity<ItemStackHandler>
{
    private static final int SLOT = 0;

    public ChoppingBlockEntity(BlockPos pos, BlockState state)
    {
        super(LignumBlockEntities.CHOPPING_BLOCK.get(), pos, state, defaultInventory(1), LignumHelpers.blockEntityName("chopping_block"));
    }

    public InteractionResult onRightClick(Player player)
    {

        assert level != null;
        final ItemStack heldItem = player.getMainHandItem();
        final boolean inventoryFilled = !inventory.getStackInSlot(SLOT).isEmpty();
        final boolean shouldInsert = !heldItem.isEmpty() && isItemValid(SLOT, heldItem);

        if (inventoryFilled)
        {
            //if clicking with empty hand, remove log
            if (heldItem.isEmpty() && !level.isClientSide)
            {
                ItemHandlerHelper.giveItemToPlayer(player, inventory.extractItem(SLOT, 1, false),
                        player.getInventory().selected);
                markForBlockUpdate();
                updateState();
                return InteractionResult.sidedSuccess(level.isClientSide);
            }

            //if hand filled with appropriate tool, split the wood
            if(!heldItem.isEmpty() && isToolValid(heldItem))
            {
                Random rand = new Random();
                BlockPos entityPos = this.getBlockPos();
                Helpers.playSound(level, this.getBlockPos(), LignumSounds.LOG_SPLIT.get());

                ItemStack logItem = inventory.extractItem(SLOT, 1, false);

                //ItemStack splitItem = new ItemStack(LignumItems.SPLIT_LOGS.get(Wood.ACACIA).get());

                ItemStack splitItem = new ItemStack(LignumItems.SPLIT_LOGS.get(logItem.))
                //ItemStack splitItem = LignumItems.SPLIT_LOGS.get(TFCBlocks.WOODS.get(logItem));

                //for(Map.Entry<Wood, Map<Wood.BlockType, RegistryObject<Block>>> w : TFCBlocks.WOODS.entrySet())
                //{
                //    if(w.getKey().equals(logItem.))
                //}
                //Helpers.DIRECTIONS.

                //TFCBlocks.WOODS.forEach((wood, map) -> ;
                //        Wood (blocktype, registryobject Block)
                //)
                //spawn four split logs, scattered in the cardinal directions between 0 and 3 blocks away
                for (int i = 0; i < 4; i++)
                {
                    Helpers.spawnItem(level, entityPos.relative(Helpers.DIRECTIONS[i], rand.nextInt(3)), splitItem);
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        if(!inventoryFilled) //chopping block empty
        {
            if(shouldInsert) //holding a log
            {
                inventory.insertItem(SLOT, heldItem.split(1), false);
                markForBlockUpdate();
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack)
    {
        return Helpers.isItem(stack, TFCTags.Items.LOG_PILE_LOGS);
    }

    public boolean isToolValid(ItemStack item)
    {
        return Helpers.isItem(item, TFCTags.Items.AXES_THAT_LOG);
    }

    @Override
    public int getSlotStackLimit(int slot)
    {
        return 1;
    }

    public void updateState()
    {
        assert level != null;
        boolean hasLog = hasLog();
        BlockState state = level.getBlockState(worldPosition);
        if (hasLog != state.getValue(ChoppingBlock.LOG_PLACED)) {
            level.setBlockAndUpdate(worldPosition, state.setValue(ChoppingBlock.LOG_PLACED, hasLog));
            markForSync();
        }
    }

    private boolean hasLog() {
        if (inventory.getStackInSlot(SLOT).isEmpty()) return false;
        else return true;
    }
}

