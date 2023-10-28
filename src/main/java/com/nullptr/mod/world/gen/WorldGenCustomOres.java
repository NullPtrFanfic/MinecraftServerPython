package com.nullptr.mod.world.gen;
import com.nullptr.mod.init.BlockInit;
import com.nullptr.mod.objects.blocks.BlockOres;
import com.nullptr.mod.util.handlers.EnumHandler;
import net.minecraft.init.Blocks;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
public class WorldGenCustomOres implements IWorldGenerator
{
   private WorldGenerator ore_overworld_copper, ore_overworld_aluminium;
   public WorldGenCustomOres()
   {
      ore_overworld_copper = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.COPPER), 9, BlockMatcher.forBlock(Blocks.STONE));
      ore_overworld_aluminium = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.ALUMINIUM), 9, BlockMatcher.forBlock(Blocks.STONE));
   }
   private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight)
   {
   }
}
