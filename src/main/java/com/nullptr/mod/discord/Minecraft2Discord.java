package com.nullptr.mod.discord;

import com.nullptr.mod.discord.events.DiscordEvents;
import com.nullptr.mod.discord.Utils;
import com.nullptr.mod.discord.events.ServerEvents;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
//import net.minecraftforge.fml.network.FMLNetworkConstants;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.LoginException;
import java.util.Date;

// The value here should match an entry in the META-INF/mods.toml file
//@Mod(value = "minecraft2discord")
public class Minecraft2Discord {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    private static JDA DISCORD_BOT = null;

    public static JDA getDiscordBot() {
        return DISCORD_BOT;
    }

    public void onServerReady(FMLServerStartedEvent event)
    {
        Utils.started_time = new Date().getTime();

        try
        {
            DISCORD_BOT = new JDABuilder("MTE2ODIxMjg0NDI1MzI4MjQxNg.GHLt-S.prUaAEf0TkBSBdkdSdb65u6zisXFrIVc80CPNM").addEventListeners(new DiscordEvents()).build();
        } catch (LoginException e)
        {
            LOGGER.error(e.getMessage());
        }
    }

    public void onServerStarting(FMLServerStartingEvent event)
    {
        
    }

    public void onServerStop(FMLServerStoppingEvent event)
    {
            if (getDiscordBot() == null) {
                return;
            }

            Utils.sendInfoMessage("Server down!");
    }

    public void onServerStopped(FMLServerStoppedEvent event)
    {
       // Utils.updateOfflineVoiceChannel();
       // Utils.updateOfflineChannelTopic();
        ServerEvents.discordWebhookClient.close();
        DISCORD_BOT.shutdown();
        OkHttpClient client = DISCORD_BOT.getHttpClient();
        client.connectionPool().evictAll();
        client.dispatcher().executorService().shutdown();
    }
}
