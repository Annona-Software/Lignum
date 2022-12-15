package com.annonasoftware.lignum.common.blocks.devices;

import com.annonasoftware.lignum.common.blockentities.ChoppingBlockEntity;
import com.annonasoftware.lignum.common.blockentities.LignumBlockEntities;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.TFCBlockStateProperties;
import net.dries007.tfc.common.blocks.devices.BottomSupportedDeviceBlock;
import net.dries007.tfc.common.blocks.devices.DeviceBlock;
import net.dries007.tfc.common.fluids.FluidProperty;
import net.dries007.tfc.common.fluids.IFluidLoggable;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;

public class ChoppingBlock extends BottomSupportedDeviceBlock implements IFluidLoggable
{
    public static final FluidProperty FLUID = TFCBlockStateProperties.WATER;

    public ChoppingBlock(ExtendedProperties properties) {
        super(properties, InventoryRemoveBehavior.DROP);
    }

    @Override
    @SuppressWarnings("deprecation")
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        if (state.getValue(getFluidProperty()).getFluid() != Fluids.EMPTY) return InteractionResult.PASS;

        final ChoppingBlockEntity choppingBlock = level.getBlockEntity(pos, LignumBlockEntities.CHOPPING_BLOCK.get()).orElse(null);
        if (barrel != null)
        {
            final ItemStack item = player.getItemInHand(hand);

            if (Helpers.isItem(item, TFCTags.Items.LOG_PILE_LOGS))
            {
                level.playSound(null, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0f, 0.85f);
                return InteractionResult.SUCCESS;
            }
            //grab unsplit log
            if (item.isEmpty() && player.isShiftKeyDown())
            {
                toggleSeal(level, pos, state);
                level.playSound(null, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0f, 0.85f);
                return InteractionResult.SUCCESS;
            }


            else if (FluidHelpers.transferBetweenBlockEntityAndItem(stack, barrel, player, hand))
            {
                return InteractionResult.SUCCESS;
            }
            else if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer)
            {
                Helpers.openScreen(serverPlayer, barrel, barrel.getBlockPos());
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public FluidProperty getFluidProperty() {
        return FLUID;
    }
}
