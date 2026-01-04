package com.sirenensemble.sculktechnologies.sound;

import com.sirenensemble.sculktechnologies.SculkTechnologies;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.level.block.JukeboxBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, SculkTechnologies.MODID);

    public static final Supplier<SoundEvent> SOUL_SEEKER_USE = registerSoundEvent("soul_seeker_use");

    public static final Supplier<SoundEvent> SIREN_SONG = registerSoundEvent("siren_song");
    public static final ResourceKey<JukeboxSong> SIREN_SONG_KEY = createSong("siren_song");


private static ResourceKey<JukeboxSong> createSong (String name){
    return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, name));
}


private static Supplier<SoundEvent> registerSoundEvent(String name){
    ResourceLocation id = ResourceLocation.fromNamespaceAndPath(SculkTechnologies.MODID, name);
    return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
}

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
