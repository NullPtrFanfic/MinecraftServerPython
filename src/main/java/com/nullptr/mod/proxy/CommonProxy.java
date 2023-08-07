package com.nullptr.mod.proxy;

import com.nullptr.mod.entity.weirdzombie.EntityInit;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import com.nullptr.mod.core.info.CreatureInfo;
//import com.nullptr.mod.core.info.ModInfo;
//import com.nullptr.mod.core.info.Subspecies;

@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
         EntityInit.init();
    }
    public void registerItemRenderer(Item item, int meta, String id) {
        // Здесь ваш код
    }
    public void addOBJLoaderDomainIfOnClient() {
    }
    public void init() {
    }
    public void registerRenders() {}
    public void registerTextures() {}
    public void registerModels() {}
    public void getResponse() {}
    public void sendLongMessage() {}

    //public EntityPlayer getClientPlayer() { return null; }

    public void loadCreatureModel(String modelClassName) throws ClassNotFoundException {}
    public void loadSubspeciesModel(String modelClassName) throws ClassNotFoundException {}
}
