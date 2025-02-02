package com.nullptr.mod.proxy;

import com.nullptr.mod.Main;
import com.nullptr.mod.util.handlers.SoundsHandler;
//import com.nullptr.mod.model.Netero;
import net.minecraft.util.ResourceLocation;
import com.nullptr.mod.events.EventHandler;
import net.minecraft.client.Minecraft;
import com.nullptr.mod.chess.ChessView;
import com.nullptr.mod.entity.weirdzombie.EntityInit;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import com.nullptr.mod.gui.GuiMysteriousStrangerBook;
import net.minecraftforge.client.model.obj.OBJLoader;
//import net.minecraftforge.registries.RegistryManager;
//import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.registry.EntityEntry; 
import net.minecraftforge.fml.common.registry.EntityRegistry;
//import com.nullptr.mod.model.Netero;
//import com.nullptr.mod.core.tileentity.TileEntityEquipmentPart;
//import com.nullptr.mod.core.tileentity.TileEntityEquipment;
//import net.minecraftforge.fml.client.registry.ClientRegistry;
//import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraft.entity.Entity; 
import com.nullptr.mod.openai.ChatGPTBot;
import com.nullptr.mod.recipes.RecipesDelete;
import com.nullptr.mod.gui.GuiMysteriousStrangerBook;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import com.nullptr.mod.commands.CommandDimensionTeleport;
import com.nullptr.mod.objects.blocks.TileEntityJenny;
import com.nullptr.mod.objects.blocks.TileEntityRenderer;
//import net.minecraft.client.renderer.block.model.ModelResourceLocation;
//import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.nullptr.mod.init.BlockInit;
import net.minecraft.item.ItemBlock;
//import net.minecraftforge.fml.ModLoadingContext;
import com.nullptr.mod.discord.events.ServerEvents;
import com.nullptr.mod.discord.Minecraft2Discord;
import org.apache.commons.lang3.tuple.Pair;
//import net.minecraftforge.fml.common.eventhandler.EventPriority;
//import net.minecraftforge.eventbus.api.EventPriority;
//import net.minecraftforge.fml.ExtensionPoint;
//import net.minecraftforge.fml.network.FMLNetworkConstants;
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);

        // Typically initialization of models and such goes here:
        EntityInit.initModels();
	MinecraftForge.EVENT_BUS.register(new EventHandler());
	MinecraftForge.EVENT_BUS.register(new ChessView());
	MinecraftForge.EVENT_BUS.register(new SoundsHandler());
	MinecraftForge.EVENT_BUS.register(new RecipesDelete());
	MinecraftForge.EVENT_BUS.register(new ServerEvents());
        // ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SERVER_SPECS);
       // ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, ()-> Pair.of(()-> FMLNetworkConstants.IGNORESERVERONLY, (in, net) -> true));
        //Netero.init();
	registerModels();
	registerRenders();
	addOBJLoaderDomainIfOnClient();
	ChatGPTBot.init();
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("mod:jenny", "inventory");
        final int DEFAULT_ITEM_SUBTYPE = 0;
        ModelLoader.setCustomModelResourceLocation(new ItemBlock(BlockInit.JENNY), DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJenny.class, new TileEntityRenderer());
	GameRegistry.registerTileEntity(TileEntityJenny.class, "mod:jenny");
    }
    
    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }
    @Override
    public void registerVariantRenderer(Item item, int meta, String filename, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation("mod", filename), id));
    }
    @Override
    public void addOBJLoaderDomainIfOnClient() {
        OBJLoader.INSTANCE.addDomain(Main.MODID);
    }
    @Override 
    public void openMyGui() { 
	    Minecraft.getMinecraft().displayGuiScreen(new GuiMysteriousStrangerBook()); 
    }
    @Override
    public void init()
    {
        //Netero.init();
    }
   // @Override
    @Override
    public String getResponse(String message)
    {
	String response = ChatGPTBot.getResponse(message);
	return response;
    }
  /*  @Override
    public String sendLongMessage(String message)
    {
	String response = ChatGPTBot.sendLongMessage(message);
	return response;
    }*/
    @Override
    public void registerModel(Item item, int metadata) 
    {
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
    @Override
    public void registerModels() {
		//AssetManager.registerModels();
    }

	// ========== Creatures ==========
    @Override
    public void loadCreatureModel(String modelClassName) throws ClassNotFoundException {
		//creature.modelClass = (Class<? extends ModelCustom>) Class.forName(modelClassName);
    }


    @Override
    public void loadSubspeciesModel(/*Subspecies subspecies, */String modelClassName) throws ClassNotFoundException {
		//subspecies.modelClass = (Class<? extends ModelCustom>) Class.forName(modelClassName);
    }
    @Override
    public void registerRenders() {
           //LightBallModel Netero = new LightBallModel();
	  // import net.minecraft.entity.Entity;

           // Предположим, у нас есть RegistryObject для сущности под названием "example_entity"
           //RegistryObject<Entity> entityRegistryObject = RegistryManager.ACTIVE.getRegistry(Netero.class).getValue(new ResourceLocation(Main.MODID, "Netero"));
          /* String entityName = Main.MODID + ":" + "Netero";
	   EntityEntry entityEntry = EntityRegistry.getEntry(entityName);
           // Проверяем, что сущность зарегистрирована в реестре
           if (entityEntry != null) {
               // Получаем объект сущности из RegistryObject
	       Entity NeteroEntity = entityEntry.newInstance();
	       ModelCreatureObj.render(NeteroEntity, 1200, 0F, 60F, 60F, 60F, 10F, false);
               // Теперь вы можете выполнять операции с "exampleEntity"
           } else {
              // Сущность не зарегистрирована или еще не загружена
           }*/
    }
  /*  public static void onPlayerTick(EntityPlayer player) {
    if (player.getCurrentArmor(0) != null) {
        ItemStack boots = player.getCurrentArmor(0);
        if (boots.getItem() == Items.diamond_boots) {
            World world = player.worldObj;
            int i = MathHelper.floor_double(player.posX);
            int j = MathHelper.floor_double(player.boundingBox.minY - 1);
            int k = MathHelper.floor_double(player.posZ);
            Material m = world.getBlock(i, j, k).getMaterial();
            boolean flag = (m == Material.water);
            //int l = EnchantmentHelper.getEnchantmentLevel(MainClass.waterWalking.effectId, boots);
            if (flag && player.motionY < 0.0D) {
                player.posY += -player.motionY;
                player.motionY = 0.0D;
                player.fallDistance = 0.0F;
            }
        }
    }
}*/
}
