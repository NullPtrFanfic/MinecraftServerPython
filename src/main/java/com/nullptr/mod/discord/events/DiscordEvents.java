package com.nullptr.mod.discord.events;


import com.nullptr.mod.discord.Utils;
import club.minnced.discord.webhook.WebhookClient;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
//import net.minecraft.command.CommandSource;
//import net.minecraft.command.Commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DiscordEvents extends ListenerAdapter
{
    @Override
    public void onReady(ReadyEvent event)
    {
        System.out.println("Discord bot logged as " + event.getJDA().getSelfUser().getName());
       // if (Config.SERVER.sendServerStartStopMessages.get())
        Utils.sendInfoMessage("Server started!");
    }
}
