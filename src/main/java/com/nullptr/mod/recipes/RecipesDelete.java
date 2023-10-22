package com.nullptr.mod.recipes;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraft.item.crafting.CraftingManager;

@EventBusSubscriber
public class RecipesDelete {
 @SubscribeEvent 
 public static void registerRecipes(RegistryEvent.Register<IRecipe> event) { 
        ResourceLocation WoodenAxe = new ResourceLocation("minecraft:crafting_table");
        IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) event.getRegistry();
        modRegistry.remove(WoodenAxe);
        CraftingHelper.loadRecipes(false);
        CraftingManager.init()
 }
}
