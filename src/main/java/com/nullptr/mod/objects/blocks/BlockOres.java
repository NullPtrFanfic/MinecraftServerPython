package com.nullptr.mod.objects.blocks;

import com.nullptr.mod.Main;

public class BlockOres extends Block implements IHasModel, IMetaName
{
   public static final PropertyEnum<EnumHandler, EnumType> VARIANT = PropertyEnum.<EnumHandler.EnumType>create("variant", EnumHandler.EnumType.class);
   private String name, dimension;
   public BlockOres(String name, Material material)
   {
      super(material);
      setUnlocalizedName(name);
      setRegistryName(name);
      setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
      setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumType.COPPER));
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
