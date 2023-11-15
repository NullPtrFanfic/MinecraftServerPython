package com.nullptr.mod.discord.events;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
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
@Mod.EventBusSubscriber(Side.CLIENT)
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
                message += event.getAdvancement().getDisplay().getDescription().getUnformattedComponentText();

                Utils.sendInfoMessage(message);
           }
       }
    }

    @SubscribeEvent
    public static void onServerChat(ClientChatEvent event)
    {
        if (Utils.chatChannel == null) Utils.chatChannel = Minecraft2Discord.getDiscordBot().getTextChannelById("1097828057018015836");
        if (Utils.chatChannel != null)
        {
            List<Webhook> discordWebhooks = Utils.chatChannel.retrieveWebhooks().complete().stream().filter(webhook -> webhook.getName().startsWith("Minecraft2Discord")).collect(Collectors.toList());
            if (discordWebhooks.size() == 0)
            {
                Utils.discordWebhook = Utils.chatChannel.createWebhook("Minecraft2Discord").complete();
            } else
            {
                Utils.discordWebhook = discordWebhooks.get(0);
            }
            discordWebhookClient = WebhookClient.withUrl(Utils.discordWebhook.getUrl());
            WebhookMessageBuilder builder = new WebhookMessageBuilder();
            builder.setContent(event.getMessage())
                .setUsername("sumeru")
                .setAvatarUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzb7brumDODi9RhjQwxqILPKJKXK7UuLN2zXUbOAYMcurRF0RMV6Rxv7Fppa3K3gRv5Ek&usqp=CAU");
            discordWebhookClient.send(builder.build());
            client.send("Hello World"); // Send and log (using embed) WebhookEmbed embed = new WebhookEmbedBuilder() .setColor(0xFF00EE) .setDescription("Hello World") .build(); client.send(embed) .thenAccept((message) -> System.out.printf("Message with embed has been sent [%s]%n", message.getId())); // Change appearance of webhook message WebhookMessageBuilder builder = new WebhookMessageBuilder(); builder.setUsername("Minn"); // use this username builder.setAvatarUrl(avatarUrl); // use this avatar builder.setContent("Hello World"); client.send(builder.build());
        }
     }
}
