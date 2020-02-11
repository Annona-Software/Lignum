package com.annonasoftware.games.woodworking.objects.te;

/* 
    This file is a part of Woodworking
    Copyright (c) 2020 madflavius
    Licensed under the GPL v3

    This class taken lock, stock, and
    barrel from TFC's TEBloom.java. Thanks, TFC team!
*/

import javax.annotation.ParametersAreNonnullByDefault;

import net.dries007.tfc.objects.te.TEInventory;
import net.minecraft.item.ItemStack;

import mcp.MethodsReturnNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TEChoppingBlock extends TEInventory
{
    public TEChoppingBlock()
    {
        super(1);
    }

    public void setLog(ItemStack stack)
    {
        inventory.setStackInSlot(0, stack);
    }

    public void setEmpty()
    {
        inventory.setStackInSlot(0, ItemStack.EMPTY);
    }
}