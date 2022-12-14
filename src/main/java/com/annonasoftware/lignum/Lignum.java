package com.annonasoftware.lignum;

/*
    Copyright (c) 2022 Annona Software (madflavius)
    Licensed under the GPL v3.

    This file is part of Lignum.

    Lignum is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Lignum is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Lignum.  If not, see <https://www.gnu.org/licenses/>.
*/

import com.annonasoftware.lignum.common.items.LignumItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

@Mod(Lignum.MOD_ID)
public class Lignum {

    public static final String MOD_ID = "lignum";
    public static final Logger LOGGER = LogUtils.getLogger();

    /**
     * Many thanks to Gaelmare's mod "WaterFlasks" and eerussianguy's "Firmalife"
     * Fingerprints from both remain...
     */

    public Lignum()
    {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        LignumItems.ITEMS.register(bus);
        //LignumBlocks.BLOCKS.register(bus);
        //LignumBlockEntities.BLOCK_ENTITIES.register(bus);
        //LignumRecipeTypes.RECIPE_TYPES.register(bus);
        //LignumRecipeSerializers.RECIPE_SERIALIZERS.register(bus);

        //LignumPackets.init();

        bus.addListener(this::setup);

        //FLForgeEvents.init();
        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            //LignumClientEvents.init();
            //LignumClientForgeEvents.init();
        }
    }

    public void setup(FMLCommonSetupEvent event)
    {
        // Vanilla registries are not thread safe
        event.enqueueWork(() -> {
            //FLInteractionManager.init();
            //FLFoodTraits.init();
            //FLIngredients.init();
        });
    }


}
