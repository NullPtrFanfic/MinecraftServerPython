package com.nullptr.mod.entity.test;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.world.World;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import com.nullptr.mod.util.handlers.LootTableHandler;
import com.nullptr.mod.util.SoundsUtil;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class EntityTest extends EntityAgeable
{
   public EntityTest(World worldIn)
   {
      super(worldIn);
      this.setSize(width, height);
   }
   @Override
   protected void initEntityAI()
   {
      this.tasks.addTask(2, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIWander(this, 0.3D));
      this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.5D, true));
      this.tasks.addTask(3, new EntityAIAvoidEntity<>(this, EntityMob.class, 4.0F, 2.2D, 2.2D));
      this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 1.0F));
   }
   @Override
   protected void applyEntityAttributes()
   {
       super.applyEntityAttributes();
       this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
       this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.201115D);
       this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
   }
   @Override
   public EntityAgeable createChild(EntityAgeable ageable)
   {
      this.setSize(0.5f, 0.5f);
      return new EntityTest(world);
   }
	
   @Override
   protected SoundEvent getAmbientSound() 
   {
	return SoundsUtil.SOUND_3;
   }
	
   @Override
   protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
   {
	return SoundsUtil.SOUND_2;
   }
	
   @Override
   protected ResourceLocation getLootTable() 
   {
        return LootTableHandler.TEST;
   }
	
   @Override
   protected boolean canDropLoot() 
   {
	return true;
   }
}
