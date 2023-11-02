package com.nullptr.mod.world.gen;

import com.nullptr.mod.world.gen.WorldGenStructure;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import java.util.Random;
public class WorldGenCustomStructures implements IWorldGenerator
{
   public static final WorldGenStructure ARMOURY = new WorldGenStructure("armoury");
   @Override
   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
}
