package com.nullptr.mod.discord.events;

import com.nullptr.mod.discord.events.ServerEvents;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Webhook;
//import club.minnced.discord.webhook.external.JDAWebhookClient;
import com.nullptr.mod.discord.Utils;
//import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
//import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import club.minnced.discord.webhook.WebhookClient;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
//import net.minecraft.command.CommandSource;
//import net.minecraft.command.Commands;
import club.minnced.discord.webhook.external.JDAWebhookClient;
import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.WebhookCluster;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookEmbed;
import okhttp3.OkHttpClient;
//import com.mojang.brigadier.exceptions.CommandSyntaxException;
//import com.nullptr.mod.discord.Config;
import com.nullptr.mod.discord.Minecraft2Discord;
import com.nullptr.mod.discord.Utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
public class DiscordEvents extends ListenerAdapter
{
    @Override
    public void onReady(ReadyEvent event)
    {
        System.out.println("Discord bot logged as " + event.getJDA().getSelfUser().getName());
       // if (Config.SERVER.sendServerStartStopMessages.get())
        Utils.sendInfoMessage("Server started!");
        if (Utils.chatChannel == null && Minecraft2Discord.getDiscordBot() != null && Minecraft2Discord.getDiscordBot().getStatus() == JDA.Status.CONNECTED) Utils.chatChannel = Minecraft2Discord.getDiscordBot().getTextChannelById("1097828057018015836");
        if (true)
        {
            List<Webhook> discordWebhooks = Utils.chatChannel.retrieveWebhooks().complete().stream()
                            .filter(webhook -> webhook.getName().startsWith("mod")).collect(Collectors.toList());
            if (discordWebhooks.size() == 0)
            {
                            Utils.discordWebhook = Utils.chatChannel.createWebhook("mod").complete();
            } else
            {
                            Utils.discordWebhook = discordWebhooks.get(0);
            }
            // Using the builder
            WebhookClientBuilder builder = new WebhookClientBuilder(Utils.discordWebhook.getUrl()); // or id, token
            builder.setThreadFactory((job) -> {
            Thread thread = new Thread(job);
            thread.setName("Hello");
            thread.setDaemon(true);
            return thread;
            });
            builder.setWait(true);
            ServerEvents.discordWebhookClient = builder.build();
            // Using the factory methods
            // Send and forget
            ServerEvents.discordWebhookClient.send("Hello World");

// Send and log (using embed)
            WebhookEmbed embed = new WebhookEmbedBuilder()
            .setColor(0xFF00EE)
            .setDescription("Hello World")
            .build();

            ServerEvents.discordWebhookClient.send(embed)
           .thenAccept((message) -> System.out.printf("Message with embed has been sent [%s]%n", message.getId()));

// Change appearance of webhook message
            WebhookMessageBuilder builder2 = new WebhookMessageBuilder();
            builder2.setUsername("Minn"); // use this username
            builder2.setAvatarUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzb7brumDODi9RhjQwxqILPKJKXK7UuLN2zXUbOAYMcurRF0RMV6Rxv7Fppa3K3gRv5Ek&usqp=CAU"); // use this avatar
            builder2.setContent("Hello World");
            ServerEvents.discordWebhookClient.send(builder2.build());
            // Create and initialize the cluster
            //sendWebhook(ServerEvents.discordWebhookClient);
       }
    }
}
