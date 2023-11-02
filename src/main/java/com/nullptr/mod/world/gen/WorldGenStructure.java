package com.nullptr.mod.world.gen;

import com.nullptr.mod.util.interfaces.IStructure;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.Random;
public class WorldGenStructure extends WorldGenerator
{
   public static String structureName;
  
   public WorldGenStructure(String name) {
      this.structureName = name;
   }
   @Override
   public boolean generate(World world, Random rand, BlockPos position)
   {
      return false;
   }
   public static void generateStructure
}
