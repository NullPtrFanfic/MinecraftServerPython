package com.nullptr.mod.util.handlers;

import com.nullptr.mod.entity.centaur.EntityCentaur;
import com.nullptr.mod.entity.centaur.RenderCentaur;
import com.nullptr.mod.entity.test.EntityTest;
import com.nullptr.mod.entity.test.RenderTest;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderHandler 
{
    @SideOnly(Side.CLIENT)
    public static void registerEntityRenders()
    {
        if (isClientSide()) {
            /*RenderingRegistry.registerEntityRenderingHandler(EntityCentaur.class, new IRenderFactory<EntityCentaur>()
            {
                @Override
                public Render<? super EntityCentaur> createRenderFor(RenderManager manager) 
                {
                    return new RenderCentaur(manager);
                }
            });*/
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

    private static boolean isClientSide() {
        return Side.CLIENT == net.minecraftforge.fml.common.FMLCommonHandler.instance().getEffectiveSide();
    }
}
