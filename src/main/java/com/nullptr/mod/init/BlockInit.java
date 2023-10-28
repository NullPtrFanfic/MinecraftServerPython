package com.nullptr.mod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import com.nullptr.mod.objects.blocks.BlockBase;
import com.nullptr.mod.objects.blocks.BlockOres;
import com.nullptr.mod.objects.blocks.BlockPlank;
import com.nullptr.mod.objects.blocks.BlockLogs;
import com.nullptr.mod.objects.blocks.BlockLeaf;
import com.nullptr.mod.objects.blocks.BlockSaplings;
public class BlockInit
{
   public static final List<Block> BLOCKS = new ArrayList<Block>();
   public static final Block SILVER_BLOCK = new BlockBase("silver_block", Material.IRON);
  // public static final Block ORE_END = new BlockOres("ore_end", "end");
   public static final Block ORE_OVERWORLD = new BlockOres("ore_overworld", "overworld");
   public static final Block PLANKS = new BlockPlank("planks");
   public static final Block LOGS = new BlockLogs("planks");
   public static final Block LEAVES = new BlockLeaf("planks");
   public static final Block SAPLINGS = new BlockSaplings("planks");
  // public static final Block ORE_NETHER = new BlockOres("ore_nether", "nether");
}
