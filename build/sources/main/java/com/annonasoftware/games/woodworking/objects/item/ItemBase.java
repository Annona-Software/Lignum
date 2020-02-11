package com.annonasoftware.games.woodworking.objects.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import com.annonasoftware.games.woodworking.Woodworking;

import static com.annonasoftware.games.woodworking.Woodworking.MOD_ID;

public class ItemBase extends Item {

    protected String name;

    public ItemBase(String name) {
        this.name = name;
        setTranslationKey(MOD_ID+"."+name);
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
