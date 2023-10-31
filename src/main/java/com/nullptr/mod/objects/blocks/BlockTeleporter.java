package com.nullptr.mod.objects.blocks;

import com.nullptr.mod.commands.util.Teleport;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.nullptr.mod.objects.blocks.BlockBase;
public class BlockTeleporter extends BlockBase
{
   public BlockTeleporter(String name)
   { 
     super(name, Material.ROCK);
   }
   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
   {
      if (!world.isRemote)
      {
          Teleport.teleportToDimension(player, 2, player.getPosition().getX(), player.getPosition().getY() + 5, player.getPosition().getZ());
          return true;
      }
      return false;
   }
}
