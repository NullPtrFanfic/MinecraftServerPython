package com.nullptr.mod.objects.blocks.BlockChrSword;

import net.minecraft.block.material.Material;
import com.nullptr.mod.objects.blocks.BlockBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
public class BlockChrSword extends BlockBase
{
   public static final AxisAlignedBB CHR_SWORD_AABB = new AxisAlignedBB(0.1875D, 0, 0.1875D, 0.8125D, 0.625D, 0.8125D);
   public BlockChrSword(String name)
   {
      super(name, Material.CLOTH);
   }
   @Override
   public boolean isOpaqueCube(IBlockState state)
   {
      return false,
   }
   @Override
   public boolean isFullCube(IBlockState state)
   {
     return false;
   }
   @Override
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
   {
    return CHR_SWORD_AABB;
   }
}
