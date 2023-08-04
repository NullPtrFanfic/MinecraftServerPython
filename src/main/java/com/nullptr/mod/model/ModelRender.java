package com.nullptr.mod.model;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import com.nullptr.mod.model.Netero;
import com.nullptr.mod.model.ModelNetero;
import com.nullptr.mod.Main;

public class ModelRender extends Render<Netero>{
	  public static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID + ":textures/entities/pokemon/enderman.png");

	  private ModelNetero model = new ModelNetero();
	  @Override
	  protected ResourceLocation getEntityTexture(Netero entity) {
	      return TEXTURES;
  	  }

	  public RenderNetero(RenderManager renderManager) {
		    super(renderManager);
	  }

          @Override
          public void doRender(Netero entity, double x, double y, double z, float entityYaw, float partialTicks) {
    	            GlStateManager.pushMatrix();
	            GlStateManager.translate(x, y, z);
		    GlStateManager.rotate(180, 1, 0, 0);
                    GlStateManager.rotate(entity.rotationPitch, 1, 0, 0);
		    GlStateManager.translate(0, -1.5, 0);
		    this.bindEntityTexture(entity);
		    this.model.render(entity, 0.0F, 0.0F, -0.1F, entity.rotationYaw, entity.rotationPitch, 0.0625F);
	    	    GlStateManager.popMatrix();
		    super.doRender(entity, x, y, z, entityYaw, partialTicks);
          }
}
