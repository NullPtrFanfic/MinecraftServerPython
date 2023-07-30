package com.nullptr.mod.objects.items;

import com.nullptr.mod.Main;
import com.nullptr.mod.util.interfaces.IHasModel;
import com.nullptr.mod.init.ItemInit;
import net.minecraft.creativetab.CreativeTab;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{
   public ItemBase(String name)
   {
      setUnlocalizedName(name);
      setRegistryName(name);
      setCreativeTab(CreativeTabs.MATERIALS);
      ItemInit.ITEMS.add(this);
   }
   @Override
   public void registerModels()
   {
      Main.proxy.registerItemRenderer(this, 0, "inventory");
   }
}
