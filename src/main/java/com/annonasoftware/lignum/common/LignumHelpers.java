package com.annonasoftware.lignum.common;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import static com.annonasoftware.lignum.Lignum.MOD_ID;

public class LignumHelpers
{

    public static Component blockEntityName(String name)
    {
        return new TranslatableComponent(MOD_ID + ".block_entity." + name);
    }
}
