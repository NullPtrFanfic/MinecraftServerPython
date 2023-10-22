package com.nullptr.mod.recipes;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
@EventBusSubscriber
public class RecipesDelete {
 @SubscribeEvent 
 public static void registerRecipes(RegistryEvent.Register event) { 
        ResourceLocation WoodenAxe = new ResourceLocation("minecraft:crafting_table");
        IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) event.getRegistry();
        modRegistry.remove(WoodenAxe);
 }
}
