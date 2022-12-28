package com.annonasoftware.lignum.common;

import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;

import static com.annonasoftware.lignum.Lignum.MOD_ID;

public class LignumHelpers
{

    public static Component blockEntityName(String name)
    {
        return new TranslatableComponent(MOD_ID + ".block_entity." + name);
    }
}
