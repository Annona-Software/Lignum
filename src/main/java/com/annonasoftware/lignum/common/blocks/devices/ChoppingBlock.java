package com.annonasoftware.lignum.common.blocks.devices;

import com.annonasoftware.lignum.common.blockentities.ChoppingBlockEntity;
import com.annonasoftware.lignum.common.blockentities.LignumBlockEntities;
import com.annonasoftware.lignum.common.blocks.LignumStateProperties;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.devices.BottomSupportedDeviceBlock;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemHandlerHelper;

public class ChoppingBlock extends BottomSupportedDeviceBlock
{
    private static final VoxelShape SHAPE = box(2, 0, 2, 12, 8, 12);
    public static final BooleanProperty LOG_PLACED = LignumStateProperties.LOG_PLACED;

    public ChoppingBlock(ExtendedProperties properties)
    {
        super(properties, InventoryRemoveBehavior.DROP, SHAPE);
        registerDefaultState(getStateDefinition().any().setValue(LOG_PLACED, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
    {
        ChoppingBlockEntity choppingBlock = level.getBlockEntity(pos, LignumBlockEntities.CHOPPING_BLOCK.get()).orElse(null);
        if(choppingBlock != null)
        {
            return choppingBlock.onRightClick(player);
        }
        return InteractionResult.PASS;
    }



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder.add(LOG_PLACED));
    }
}
