package com.nullptr.mod.recipes;

import net.minecraftforge.fml.common.registry.GameRegistry;
import com.nullptr.mod.init.ItemInit;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
public class SmeltingRecipes
{
   public static void init()
   {
      GameRegistry.addSmelting(new ItemStack(Blocks.OBSIDIAN), new ItemStack(ItemInit.OBSIDIAN_INGOT), 0.4F);
   }
}
