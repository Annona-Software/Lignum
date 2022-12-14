package com.annonasoftware.lignum.common.blocks;

import net.dries007.tfc.client.IGhostBlockHandler;
import net.dries007.tfc.client.TFCSounds;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.IForgeBlockExtension;
import net.dries007.tfc.common.blocks.TFCBlockStateProperties;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.wood.TFCFenceBlock;
import net.dries007.tfc.common.fluids.FluidHelpers;
import net.dries007.tfc.common.fluids.FluidProperty;
import net.dries007.tfc.common.fluids.IFluidLoggable;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;

import static net.dries007.tfc.common.blocks.TFCBlockStateProperties.*;

public class WattleFenceBlock extends TFCFenceBlock implements IForgeBlockExtension, IGhostBlockHandler, IFluidLoggable
{
    public static final BooleanProperty WOVEN = TFCBlockStateProperties.WATTLE_WOVEN;
    public static final FluidProperty FLUID = TFCBlockStateProperties.WATER;

    public WattleFenceBlock(ExtendedProperties properties)
    {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(TOP, false).setValue(BOTTOM, false).setValue(LEFT, false).setValue(RIGHT, false).setValue(WOVEN, false).setValue(FLUID, FLUID.keyFor(Fluids.EMPTY)));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        if (hand == InteractionHand.OFF_HAND) return InteractionResult.PASS;
        if (state.getValue(getFluidProperty()).getFluid() != Fluids.EMPTY) return InteractionResult.PASS;
        final ItemStack item = player.getItemInHand(hand);
        final boolean woven = state.getValue(WOVEN);
        if (!woven && Helpers.isItem(item, Tags.Items.RODS_WOODEN) && item.getCount() >= 2)
        {
            Helpers.playSound(level, pos, TFCSounds.WATTLE_WOVEN.get());
            return setState(level, pos, state.setValue(WOVEN, true), player, item, 2); // add sticks
        }

        return super.use(state, level, pos, player, hand, hit); // other behavior
    }

    protected InteractionResult setState(Level level, BlockPos pos, BlockState state, Player player, ItemStack item, int toShrink)
    {
        if (!player.isCreative()) item.shrink(toShrink);
        level.setBlockAndUpdate(pos, state);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Nullable
    @Override
    public BlockState getStateToDraw(Level level, Player player, BlockState lookState, Direction direction, BlockPos lookPos, double x, double y, double z, ItemStack item)
    {
        if (lookState.getValue(getFluidProperty()).getFluid() != Fluids.EMPTY) return null;
        final boolean woven = lookState.getValue(WOVEN);
        if (!woven && Helpers.isItem(item, Tags.Items.RODS_WOODEN) && item.getCount() >= 4)
        {
            return lookState.setValue(WOVEN, true);
        }

        return getStateToDraw(level, player, lookState, direction, lookPos, x, y, z, item);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        final FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        if (getFluidProperty().canContain(fluidState.getType()))
        {
            return defaultBlockState().setValue(getFluidProperty(), getFluidProperty().keyFor(fluidState.getType()));
        }
        return defaultBlockState();
    }

    @Override
    public FluidProperty getFluidProperty()
    {
        return FLUID;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder.add(WOVEN, getFluidProperty()));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return state.getValue(WOVEN) ? super.getCollisionShape(state, level, pos, context) : Shapes.empty();
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos)
    {
        return state.getValue(WOVEN) ? super.getOcclusionShape(state, level, pos) : Shapes.empty();
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state)
    {
        return IFluidLoggable.super.getFluidState(state);
    }


    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos)
    {
        FluidHelpers.tickFluid(level, currentPos, state);
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float distance)
    {
        super.fallOn(level, state, pos, entity, distance);
        if (distance > 3f)
        {
            level.destroyBlock(pos, true);
        }
    }
}