package com.nullptr.astgrief;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		log("Loading AstGrief plugin..");
	        SERVER = Bukkit.getServer()
                WORLD = SERVER.getWorlds().get(0)
                PLUGIN = SERVER.getPluginManager().getPlugin('astgrief')
	}
	
	public void log(String message) {
		getLogger().info(message);
	}
}
