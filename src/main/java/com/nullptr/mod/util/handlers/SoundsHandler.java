package com.nullptr.mod.util.handlers;

import com.nullptr.mod.util.SoundsUtil;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//mport net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class SoundsHandler {
    @SubscribeEvent
    public void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        IForgeRegistry<SoundEvent> registry = event.getRegistry();
        registry.registerAll(SoundsUtil.SOUND_1.get(), SoundsUtil.SOUND_2.get(), SoundsUtil.SOUND_3.get());
    }
}
