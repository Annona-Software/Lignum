package com.annonasoftware.games.woodworking.client;

/*
 *  Copyright (c) 2020 madflavius under the terms of GPL v3 
 *  Most of the tree/wood logic taken from net.dries007.tfc.objects.blocks.wood.BlockLogTFC.java.
 *   Thank you to the TFC TNG team!
 */

import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.annonasoftware.games.woodworking.Woodworking.MOD_ID;
import static net.dries007.tfc.util.Helpers.getNull;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class WoodworkingSounds
{
    @GameRegistry.ObjectHolder(MOD_ID + ":wood.log.split")
    public static final SoundEvent WOOD_LOG_SPLIT = getNull();

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event)
    {
        event.getRegistry().registerAll(
            
            // Misc
            createSoundEvent("wood.log.split")
        );
    }

    private static SoundEvent createSoundEvent(String name)
    {
        final ResourceLocation soundID = new ResourceLocation(MOD_ID, name);
        return new SoundEvent(soundID).setRegistryName(soundID);
    }
}