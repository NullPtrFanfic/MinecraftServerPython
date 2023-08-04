package com.nullptr.mod.client.renderer;

import com.nullptr.mod.Main;
import com.nullptr.mod.model.Netero;
import com.nullptr.mod.model.ModelNetero;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexFormat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.renderer.vertex.VertexFormatElement.EnumUsage;
import net.minecraft.client.renderer.vertex.VertexFormatElement.EnumType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.model.IModel;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.entity.AbstractClientPlayer;

@SideOnly(Side.CLIENT)
public class RenderNetero extends Render<Netero> implements LayerRenderer<AbstractClientPlayer> {
    static private IModel model;
    static private IBakedModel bakedModel;
    static ResourceLocation texture;

    public RenderNetero(RenderManager renderManager) {
        super(renderManager);
        this.model = ModelLoaderRegistry.getModelOrLogError(new ResourceLocation(Main.MODID, "models/item/tutorial_item.obj"), "Missing model");
        this.bakedModel = this.model.bake(TRSRTransformation.identity(), DefaultVertexFormats.ITEM, ModelLoader.defaultTextureGetter());
    }

    private static void renderModel(IBakedModel model, VertexFormat fmt) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();
        worldrenderer.begin(GL11.GL_QUADS, fmt);
        for (BakedQuad bakedquad : model.getQuads(null, null, 0)) {
            worldrenderer.addVertexData(bakedquad.getVertexData());
            // net.minecraftforge.client.model.pipeline.LightUtil.renderQuadColor(worldrenderer, bakedquad, -1);
        }
        tessellator.draw();
    }

    @Override
    public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(entitylivingbaseIn.posX, entitylivingbaseIn.posY, entitylivingbaseIn.posZ);
        // GlStateManager.enableRescaleNormal();
        GlStateManager.scale(scale * 1.0f, scale * 1.0f, scale * 1.0f);
        // this.renderModel(this.bakedModel, DefaultVertexFormats.ITEM);
        GlStateManager.popMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }

    public static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID + ":textures/entity/netero/netero.png");
    private ModelNetero model = new ModelNetero();

    @Override
    protected ResourceLocation getEntityTexture(Netero entity) {
        return TEXTURES;
    }

    @Override
    public void doRender(Netero entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(90, 1, 0, 0);
        GlStateManager.rotate(entity.rotationPitch, 1, 0, 0);
        GlStateManager.translate(0, -1.5, 0);
        this.bindEntityTexture(entity);
        this.model.render(entity, 0.0F, 0.0F, -0.1F, entity.rotationYaw, entity.rotationPitch, 0.0625F);
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
