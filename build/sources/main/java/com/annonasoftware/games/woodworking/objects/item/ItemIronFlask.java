package com.annonasoftware.games.woodworking.objects.item;


import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.minecraft.item.ItemStack;
import com.annonasoftware.games.woodworking.ConfigFlasks;

import javax.annotation.Nonnull;

public class ItemIronFlask extends ItemFlask {

    protected static int CAPACITY = ConfigFlasks.GENERAL.ironCap;
    protected static int DRINK = 100; //matches amount of water in TFC Jug

    public ItemIronFlask() {
        super("iron_flask", CAPACITY, DRINK);
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack stack)
    {
        return Size.NORMAL;
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack stack)
    {
        return Weight.HEAVY;
    }

}
