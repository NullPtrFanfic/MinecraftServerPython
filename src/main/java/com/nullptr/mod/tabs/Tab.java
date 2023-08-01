package com.nullptr.mod.tabs;

import com.nullptr.mod.init.BlockInit;
import com.nullptr.mod.init.ItemInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class Tab extends CreativeTabs
{
   public Tab(String label)
   {
      super("modtab");
      this.setBackgroundImageName("tutorialmod.png");
   }

   @Override
   public ItemStack getTabIconItem()
   {
      return new ItemStack(ItemInit.OBSIDIAN_INGOT);
   }
}
