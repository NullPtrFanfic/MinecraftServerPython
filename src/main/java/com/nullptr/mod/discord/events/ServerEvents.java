package com.nullptr.mod.discord.events;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
//import com.nullptr.mod.discord.Config;
import com.nullptr.mod.discord.Minecraft2Discord;
import com.nullptr.mod.discord.Utils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Webhook;
import net.minecraft.command.arguments.MessageArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.ServerLifecycleEvent;

import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(Dist.DEDICATED_SERVER)
public class ServerEvents
{
    public static WebhookClient discordWebhookClient;

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event)
    {
        Utils.sendInfoMessage(event.getPlayer().getName().getFormattedText()+" зашел на сервер!");
    }

    @SubscribeEvent
    public static void onPlayerLeft(PlayerEvent.PlayerLoggedOutEvent event)
    {
        if (Minecraft2Discord.getDiscordBot() != null && Minecraft2Discord.getDiscordBot().getStatus() == JDA.Status.CONNECTED) { 
            Utils.sendInfoMessage(event.getPlayer().getName().getFormattedText()+" вышел из сервера");
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event)
    {
        InterModComms.getMessages("minecraft2discord").forEach(imcMessage ->
        {
                if (imcMessage.getMethod().equals("info_channel"))
                    Utils.sendInfoMessage(imcMessage.getMessageSupplier().get().toString());

                if (imcMessage.getMethod().equals("chat_channel"))
                    Utils.sendChatMessage(imcMessage.getMessageSupplier().get().toString());

                if (imcMessage.getMethod().matches("\\d+"))
                    Utils.sendMessage(Minecraft2Discord.getDiscordBot().getTextChannelById(imcMessage.getMethod()),
                        imcMessage.getMessageSupplier().get().toString());
        });
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event)
    {
        if (event.getEntityLiving() instanceof PlayerEntity)
        {
                PlayerEntity player = (PlayerEntity) event.getEntityLiving();
                Utils.sendInfoMessage("Кто-то сдох"+player.getCombatTracker().getDeathMessage().getFormattedText() + player.getName().getFormattedText()+player.getCombatTracker().getDeathMessage().getUnformattedComponentText()+"death.attack." + event.getSource().damageType)
                );
        }
    }

    @SubscribeEvent
    public static void onAdvancement(AdvancementEvent event)
    {
        if (event.getEntityLiving() instanceof PlayerEntity && event.getAdvancement().getDisplay() != null)
        {
                PlayerEntity player = (PlayerEntity) event.getEntityLiving();
                String message = "Кто-то получил ачивку"+player.getName().getFormattedText()+event.getAdvancement().getDisplayText().getString();
                message += event.getAdvancement().getDisplay().getDescription().getUnformattedComponentText();

                Utils.sendInfoMessage(message);
        }
    }

    @SubscribeEvent
    public static void onCommand(CommandEvent event) throws CommandSyntaxException
    {
        if (event.getParseResults().getContext().getCommand() != null && event.getParseResults().getContext().getNodes().get(0).getNode().getName().equals("say"))
        {
            Utils.sendInfoMessage(Config.SERVER.noneWebhookChatMessageFormat.get()
                .replace("$1", event.getParseResults().getContext().getSource().getDisplayName().getFormattedText())
                .replace("$2", ((MessageArgument.Message) event.getParseResults().getContext().getArguments().get("message").getResult())
                    .toComponent(event.getParseResults().getContext().getSource(), true).getFormattedText()));
        }
    }

    @SubscribeEvent
    public static void onServerChat(final ServerChatEvent event)
    {
        if (true)
        {
            if (discordWebhookClient == null)
            {
                if (Utils.discordWebhook == null)
                {
                    if (Utils.chatChannel == null)
                        Utils.chatChannel = Minecraft2Discord.getDiscordBot().getTextChannelById("");
                    if (Utils.chatChannel != null)
                    {
                        List<Webhook> discordWebhooks = Utils.chatChannel.retrieveWebhooks().complete().stream()
                            .filter(webhook -> webhook.getName().startsWith("Minecraft2Discord")).collect(Collectors.toList());
                        if (discordWebhooks.size() == 0)
                        {
                            Utils.discordWebhook = Utils.chatChannel.createWebhook("Minecraft2Discord").complete();
                        } else
                        {
                            Utils.discordWebhook = discordWebhooks.get(0);
                        }
                    }
                }
                discordWebhookClient = WebhookClient.withUrl(Utils.discordWebhook.getUrl());
            }
            WebhookMessageBuilder builder = new WebhookMessageBuilder();
            builder.setContent(event.getMessage())
                .setUsername(event.getUsername())
                .setAvatarUrl(Utils.globalVariableReplacement(Config.SERVER.discordPictureAPI.get())
            event.getUsername())
                    .replace("$2", event.getPlayer().getUniqueID().toString()));
            discordWebhookClient.send(builder.build());
        } else
        {
            Utils.sendChatMessage(Utils.globalVariableReplacement("Чета с вебхуком не так" + event.getUsername() + event.getMessage()));
        }
    }
}
