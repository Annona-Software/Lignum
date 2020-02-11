package com.annonasoftware.games.woodworking.util;

/*
 *  Copyright (c) 2020 madflavius under the terms of GPL v3 
 */

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Joiner;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.BlockStructure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.registries.IForgeRegistryEntry;

import io.netty.buffer.ByteBuf;
import net.dries007.tfc.Constants;
import net.dries007.tfc.objects.entity.EntitySeatOn;

public final class Helpers
{
    private static final Joiner JOINER_DOT = Joiner.on('.');

    /**
     * Makes an entity sit on a block
     *
     * @param world    the worldObj
     * @param pos      the BlockPos of the block to sit on
     * @param creature the entityLiving that will sit on this block
     * @param yOffset  the y offset of the top facing
     */
    public static void sitOnBlock(World world, BlockPos pos, EntityLiving creature, double yOffset)
    {
        if (!world.isRemote && !world.getBlockState(pos).getMaterial().isReplaceable())
        {
            EntitySeatOn seat = new EntitySeatOn(world, pos, yOffset);
            world.spawnEntity(seat);
            creature.startRiding(seat);
        }
    }

    /**
     * Makes a player entity sit on a block
     *
     * @param world    the worldObj
     * @param pos      the BlockPos of the block to sit on
     * @param player   the EntityPlayer that will sit on this block
     * @param yOffset  the y offset of the top facing
     */
    public static void sitOnBlock(World world, BlockPos pos, EntityPlayer player, double yOffset)
    {
        if (!world.isRemote && !world.getBlockState(pos).getMaterial().isReplaceable())
        {
            EntitySeatOn seat = new EntitySeatOn(world, pos, yOffset);
            world.spawnEntity(seat);
            player.startRiding(seat);
        }
    }
}
