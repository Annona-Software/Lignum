package com.annonasoftware.lignum.objects.item;

import com.annonasoftware.lignum.Woodworking;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    protected String name;

    public ItemBase(String name) {
        this.name = name;
        setTranslationKey(Woodworking.MOD_ID+"."+name);
        setRegistryName(name);

        setCreativeTab(CreativeTabs.MATERIALS);

    }

    public void registerItemModel() {
        Woodworking.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public ItemBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
    }

}
