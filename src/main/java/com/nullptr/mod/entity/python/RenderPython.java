package com.nullptr.mod.entity.python;

import com.nullptr.mod.Main;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import com.nullptr.mod.entity.python.ModelPython;
import com.nullptr.mod.entity.python.EntityPython;
@SideOnly(Side.CLIENT)
public class RenderPython extends RenderLiving<EntityPython>
{
	public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID + ":textures/entity/test/test.png");
	
	public RenderPython(RenderManager manager) 
	{
		super(manager, new ModelPython(), 0.2f);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityPython entity) 
	{
		return TEXTURE;
	}
}
