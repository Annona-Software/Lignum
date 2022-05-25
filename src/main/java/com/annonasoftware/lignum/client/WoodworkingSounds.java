package com.annonasoftware.lignum.client;

/*
 *  Copyright (c) 2020 madflavius under the terms of GPL v3 
 *  Most of the tree/wood logic taken from net.dries007.tfc.objects.blocks.wood.BlockLogTFC.java.
 *   Thank you to the TFC TNG team!
 */

import com.annonasoftware.lignum.Woodworking;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static net.dries007.tfc.util.Helpers.getNull;

@Mod.EventBusSubscriber(modid = Woodworking.MOD_ID)
public class WoodworkingSounds
{
    @GameRegistry.ObjectHolder(Woodworking.MOD_ID + ":wood.log.split")
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
        final ResourceLocation soundID = new ResourceLocation(Woodworking.MOD_ID, name);
        return new SoundEvent(soundID).setRegistryName(soundID);
    }
}