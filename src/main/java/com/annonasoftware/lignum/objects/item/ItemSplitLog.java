package com.annonasoftware.lignum.objects.item;

/* 
    This file is a part of Woodworking
    Copyright (c) 2020 madflavius
    Licensed under the GPL v3
*/

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.item.ItemStack;

import mcp.MethodsReturnNonnullByDefault;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.items.ItemTFC;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ItemSplitLog extends ItemTFC
{
    private static final Map<Tree, ItemSplitLog> MAP = new HashMap<>();

    public static ItemSplitLog get(Tree wood)
    {
        return MAP.get(wood);
    }

    public static ItemStack get(Tree wood, int amount)
    {
        return new ItemStack(MAP.get(wood), amount);
    }

    public final Tree wood;

    public ItemSplitLog(Tree wood)
    {
        this.wood = wood;
        if (MAP.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
        setMaxDamage(0);
        //OreDictionaryHelper.register(this, "lumber");
        //noinspection ConstantConditions
        //OreDictionaryHelper.register(this, "lumber", wood.getRegistryName().getPath());
    }

    @Nonnull
    @Override
    public Size getSize(ItemStack stack)
    {
        return Size.SMALL;
    }

    @Nonnull
    @Override
    public Weight getWeight(ItemStack stack)
    {
        return Weight.LIGHT;
    }
}