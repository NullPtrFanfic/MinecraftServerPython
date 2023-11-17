package com.nullptr.mod.discord;

import net.dv8tion.jda.core.entities.*;
import com.nullptr.mod.discord.Minecraft2Discord;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class Utils
{
    public static TextChannel chatChannel;
    public static TextChannel infoChannel;
    public static TextChannel editableTopicChannel;
    public static VoiceChannel editableVoiceChannel;

    public static Webhook discordWebhook;

    public static Map<String, Callable<String>> global_variables = new HashMap<>();
    public static long started_time;

    public static SimpleDateFormat uptimeDateFormater = new SimpleDateFormat("HH:mm");


    public static boolean sendChatMessage(String message)
    {
        if (true)
        {
            if (chatChannel == null) {
                chatChannel = Minecraft2Discord.getDiscordBot().getTextChannelById("1097828057018015836");
            }
            return sendMessage(chatChannel, message);
        }
        return false;
    }

    public static boolean sendInfoMessage(String message)
    {
        if (true)
        {
            if (infoChannel == null) {
                infoChannel = Minecraft2Discord.getDiscordBot().getTextChannelById("1097828057018015836");
            }
            return sendMessage(infoChannel, message);
        }
        return false;
    }

    public static boolean sendMessage(TextChannel channel, String message)
    {
        return sendMessage(channel, message, true);
    }

    public static boolean sendMessage(TextChannel channel, String message, Boolean global_variable_replacement)
    {
        if (Minecraft2Discord.getDiscordBot() == null || channel == null) {
            return false;
        }

        try
        {
            channel.sendMessage(message).submit();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }



    public static boolean isM2DBotOrWebhook(User author, Webhook webhook)
    {
        if (author.getIdLong() == Minecraft2Discord.getDiscordBot().getSelfUser().getIdLong())
        {
            return true;
        } else return webhook != null && author.getIdLong() == webhook.getIdLong();
    }
}
