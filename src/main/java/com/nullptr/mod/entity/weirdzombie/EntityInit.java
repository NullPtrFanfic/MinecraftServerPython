package com.nullptr.mod.entity.weirdzombie;

import com.nullptr.mod.entity.weirdzombie.EntityWeirdZombie;
import com.nullptr.mod.entity.weirdzombie.RenderWeirdZombie;
import com.nullptr.mod.entity.test.EntityTest;
import com.nullptr.mod.entity.test.RenderTest;
import com.nullptr.mod.model.Netero;
import com.nullptr.mod.model.ModelRender;
import com.nullptr.mod.entity.centaur.EntityCentaur;
import com.nullptr.mod.entity.centaur.RenderCentaur;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraft.client.renderer.entity.Render;
import com.nullptr.mod.Main;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityInit {
    public static void init() {
        // Every entity in our mod has an ID (local to this mod)
        int id = 1;
        EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID, "weirdzombie"), EntityWeirdZombie.class, "WeirdZombie", id++, Main.instance, 64, 3, true, 0x996600, 0x00ff00);

        // We want our mob to spawn in Plains and ice plains biomes. If you don't add this then it will not spawn automatically
        // but you can of course still make it spawn manually
        //EntityRegistry.addSpawn(EntityWeirdZombie.class, 100, 3, 5, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.ICE_PLAINS);

        // This is the loot table for our mob
       // LootTableList.register(EntityWeirdZombie.LOOT);
	//Netero.init();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityWeirdZombie.class, RenderWeirdZombie.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityCentaur.class, new IRenderFactory<EntityCentaur>()
        {
                @Override
                public Render<? super EntityCentaur> createRenderFor(RenderManager manager) 
                {
                    return new RenderCentaur(manager);
                }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityTest.class, new IRenderFactory<EntityTest>()
        {
                @Override
                public Render<? super EntityTest> createRenderFor(RenderManager manager) 
                {
                    return new RenderTest(manager);		
                }
        });
        RenderingRegistry.registerEntityRenderingHandler(Netero.class, new IRenderFactory<Netero>() {
			@Override
			public Render<? super Netero> createRenderFor(RenderManager manager) {
				return new ModelRender(manager);
			}
		});
    }
}
