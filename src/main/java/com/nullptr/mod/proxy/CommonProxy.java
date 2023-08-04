package com.nullptr.mod.proxy;

import com.nullptr.mod.entity.weirdzombie.EntityWeirdZombie;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
         EntityWeirdZombie.init();
    }
    public void registerItemRenderer(Item item, int meta, String id) {
        // Здесь ваш код
    }
}
