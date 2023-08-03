package com.nullptr.mod.util.handlers;

//import com.tutorialmod.turtywurty.entity.diamondArrow.EntityDiamondArrow;
//import com.tutorialmod.turtywurty.entity.diamondArrow.RenderDiamondArrow;
import com.nullptr.mod.entity.test.EntityTest;
import com.nullptr.mod.entity.test.RenderTest;
import com.nullptr.mod.init.BlockInit;
import com.nullptr.mod.Main;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHandler 
{
	public static void registerEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityTest.class, new IRenderFactory<EntityTest>()
		{
			@Override
			public Render<? super EntityTest> createRenderFor(RenderManager manager) 
			{
				return new RenderTest(manager);		
			}
		});
	}
  }
