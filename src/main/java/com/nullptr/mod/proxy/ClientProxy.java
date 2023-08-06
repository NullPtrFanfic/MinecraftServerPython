package com.nullptr.mod.proxy;

import com.nullptr.mod.Main;
import com.nullptr.mod.model.Netero;
import com.nullptr.mod.AssetManager;
import com.nullptr.mod.model.ModelObjOld;
import com.nullptr.mod.model.ModelCreatureObj;
import net.minecraft.util.ResourceLocation;
import com.nullptr.mod.model.projectile.LightBallModel;
//import com.nullptr.mod.BakedModelLoader;
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
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.client.model.obj.OBJLoader;
//import net.minecraftforge.registries.RegistryManager;
//import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.registry.EntityEntry; 
import net.minecraftforge.fml.common.registry.EntityRegistry;
import com.nullptr.mod.model.Netero;
//import com.nullptr.mod.core.tileentity.TileEntityEquipmentPart;
//import com.nullptr.mod.core.tileentity.TileEntityEquipment;
//import net.minecraftforge.fml.client.registry.ClientRegistry;
//import net.minecraftforge.client.model.ModelLoaderRegistry;
import com.nullptr.mod.model.projectile.LightBallModel;
//import com.nullptr.mod.renderer.EquipmentPartRenderer;
//import com.nullptr.mod.renderer.EquipmentRenderer;
import com.nullptr.mod.renderer.RenderRegister;
import net.minecraft.entity.Entity; 
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);

        // Typically initialization of models and such goes here:
        EntityInit.initModels();
        //Netero.init();
	registerModels();
	registerRenders();
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }
    @Override
    public void addOBJLoaderDomainIfOnClient() {
        OBJLoader.INSTANCE.addDomain(Main.MODID);
    }
    @Override
    public void init()
    {
        Netero.init();
    }
    @Override
    public void registerModels() {
		AssetManager.registerModels();
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
           LightBallModel Netero = new LightBallModel();
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
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) { // Use the correct event parameter
        EntityPlayer player = event.player;

        if (player.inventory.armorItemInSlot(0) != null) { // Use `player.inventory.armorItemInSlot()` instead of `getCurrentArmor()`
            ItemStack boots = player.inventory.armorItemInSlot(0);

            if (boots.getItem() == Items.DIAMOND_BOOTS) { // Use `Items.DIAMOND_BOOTS` instead of `Items.diamond_boots`
                World world = player.world;
                int i = MathHelper.floor(player.posX);
                int j = MathHelper.floor(player.boundingBox.minY - 1);
                int k = MathHelper.floor(player.posZ);
                Material m = world.getBlockState(new BlockPos(i, j, k)).getMaterial(); // Use `getBlockState()` instead of `getBlock()`
                boolean flag = (m == Material.WATER); // Use `Material.WATER` instead of `Material.water`

                if (flag && player.motionY < 0.0D) {
                    player.posY += -player.motionY;
                    player.motionY = 0.0D;
                    player.fallDistance = 0.0F;
                }
            }
        }
    }
}
