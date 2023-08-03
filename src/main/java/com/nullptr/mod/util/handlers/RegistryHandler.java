package com.nullptr.mod.util.handlers;

import com.nullptr.mod.init.ItemInit;
import com.nullptr.mod.init.BlockInit;
import com.nullptr.mod.Main;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import com.nullptr.mod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.util.EnumHelper;
import com.nullptr.mod.recipes.SmeltingRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.nullptr.mod.init.EntityInit;
import com.nullptr.mod.util.handlers.RenderHandler;
import com.nullptr.mod.util.handlers.SoundsHandler;

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
    public static void preInitRegistries(FMLPreInitializationEvent event)
    {
		//GameRegistry.registerWorldGenerator(new WorldGenOres(), 3);
		EntityInit.registerEntities();
		//EventHandler.registerEvents();
	        SoundsHandler.registerSounds();
		RenderHandler.registerEntityRenders();
		//ConfigHandler.registerConfig(event);
    }
	
    public static void initRegistries()
    {
		//NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
		SmeltingRecipes.init();
		EnumHelper.addArt("Test", "Test", 16, 16, 112, 0);
    }
	
    public static void postInitRegistries()
    {
		//@SuppressWarnings("unused")
		//WorldType TEST_TYPE = new WorldTypeTest("test");
    }
    // Здесь могут быть другие методы, связанные с регистрацией предметов, блоков и других объектов
}
