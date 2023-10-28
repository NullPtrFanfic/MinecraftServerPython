package com.nullptr.mod.objects.blocks;

import com.nullptr.mod.Main;
import com.nullptr.mod.objects.blocks.item.ItemBlockVariants;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import com.nullptr.mod.util.interfaces.IMetaName;
import com.nullptr.mod.util.IHasModel;
import com.nullptr.mod.util.handlers.EnumHandler;
import com.nullptr.mod.init.ItemInit;
import com.nullptr.mod.init.BlockInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.properties.PropertyEnum;
public class BlockOres extends Block implements IHasModel, IMetaName
{
   public static final PropertyEnum<EnumHandler.EnumType> VARIANT = PropertyEnum.<EnumHandler.EnumType>create("variant", EnumHandler.EnumType.class);
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
      ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
   }
   @Override
   public void registerModels()
   {
      for(int i = 0; i < EnumHandler.EnumType.values().length; i++)
      {
          Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "ore_" + this.dimension + EnumHandler.EnumType.values()[i].getName(), "inventory");
      }
   }
}
