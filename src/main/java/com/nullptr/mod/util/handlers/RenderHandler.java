package com.nullptr.mod.util.handlers;

import com.nullptr.mod.entity.centaur.EntityCentaur;
//import com.nullptr.mod.init.BlockInit;
import com.nullptr.mod.entity.centaur.RenderCentaur;
import com.nullptr.mod.entity.test.EntityTest;
import com.nullptr.mod.entity.test.RenderTest;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.client.renderer.ItemMeshDefinition;
//import net.minecraft.client.renderer.block.model.ModelResourceLocation;
//import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler 
{
	public static void registerEntityRenders()
	{
		/*RenderingRegistry.registerEntityRenderingHandler(EntityCentaur.class, new IRenderFactory<EntityCentaur>()
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
		});*/
	}
}
