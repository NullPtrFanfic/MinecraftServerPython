package com.nullptr.mod.objects.blocks;

import com.nullptr.mod.Main;
import com.nullptr.mod.objects.blocks.item.ItemBlockVariants;
import net.minecraft.block.Block;
import com.nullptr.mod.util.interfaces.IMetaName;
import com.nullptr.mod.util.IHasModel;
import com.nullptr.mod.util.handlers.EnumHandler;
import com.nullptr.mod.init.ItemInit;
import com.nullptr.mod.init.BlockInit;
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
      ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
   }
   @Override
   public void registerModels()
   {
      Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
   }
}
