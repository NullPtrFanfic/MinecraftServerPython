package com.nullptr.mod.objects.items.staffs;

import com.nullptr.mod.Main;
import com.nullptr.mod.init.ItemInit;
import com.nullptr.mod.util.interfaces.IHasModel;
import net.minecraft.item.Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class LargeFireballStaff extends Item implements IHasModel
{
   public LargeFireballStaff(String name, int explosionSize)
   {
      setUnlocalizedName(name);
      setRegistryName(name);
      setCreativeTab(Main.MODTAB);
      ItemInit.ITEMS.add(this);
   }
   @Override
   public ActionResult<ItemStack> onItemRightClick(Worls worldIn, EntityPlayer playerIn, EnumHand handIn)
   {
      ItemStack item = playerIn.getHeldItem(handIn);
      Vec3d look = playerIn.getLookVec();
      EntityLargeFireball largeFireball = new EntityLargeFireball(worldin, playerIn, 1D, 1D, 1D);
     
   }
   @Override
   public void registerModels()
   {
      Main.proxy.registerItemRenderer(this, 0, "inventory");
   }
}
