package com.nullptr.mod.party;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.nullptr.mod.party.Party;

@Mod.EventBusSubscriber
public class Minigame {

    private String name = "";
    private static Party m;
    private boolean enabled;
    private int countdown = 5;
    private int cooldownTicks = -1;

    public Minigame(String arg1, String arg2, Party arg3) {
        name = arg1;
        m = arg3;
        enabled = true;
    }

    public void getWinner() {
        for (EntityPlayer player : m.getPlayers()) {
            if (!lost.contains(player)) {
                m.win(player);
            }
        }
        lost.clear();
    }

    public void startCooldown() {
        cooldownTicks = countdown * 20;
    }

    public void startGame() {
        m.registerMinigameStart(start());
        m.setIngameStarted(true);
    }

    public Object start() {
        return null; // Implement your game logic here
    }

    public void join(EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 200, 2));
        player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 200, 2));
        player.dismountRidingEntity();
        player.setPositionAndUpdate(spawn.x, spawn.y, spawn.z);
        player.sendMessage(new TextComponentString("Now playing " + name));
        player.sendMessage(new TextComponentString(description));
    }

    public void leave(EntityPlayer player) {
        player.removePotionEffect(MobEffects.JUMP_BOOST);
        player.removePotionEffect(MobEffects.SPEED);
        player.dismountRidingEntity();
        player.setPositionAndUpdate(lobby.x, lobby.y, lobby.z);
        m.scheduleTask(() -> giveItemRewards(player, true), 15L);
    }

    public void spectate(EntityPlayer player) {
        player.dismountRidingEntity();
        player.setInvisible(true);
        player.setPositionAndUpdate(spectatorlobby.x, spectatorlobby.y, spectatorlobby.z);
    }

    public void reset(World world) {
        // Reset game state in the world
    }

    

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (cooldownTicks > 0) {
            if (cooldownTicks % 20 == 0) {
                EntityPlayer player = event.player;
                player.sendMessage(new TextComponentString("Starting in " + countdown));
                countdown--;
            }
            cooldownTicks--;

            if (cooldownTicks == 0) {
                startGame();
                cooldownTicks = -1;
                countdown = 5;
            }
        }
    }
}
