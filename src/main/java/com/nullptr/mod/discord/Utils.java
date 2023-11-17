package com.nullptr.mod.discord;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.*;
import com.nullptr.mod.discord.Minecraft2Discord;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.WebhookCluster;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookEmbed;
import okhttp3.OkHttpClient;
import net.dv8tion.jda.api.entities.Webhook;
import java.util.stream.Collectors;
import java.io.File;
public class Utils
{
    public static boolean sendMessage(TextChannel channel, String message)
    {
        if (Minecraft2Discord.getDiscordBot() == null || channel == null) {
            return false;
        }

        try
        {
            Webhook discordWebhook = null;
            channel = Minecraft2Discord.getDiscordBot().getTextChannelById("1097828057018015836");
            List<Webhook> discordWebhooks = channel.retrieveWebhooks().complete().stream()
                            .filter(webhook -> webhook.getName().startsWith("mod")).collect(Collectors.toList());
            if (discordWebhooks.size() == 0)
            {
                            Utils.discordWebhook = Utils.channel.createWebhook("mod").complete();
            } else
            {
                            Utils.discordWebhook = discordWebhooks.get(0);
            }
            // Using the builder
            WebhookClientBuilder builder = new WebhookClientBuilder(discordWebhook.getUrl()); // or id, token
            builder.setThreadFactory((job) -> {
            Thread thread = new Thread(job);
            thread.setName("Hello");
            thread.setDaemon(true);
            return thread;
            });
            builder.setWait(true);
            discordWebhookClient = builder.build();
            // Using the factory methods
            // Send and forget
           // discordWebhookClient.send("Hello World");

// Send and log (using embed)
            WebhookEmbed embed = new WebhookEmbedBuilder()
            .setColor(0xFF00EE)
            .setDescription(message)
            .setTitle(new WebhookEmbed.EmbedTitle("Info:" ""))
            .setFooter(new WebhookEmbed.EmbedFooter("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzb7brumDODi9RhjQwxqILPKJKXK7UuLN2zXUbOAYMcurRF0RMV6Rxv7Fppa3K3gRv5Ek&usqp=CAU"))
            .build();

            discordWebhookClient.send(embed)
           .thenAccept((message) -> System.out.printf("Message with embed has been sent [%s]%n", message.getId()));

// Change appearance of webhook message
            WebhookMessageBuilder builder2 = new WebhookMessageBuilder();
            builder2.setUsername("INFO"); // use this username
            builder2.setAvatarUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzb7brumDODi9RhjQwxqILPKJKXK7UuLN2zXUbOAYMcurRF0RMV6Rxv7Fppa3K3gRv5Ek&usqp=CAU"); // use this avatar
            builder2.setContent(message);
            discordWebhookClient.send(builder2.build());
            client.send(new File("/src/main/resources/mod/assets/"));
            // Create and initialize the cluster
            //sendWebhook(ServerEvents.discordWebhookClient);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }
}
