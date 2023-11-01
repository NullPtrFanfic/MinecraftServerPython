package com.nullptr.mod.entity.jenny;

import com.nullptr.mod.Main;
import com.nullptr.mod.entity.jenny.EntityJenny;
import com.nullptr.mod.entity.jenny.ModelJenny;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderJenny extends RenderLiving<EntityJenny>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID + ":textures/entity/jenny/jenny.png");
	
	public RenderCentaur(RenderManager manager) 
	{
		super(manager, new ModelJenny(), 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityJenny entity) 
	{
		return TEXTURES;
	}

	@Override
	protected void applyRotations(EntityJenny entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
	{
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}
