package com.annonasoftware.games.woodworking.client;

/* 
    This file is a part of Woodworking
    Copyright (c) 2020 madflavius
    Licensed under the GPL v3

    Thanks to TFC, from whom I shamelessly cribbed
    the bulk of this class. EUPL <-> GPL.
*/

import com.annonasoftware.games.woodworking.objects.block.BlocksWoodworking;
import com.annonasoftware.games.woodworking.objects.item.ItemsWoodworking;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;


import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.annonasoftware.games.woodworking.Woodworking.MOD_ID;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = MOD_ID)
public final class ClientRegisterEvents
{
    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void registerModels(ModelRegistryEvent event)
    {
        // Item Blocks
        for (ItemBlock item : BlocksWoodworking.getAllNormalItemBlocks())
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "normal"));

        // Simple Items
        for (Item item : ItemsWoodworking.getAllSimpleItems())
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));
    }

}