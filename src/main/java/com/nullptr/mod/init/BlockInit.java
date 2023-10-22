package com.nullptr.mod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import com.nullptr.mod.objects.blocks.BlockBase;

public class BlockInit
{
   public static final List<Block> BLOCKS = new ArrayList<Block>();
   public static final Block SILVER_BLOCK = new BlockBase("silver_block", Material.IRON);
   public static final Block WAND = new BlockBase("wand");
}
