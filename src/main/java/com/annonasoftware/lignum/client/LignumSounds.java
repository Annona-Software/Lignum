package com.annonasoftware.lignum.client;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import net.dries007.tfc.util.Helpers;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.annonasoftware.lignum.Lignum.MOD_ID;

public class LignumSounds
{
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MOD_ID);

    private static RegistryObject<SoundEvent> create(String name)
    {
        return SOUNDS.register(name, () -> new SoundEvent(Helpers.identifier(name)));
    }

    //Blocks
    public static final RegistryObject<SoundEvent> LOG_SPLIT = create("block.chopping_block.split");

    private static Optional<Supplier<SoundEvent>> createOptional(String name, boolean present)
    {
        return Optional.ofNullable(present ? create(name) : null);
    }
}
