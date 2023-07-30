package com.nullptr.mod;

import com.nullptr.mod.proxy.CommonProxy;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main
{
    public static final String MODID = "mod";
    public static final String NAME = "NullPtr Mod";
    public static final String VERSION = "0.1";
    public static final String CLIENT_PROXY_CLASS = "";
    public static final String COMMON_PROXY_CLASS = "";

    @Instance
    public static Main instance;
    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = COMMON_PROXY_CLASS)
    public static CommonProxy proxy;
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
