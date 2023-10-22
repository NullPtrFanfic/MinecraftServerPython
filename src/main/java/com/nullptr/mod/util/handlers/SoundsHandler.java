package com.nullptr.mod.util.handlers;

import com.nullptr.mod.Main;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler {
    public static void playSound(SoundEvent sound) {
        try {
            if (Minecraft.getMinecraft().world.isRemote) {
                EntityPlayerSP player = Minecraft.getMinecraft().player;
                Minecraft.getMinecraft().world.playSound(player, player.getPosition(), sound,
                        SoundCategory.PLAYERS, RandomGenerator.getNextRandomVolumeLoud(), 1.0F);
            }
        } catch (Exception ex) {
            // Обработка ошибки
        }
    }
}
