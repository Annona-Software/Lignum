package com.annonasoftware.games.woodworking;

/*
    Copyright (c) 2020 madflavius
    Licensed under the GPL v3.

    This file is part of Woodworking.

    Woodworking is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Woodworking is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Woodworking.  If not, see <https://www.gnu.org/licenses/>.
*/

import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.recipes.knapping.KnappingRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.annonasoftware.games.woodworking.objects.item.ItemsWoodworking;
import com.annonasoftware.games.woodworking.objects.block.BlocksWoodworking;
import com.annonasoftware.games.woodworking.proxy.CommonProxy;
import com.annonasoftware.games.woodworking.recipe.ModRecipes;

@Mod(
        modid = Woodworking.MOD_ID,
        name = Woodworking.MOD_NAME,
        version = Woodworking.VERSION,
        dependencies = Woodworking.DEPENDENCIES
)
public class Woodworking {

    public static final String MOD_ID = "woodworking";
    public static final String MOD_NAME = "Woodworking";
    public static final String VERSION = "0.0.1";
    public static final String DEPENDENCIES = "required-after:tfc";

    /**
     * Many thanks to Shadowfacts's and Cubicoder's 1.12.2 modding tutorials,
     * and Gaelmare's mod "WaterFlasks" for putting these lessons into practice.
     * Fingerprints from all three remain...
     */

    @Mod.Instance(MOD_ID)
    public static Woodworking INSTANCE;

    @SidedProxy(serverSide = "com.annonasoftware.games.woodworking.proxy.CommonProxy",
            clientSide = "com.annonasoftware.games.woodworking.proxy.ClientProxy")
    public static CommonProxy proxy;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        System.out.println(MOD_NAME + " is loading");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // register loot function for use when we add bladder loot pools to TFC animals
        LootFunctionManager.registerFunction(new ApplyRequiredSkill.Serializer(new ResourceLocation(MOD_ID, "apply_req_skill")));
    }

    // @Mod.EventBusSubscriber
    // public static class ObjectRegistryHandler {
    //     /**
    //      * Listen for the register event for creating custom items
    //      */
    //     @SubscribeEvent
    //     public static void registerItems(RegistryEvent.Register<Item> event) {
    //         ItemsWoodworking.register(event.getRegistry());
    //         BlocksWoodworking.registerItemBlocks(event.getRegistry());
    //     }

        // /**
        //  * Listen for the register event for creating custom blocks
        //  */
        // @SubscribeEvent
        // public static void registerBlocks(RegistryEvent.Register<Block> event) {
        //     BlocksWoodworking.register(event.getRegistry());
        // }

        /**
         * Listen for the register event for models
         */
        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            ItemsWoodworking.registerModels();
            //BlocksWoodworking.registerModels();
        }

        /**
         * Register Knapping Recipe
         */
        @SubscribeEvent
        public static void onRegisterKnappingRecipeEvent(RegistryEvent.Register<KnappingRecipe> event) {
            ModRecipes.registerKnapping(event);
        }

        /**
         * Register Anvil Recipe
         */
        @SubscribeEvent
        public static void onRegisterAnvilRecipeEvent(RegistryEvent.Register<AnvilRecipe> event) {
            ModRecipes.registerAnvil(event);
        }


        @SubscribeEvent
        public static void onLootTableLoad(LootTableLoadEvent event)
        {
            if (event.getName().toString().startsWith("tfc:"))
            {
                //attempt to adjust 4 tables for every table load.
                //TODO: Clean up loot table name matching
                addPool(event, "animals/cow");
                addPool(event, "animals/horse");
                addPool(event, "animals/bear");
                addPool(event, "animals/sheep");
            }
        }

        private static void addPool(LootTableLoadEvent event, String tableName)
        {
            if (("tfc:"+tableName).equals(event.getName().toString()))
            {
                LootEntry entry = new LootEntryTable(new ResourceLocation("waterflasks:"+tableName),
                        1, 0, new LootCondition[0], "waterflasks_bladder_entry");

                LootPool newPool = new LootPool(new LootEntry [] {entry}, new LootCondition[0],
                        new RandomValueRange(1), new RandomValueRange(0), "waterflasks_bladder_pool");
                //weights here seemed screwy. Implemented own skill function, applied in json data
                event.getTable().addPool(newPool);
            }
        }
    }
