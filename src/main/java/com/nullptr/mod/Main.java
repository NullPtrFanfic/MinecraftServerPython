package com.nullptr.mod;

import com.nullptr.mod.proxy.CommonProxy;
import com.nullptr.mod.util.handlers.RegistryHandler;
import com.nullptr.mod.tabs.Tab;
import com.nullptr.mod.recipes.SmeltingRecipes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import com.nullptr.mod.commands.CommandDimensionTeleport;
import com.nullptr.mod.discord.Minecraft2Discord;
@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main
{
    public static final String MODID = "mod";
    public static final String NAME = "NullPtr Mod";
    public static final String VERSION = "0.1";
    public static final String CLIENT_PROXY_CLASS = "com.nullptr.mod.proxy.ClientProxy";
    public static final String COMMON_PROXY_CLASS = "com.nullptr.mod.proxy.CommonProxy";
    public static final int ENTITY_TEST = 250;
    public static final int ENTITY_CENTAUR = 120;
    public static final int ENTITY_PYTHON = 25;
    public static final int ENTITY_JENNY = 50;
    @Instance
    public static Main instance;
    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = COMMON_PROXY_CLASS)
    public static CommonProxy proxy;
    public static final CreativeTabs MODTAB = new Tab("modtab");
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
       RegistryHandler.preInitRegistries(event);
       proxy.preInit(event);
       proxy.addOBJLoaderDomainIfOnClient();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
       RegistryHandler.initRegistries();
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
       RegistryHandler.postInitRegistries();
    }
    @EventHandler
    public void onServerReady(FMLServerStartingEvent event)
    {
           event.registerServerCommand(new CommandDimensionTeleport());
           Minecraft2Discord.onServerReady();
    }
    @EventHandler
    public void onServerStopping(FMLServerStoppingEvent event)
    {
           Minecraft2Discord.onServerStop();
    }
    @EventHandler
    public void onServerStop(FMLServerStoppedEvent event)
    {
           Minecraft2Discord.onServerStopped();
    }
}
