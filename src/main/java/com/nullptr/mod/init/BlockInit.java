package com.nullptr.mod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import com.nullptr.mod.objects.blocks.BlockBase;
import com.nullptr.mod.objects.blocks.BlockOres;
//import com.nullptr.mod.objects.blocks.BlockPlank;
import com.nullptr.mod.objects.blocks.BlockLogBase;
import com.nullptr.mod.objects.blocks.BlockLeavesBase;
import com.nullptr.mod.objects.blocks.BlockSaplingBase;
import com.nullptr.mod.objects.blocks.BlockChrSword;
import com.nullptr.mod.objects.blocks.BlockTeleporter;
import com.nullptr.mod.objects.blocks.BlockJenny;
public class BlockInit
{
   public static final List<Block> BLOCKS = new ArrayList<Block>();
   public static final Block SILVER_BLOCK = new BlockBase("silver_block", Material.IRON);
  // public static final Block ORE_END = new BlockOres("ore_end", "end");
   public static final Block ORE_OVERWORLD = new BlockOres("ore_overworld", "overworld");
   public static final Block COPPER_LEAVES = new BlockLeavesBase("copper_leaves");
   public static final Block COPPER_LOG = new BlockLogBase("copper_log");
   public static final Block COPPER_SAPLING = new BlockSaplingBase("copper_sapling");
   public static final Block ALUMINIUM_LEAVES = new BlockLeavesBase("aluminium_leaves");
   public static final Block ALUMINIUM_LOG = new BlockLogBase("aluminium_log");
   public static final Block ALUMINIUM_SAPLING = new BlockSaplingBase("aluminium_sapling");
   public static final Block CHR_SWORD = new BlockChrSword("chr_sword");
   public static final Block TELEPORTER = new BlockTeleporter("teleporter");
   public static final Block JENNY = new BlockJenny();
}
