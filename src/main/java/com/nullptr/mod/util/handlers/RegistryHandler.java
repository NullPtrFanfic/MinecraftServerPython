package com.nullptr.mod.util.handlers;

import com.nullptr.mod.init.ItemInit;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.nullptr.mod.util.interfaces.IHasModel;

@EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ItemInit.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel)item).registerModels();
            }
        }
        //For Loop for Blocks in RegistryHandler.java
        for(Block block : BlockInit.BLOCKS)
	{
		if(block instanceof IHasModel)
		{
			((IHasModel)block).registerModels();
		}
	}
    }
//onBlockRegister method in RegistryHandler.java
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
	   event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
    }

    // Здесь могут быть другие методы, связанные с регистрацией предметов, блоков и других объектов
}
