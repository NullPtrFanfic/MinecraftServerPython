package com.nullptr.mod.util.handlers;

import com.nullptr.mod.entity.centaur.EntityCentaur;
import com.nullptr.mod.entity.python.EntityPython;
import com.nullptr.mod.entity.python.RenderPython;
import com.nullptr.mod.entity.python.ModelPython;
import com.nullptr.mod.entity.centaur.RenderCentaur;
import com.nullptr.mod.entity.test.EntityTest;
import com.nullptr.mod.entity.test.RenderTest;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
//import net.minecraftforge.fml.common.FMLCommonHandler;

@SideOnly(Side.CLIENT)
public class RenderHandler 
{
    public static void registerEntityRenders()
    {
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
            RenderingRegistry.registerEntityRenderingHandler(
	    		EntityPython.class, 
	    		RenderPython.getRenderFactory(
	                    new ModelPython(), 
	                    0.0F, // no shadow needed
	                    new ResourceLocation("mod:textures/entity/serpent/python.png")
	    				)
	    	);   
    }
}
