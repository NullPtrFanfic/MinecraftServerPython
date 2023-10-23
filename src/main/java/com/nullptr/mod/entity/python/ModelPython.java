package com.nullptr.mod.entity.python;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelPython extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer tongue;
    public ModelRenderer body1;
    public ModelRenderer body2;
    public ModelRenderer body3;
    public ModelRenderer body4;
    public ModelRenderer body5;
    public ModelRenderer body6;
    public ModelRenderer body7;
    public ModelRenderer body8;
    public ModelRenderer body9;
    public int textureWidth = 64;
    public int textureHeight = 32;

    protected double distanceMovedTotal = 0.0D;
    protected static final double CYCLES_PER_BLOCK = 3.0D;
    protected int cycleIndex = 0;
    protected float[][] undulationCycle = new float[][] {
        {45F, -45F, -45F, 0F, 45F, 45F, 0F, -45F},
        {0F, 45F, -45F, -45F, 0F, 45F, 45F, 0F},
        {-45F, 90F, 0F, -45F, -45F, 0F, 45F, 45F},
        {-45F, 45F, 45F, 0F, -45F, -45F, 0F, 45F},
        {0F, -45F, 45F, 45F, 0F, -45F, -45F, 0F},
        {45F, -90F, 0F, 45F, 45F, 0F, -45F, -45F}
    };

    public ModelPython() {
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-2.5F, -1F, -5F, 5, 2, 5);
        head.setRotationPoint(0F, 23F, -8F);
        head.setTextureSize(textureWidth, textureHeight);

        tongue = new ModelRenderer(this, 0, 13);
        tongue.addBox(-0.5F, -0.5F, -10F, 1, 1, 5);
        tongue.setRotationPoint(0F, 23F, -8F);
        tongue.setTextureSize(textureWidth, textureHeight);

        body1 = new ModelRenderer(this, 20, 20);
        body1.addBox(-1.5F, -1F, -1F, 3, 2, 5);
        body1.setRotationPoint(0F, 23F, -8F);
        body1.setTextureSize(textureWidth, textureHeight);

        body2 = new ModelRenderer(this, 20, 20);
        body2.addBox(-1.5F, -1F, -1F, 3, 2, 5);
        body2.setRotationPoint(0F, 0F, 4F);
        body1.addChild(body2);

        // Continue with the same pattern for body3 to body9...

        // Rest of the code remains the same.
    }

    @Override
    public void render(Entity parEntity, float parTime, float parSwingSuppress, float par4, float parHeadAngleY, float parHeadAngleX, float par7) {
        setRotationAngles(parTime, parSwingSuppress, par4, parHeadAngleY, parHeadAngleX, par7, parEntity);
        GL11.glPushMatrix();
        GL11.glScalef(1.5F, 1.5F, 1.5F);
        if (this.isChild) {
            float childScaleFactor = 0.5F;
            GL11.glPushMatrix();
            GL11.glScalef(1.0F * childScaleFactor, 1.0F * childScaleFactor, 1.0F * childScaleFactor);
            GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
            head.render(par7);
            if (parEntity.ticksExisted % 60 == 0 && parSwingSuppress <= 0.1F) {
                tongue.render(par7);
            }
            body1.render(par7);
            GL11.glPopMatrix();
        } else {
            head.render(par7);
            if (parEntity.ticksExisted % 60 == 0 && parSwingSuppress <= 0.1F) {
                tongue.render(par7);
            }
            body1.render(par7);
        }
        GL11.glPopMatrix();
    }

    @Override
    public void setRotationAngles(float parTime, float parSwingSuppress, float par3, float parHeadAngleY, float parHeadAngleX, float par6, Entity parEntity) {
        updateDistanceMovedTotal(parEntity);
        cycleIndex = (int) ((getDistanceMovedTotal(parEntity) * CYCLES_PER_BLOCK) % undulationCycle.length);
        body2.rotateAngleY = degToRad(undulationCycle[cycleIndex][0]);
        body3.rotateAngleY = degToRad(undulationCycle[cycleIndex][1]);
        body4.rotateAngleY = degToRad(undulationCycle[cycleIndex][2]);
        body5.rotateAngleY = degToRad(undulationCycle[cycleIndex][3]);
        body6.rotateAngleY = degToRad(undulationCycle[cycleIndex][4]);
        body7.rotateAngleY = degToRad(undulationCycle[cycleIndex][5]);
        body8.rotateAngleY = degToRad(undulationCycle[cycleIndex][6]);
        body9.rotateAngleY = degToRad(undulationCycle[cycleIndex][7]);
    }

    protected void updateDistanceMovedTotal(Entity parEntity) {
        distanceMovedTotal += parEntity.getDistance(parEntity.prevPosX, parEntity.prevPosY, parEntity.prevPosZ);
    }

    protected double getDistanceMovedTotal(Entity parEntity) {
        return distanceMovedTotal;
    }

    protected float degToRad(float degrees) {
        return degrees * (float) Math.PI / 180;
    }

    protected void setRotation(ModelRenderer model, float rotX, float rotY, float rotZ) {
        model.rotateAngleX = degToRad(rotX);
        model.rotateAngleY = degToRad(rotY);
        model.rotateAngleZ = degToRad(rotZ);
    }
}
