package com.nullptr.mod.objects.blocks.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import com.nullptr.mod.util.interfaces.IMetaName;
import net.minecraft.block.Block;
public class ItemBlockVariants extends ItemBlock
{
   public ItemBlockVariants(Block block)
   {
      super(block);
      setHasSubtypes(true);
      setMaxDamage(0);
   }
   @Override
   public int getMetadata(int damage)
   {
      return damage;
   }
   @Override
   public String getTranslationKey(ItemStack stack)
   {
      return super.getTranslationKey() + "_" + ((IMetaName)this.block).getSpecialName(stack);
   }
}
