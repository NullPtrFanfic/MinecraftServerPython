package com.nullptr.mod.objects.blocks;
import com.nullptr.mod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
public class BlockBase extends Block implements IHasMod
{
   public BlockBase()
   {
       ItemInit.ITEMS.add(new ItemBlock(this.getRegistryName(name)))
   }
}
