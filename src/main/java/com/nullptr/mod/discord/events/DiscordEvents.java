package com.nullptr.mod.discord.events;

import com.nullptr.mod.discord.events.ServerEvents;
//import net.dv8tion.jda.api.JDA;
//import net.dv8tion.jda.api.entities.Webhook;
//import club.minnced.discord.webhook.external.JDAWebhookClient;
import com.nullptr.mod.discord.Utils;
//import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
//import net.dv8tion.jda.api.utils.messages.MessageCreateData;
//import club.minnced.discord.webhook.WebhookClient;
import net.dv8tion.jda.api.events.session.ReadyEvent;
//import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
//import net.minecraft.command.CommandSource;
//import net.minecraft.command.Commands;
//import club.minnced.discord.webhook.external.JDAWebhookClient;

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
        //Utils.chatChannel = Minecraft2Discord.getDiscordBot().getTextChannelById("1097828057018015836");
    }
}
