package com.nullptr.mod.objects.blocks;

import com.nullptr.mod.Main;

public class BlockOres extends Block implements IHasModel, IMetaName
{
   private String name, dimension;
   public BlockOres(String name, Material material)
   {
      super(material);
      setUnlocalizedName(name);
      setRegistryName(name);
      setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
      this.name = name;
      this.dimension = dimension;
      BlockInit.BLOCKS.add(this);
      ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
   }
   @Override
   public void registerModels()
   {
      Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
   }
}
