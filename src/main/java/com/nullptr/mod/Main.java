package com.nullptr.mod;

import com.nullptr.mod.proxy.CommonProxy;
import com.nullptr.mod.util.handlers.RegistryHandler;
import com.nullptr.mod.tabs.Tab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main
{
    public static final String MODID = "mod";
    public static final String NAME = "NullPtr Mod";
    public static final String VERSION = "0.1";
    public static final String CLIENT_PROXY_CLASS = "com.nullptr.mod.proxy.ClientProxy";
    public static final String COMMON_PROXY_CLASS = "com.nullptr.mod.proxy.CommonProxy";

    @Instance
    public static Main instance;
    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = COMMON_PROXY_CLASS)
    public static CommonProxy proxy;
    public static final CreativeTabs MODTAB = new Tab("modtab");
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
    @EventHandler
    public void serverInit(FMLServerStartingEvent event)
    {

    }
}
