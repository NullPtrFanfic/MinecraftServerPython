package ml.denis3d.minecraft2discord.events;


import com.nullptr.mod.discord.Utils;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DiscordEvents extends ListenerAdapter
{
    @Override
    public void onReady(ReadyEvent event)
    {
        System.out.println("Discord bot logged as " + event.getJDA().getSelfUser().getName());

        Utils.global_variables.put("online_players", () -> String.valueOf(ServerLifecycleHooks.getCurrentServer().getCurrentPlayerCount()));
        Utils.global_variables.put("max_players", () -> String.valueOf(ServerLifecycleHooks.getCurrentServer().getMaxPlayers()));
        Utils.global_variables.put("unique_player", () -> String.valueOf(Optional.ofNullable(ServerLifecycleHooks.getCurrentServer().getWorld(DimensionType.OVERWORLD).getSaveHandler().getPlayerFolder().list()).map(list -> list.length).orElse(0)));
        Utils.global_variables.put("motd", () -> ServerLifecycleHooks.getCurrentServer().getMOTD());
        Utils.global_variables.put("mc_version", () -> ServerLifecycleHooks.getCurrentServer().getMinecraftVersion());
        Utils.global_variables.put("server_hostname", () -> ServerLifecycleHooks.getCurrentServer().getServerHostname());
        Utils.global_variables.put("server_port", () -> String.valueOf(ServerLifecycleHooks.getCurrentServer().getServerPort()));
        Utils.global_variables.put("date", () -> DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()));
        Utils.global_variables.put("time", () -> DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
        Utils.global_variables.put("uptime", () -> Utils.uptimeDateFormater.format(new Date().getTime() - Utils.started_time));

       // if (Config.SERVER.sendServerStartStopMessages.get())
        Utils.sendInfoMessage("Server started!");
    }
}
