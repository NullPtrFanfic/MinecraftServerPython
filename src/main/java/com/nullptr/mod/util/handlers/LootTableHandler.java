package com.nullptr.mod.util.handlers;

import com.nullptr.mod.Main;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableHandler 
{
	public static final ResourceLocation TEST = LootTableList.register(new ResourceLocation(Main.MODID + ":loot_tables/entity/test/test.json"));
	public static final ResourceLocation CENTAUR = LootTableList.register(new ResourceLocation(Main.MODID, "centaur"));
}
