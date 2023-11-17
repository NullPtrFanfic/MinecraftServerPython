package com.nullptr.mod.discord.events;

//import okhttp3.OkHttpClient;
//import com.mojang.brigadier.exceptions.CommandSyntaxException;
//import com.nullptr.mod.discord.Config;
import com.nullptr.mod.discord.Minecraft2Discord;
import com.nullptr.mod.discord.Utils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Webhook;
import net.minecraftforge.fml.relauncher.Side;
//import net.minecraft.command.arguments.MessageArgument;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.CommandEvent;
//import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
//import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;//i)mport net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.event.server.ServerLifecycleEvent;
import net.minecraftforge.client.event.ClientChatEvent;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ServerChatEvent;
@Mod.EventBusSubscriber
public class ServerEvents
{
    public static WebhookClient discordWebhookClient;

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event)
    {
        if (Minecraft2Discord.getDiscordBot() != null && Minecraft2Discord.getDiscordBot().getStatus() == JDA.Status.CONNECTED) { 
            Utils.sendInfoMessage(event.player.getName().toString()+" зашел на сервер!");
        }
    }

    @SubscribeEvent
    public static void onPlayerLeft(PlayerEvent.PlayerLoggedOutEvent event)
    {
        if (Minecraft2Discord.getDiscordBot() != null && Minecraft2Discord.getDiscordBot().getStatus() == JDA.Status.CONNECTED) { 
            Utils.sendInfoMessage(event.player.getName().toString()+" вышел с сервера");
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event)
    {
        if (Minecraft2Discord.getDiscordBot() != null && Minecraft2Discord.getDiscordBot().getStatus() == JDA.Status.CONNECTED) { 
            if (event.getEntityLiving() instanceof EntityPlayer)
            {
                EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
                Utils.sendInfoMessage("Кто-то сдох"+player.getCombatTracker().getDeathMessage().toString() + player.getName().toString()+player.getCombatTracker().getDeathMessage().toString()+"death.attack." + event.getSource().damageType);
        
            }
        }
    }

    @SubscribeEvent
    public static void onAdvancement(AdvancementEvent event)
    {
       if (Minecraft2Discord.getDiscordBot() != null && Minecraft2Discord.getDiscordBot().getStatus() == JDA.Status.CONNECTED) {  
           if (event.getEntityLiving() instanceof EntityPlayer && event.getAdvancement().getDisplay() != null)
           {
                EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
                String message = "Кто-то получил ачивку"+player.getName().toString()+event.getAdvancement().getDisplayText().toString();
                //message += event.getAdvancement().getDisplay().getDescription().getUnformattedComponentText();

                Utils.sendInfoMessage(message);
           }
       }
    }

    @SubscribeEvent
    public static void onServerChat(ServerChatEvent event)
    {
        Utils.sendInfoMessage();
     }
}
