package com.nullptr.mod.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.common.util.Lazy;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
//import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraft.util.SoundCategory;
public class SoundsUtil {
    public static final RegistryObject<SoundEvent> SOUND_1 = registerSound("sound1");
    public static final RegistryObject<SoundEvent> SOUND_2 = registerSound("sound2");
    public static final RegistryObject<SoundEvent> SOUND_3 = registerSound("sound3");

    private static RegistryObject<SoundEvent> registerSound(String soundId) {
        ResourceLocation soundLocation = new ResourceLocation("mod", soundId);
        return ForgeRegistry.SOUND_EVENTS.register(soundId, () -> new SoundEvent(soundLocation));
    }

    public static void playSound(SoundEvent sound) {
        try {
            if (Minecraft.getMinecraft().world.isRemote) {
                EntityPlayerSP player = Minecraft.getMinecraft().player;
                Minecraft.getMinecraft().world.playSound(player, player.getPosition(), sound,
                        SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
        } catch (Exception ex) {
            // Обработка ошибки
        }
    }
}
