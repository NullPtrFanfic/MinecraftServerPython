package com.nullptr.mod.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.Minecraft;
public class SoundUtil {
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
