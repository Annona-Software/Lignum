package com.annonasoftware.lignum.common.items;


import com.annonasoftware.lignum.Lignum;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Helpers;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

import static net.dries007.tfc.common.TFCItemGroup.WOOD;

@SuppressWarnings("unused")
public class LignumItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Lignum.MOD_ID);

    //public static final Map<Wood, RegistryObject<Item>> BOATS = Helpers.mapOfKeys(Wood.class, wood -> register("wood/table/" + wood.name(), () -> new TFCBoatItem(TFCEntities.BOATS.get(wood), new Item.Properties().tab(WOOD))));

    public static final RegistryObject<Item> SMILE = ITEMS.register("smile",
            () -> new Item(new Item.Properties()));
}
