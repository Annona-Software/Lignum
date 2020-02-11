package com.annonasoftware.games.woodworking.objects.block;

import com.annonasoftware.games.woodworking.objects.te.TEChoppingBlock;

/* 
    This file is a part of Woodworking
    Copyright (c) 2020 madflavius
    Licensed under the GPL v3

    Thanks to TFC, from whom I shamelessly cribbed
    the bulk of this class. EUPL <-> GPL.
*/

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.*;
import net.dries007.tfc.objects.items.itemblock.*;
import net.dries007.tfc.objects.te.*;

import static com.annonasoftware.games.woodworking.Woodworking.MOD_ID;
import static net.dries007.tfc.objects.CreativeTabsTFC.*;
import static net.dries007.tfc.util.Helpers.getNull;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = MOD_ID)
@GameRegistry.ObjectHolder(MOD_ID)
public final class BlocksWoodworking
{
    // All these are for use in model registration. Do not use for block lookups.
    // Use the static get methods in the classes instead.

    private static ImmutableList<ItemBlock> allNormalItemBlocks;

    // private static ImmutableList<BlockChoppingBlock> allChoppingBlocks;
    // private static ImmutableList<BlockTable> allTableBlocks;
    // private static ImmutableList<BlockStool> allStoolBlocks;

    //Public Static Getters
    public static ImmutableList<ItemBlock> getAllNormalItemBlocks()
    {
        return allNormalItemBlocks;
    }
    
    // public static ImmutableList<BlockChoppingBlock> getAllChoppingBlocks()
    // {
    //     return allChoppingBlocks;
    // }

    // public static ImmutableList<BlockTable> getAllTableBlocks()
    // {
    //     return allTableBlocks;
    // }

    // public static ImmutableList<BlockStool> getAllStoolBlocks()
    // {
    //     return allStoolBlocks;
    // }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> r = event.getRegistry();

        Builder<ItemBlock> normalItemBlocks = ImmutableList.builder();

        // Builder<BlockTable> tables = ImmutableList.builder();
        // Builder<BlockStool> stools = ImmutableList.builder();

        // Builder<BlockChoppingBlock> choppingBlocks = ImmutableList.builder();
                   
            //FURNITURE
            //All tables + item blocks
            for (Tree wood : TFCRegistries.TREES.getValuesCollection())
            normalItemBlocks.add(new ItemBlockTFC(register(r, "wood/furniture/simple_table/" + wood.getRegistryName().getPath(), new BlockTable(wood), CT_DECORATIONS)));
            //All stools + item blocks
            for (Tree wood : TFCRegistries.TREES.getValuesCollection())
            normalItemBlocks.add(new ItemBlockTFC(register(r, "wood/furniture/simple_stool/" + wood.getRegistryName().getPath(), new BlockStool(wood), CT_DECORATIONS)));
            //DEVICES
            for (Tree wood : TFCRegistries.TREES.getValuesCollection())
            normalItemBlocks.add(new ItemBlockTFC(register(r, "wood/devices/chopping_block/" + wood.getRegistryName().getPath(), new BlockChoppingBlock(wood), CT_DECORATIONS)));

            //Finally, build allNormalItemBlocks
            allNormalItemBlocks = normalItemBlocks.build();

        //Register Tile Entities
        register(TEChoppingBlock.class, "chopping_block");
	}

    //GENERIC REGISTRATION METHODS
    private static <T extends Block> T register(IForgeRegistry<Block> r, String name, T block, CreativeTabs ct)
    {
        block.setCreativeTab(ct);
        return register(r, name, block);
    }

    private static <T extends Block> T register(IForgeRegistry<Block> r, String name, T block)
    {
        block.setRegistryName(MOD_ID, name);
        block.setTranslationKey(MOD_ID + "." + name.replace('/', '.'));
        r.register(block);
        return block;
    }

    private static <T extends TileEntity> void register(Class<T> te, String name)
    {
        TileEntity.register(MOD_ID + ":" + name, te);
    }
}