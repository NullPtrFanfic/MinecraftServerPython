package com.nullptr.mod;

import com.nullptr.mod.Main;
//import com.lycanitesmobs.ObjectManager;
//import com.nullptr.mod.model.ModelEquipmentPart;
//import com.lycanitesmobs.core.entity.BaseCreatureEntity;
//import com.lycanitesmobs.core.info.CreatureInfo;
//import com.lycanitesmobs.core.info.CreatureManager;
//import com.lycanitesmobs.core.info.ModInfo;
import com.nullptr.mod.model.ModelObjOld;
import com.nullptr.mod.model.ModelItemBase;
//import com.lycanitesmobs.core.info.projectile.ProjectileInfo;
//import com.lycanitesmobs.core.info.projectile.ProjectileManager;
//import com.lycanitesmobs.core.item.ItemBase;
//import com.lycanitesmobs.core.item.equipment.ItemEquipmentPart;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class AssetManager {
	
	// Maps:
	public static Map<String, ResourceLocation> textures = new HashMap<>();
	public static Map<String, ResourceLocation[]> textureGroups = new HashMap<>();
	public static Map<String, SoundEvent> sounds = new HashMap<>();
	public static Map<String, ModelBase> models = new HashMap<>();
	//public static Map<ProjectileInfo, ModelBase> projectileModels = new HashMap<>();
	public static Map<String, IModel> objModels = new HashMap<>();
	public static Map<String, ModelItemBase> itemModels = new HashMap<>();
	
    // ==================================================
    //                        Add
    // ==================================================
	// ========== Texture ==========
	public static void addTexture(String name, String path) {
		name = name.toLowerCase();
		textures.put(name, new ResourceLocation(Main.MODID, path));
	}
	
	// ========== Texture Group ==========
	public static void addTextureGroup(String name, String[] paths) {
		name = name.toLowerCase();
        ResourceLocation[] textureGroup = new ResourceLocation[paths.length];
		for(int i = 0; i < paths.length; i++)
            textureGroup[i] = new ResourceLocation(Main.MODID, paths[i]);
        textureGroups.put(name, textureGroup);
	}
	
	// ========== Sound ==========
	public static void addSound(String name, String path) {
		name = name.toLowerCase();
        ResourceLocation resourceLocation = new ResourceLocation(Main.MODID, path);
        SoundEvent soundEvent = new SoundEvent(resourceLocation);
        soundEvent.setRegistryName(resourceLocation);
		sounds.put(name, soundEvent);
        GameRegistry.findRegistry(SoundEvent.class).register(soundEvent);
	}
	
	// ========== Model ==========
	public static void addModel(String name, ModelBase model) {
		name = name.toLowerCase();
		models.put(name, model);
	}
	
	// ========== Obj Model ==========
	public static void addObjModel(String name, String path) {
		name = name.toLowerCase();
		objModels.put(name, ModelObjOld.loadModel(new ResourceLocation(Main.MODID, "models/" + path + ".obj")));
	}

	// ========== Item Model ==========
	public static void addItemModel(String name, ModelItemBase model) {
		name = name.toLowerCase();
		itemModels.put(name, model);
	}
	
	
    // ==================================================
    //                        Get
    // ==================================================
	// ========== Texture ==========
	public static ResourceLocation getTexture(String name) {
		name = name.toLowerCase();
		if(!textures.containsKey(name))
			return null;
		return textures.get(name);
	}
	
	// ========== Icon Group ==========
	public static ResourceLocation[] getTextureGroup(String name) {
		name = name.toLowerCase();
		if(!textureGroups.containsKey(name))
			return null;
		return textureGroups.get(name);
	}
	
	// ========== Sound ==========
	public static SoundEvent getSound(String name) {
		name = name.toLowerCase();
		if(!sounds.containsKey(name))
			return null;
		return sounds.get(name);
	}
	
	// ========== Model ==========
	public static ModelBase getModel(String name) {
		name = name.toLowerCase();
		if(!models.containsKey(name))
			return null;
		return models.get(name);
	}

	public static ModelBase getCreatureModel(/*BaseCreatureEntity entityCreature*/) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		/*if (entityCreature.creatureInfo == null) {
			return null;
		}

		// Subspecies Model:
		if(entityCreature.getSubspecies() != null && entityCreature.getSubspecies().modelClass != null) {
			if(models.containsKey(entityCreature.getSubspecies().modelClass.toString())) {
				return models.get(entityCreature.getSubspecies().modelClass.toString());
			}
			ModelBase creatureModel = entityCreature.getSubspecies().modelClass.getConstructor().newInstance();
			models.put(entityCreature.getSubspecies().modelClass.toString(), creatureModel);
			return creatureModel;
		}

		// Main Model:
		return getCreatureModel(entityCreature.creatureInfo);*/
	}

	public static ModelBase getCreatureModel(/*CreatureInfo creatureInfo*/) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		/*if (creatureInfo == null || creatureInfo.modelClass == null) {
			return null;
		}*/

		// Main Model:
		//if (models.containsKey(creatureInfo.modelClass.toString())) {
			//return models.get(creatureInfo.modelClass.toString());
		//}
		//ModelBase creatureModel = creatureInfo.modelClass.getConstructor().newInstance();
		//models.put(creatureInfo.modelClass.toString(), creatureModel);
		//return creatureModel;
	}

	/**
	 * Gets the model used by the provided Projectile.
	 * @param projectileInfo The projectile info to get the model for.
	 * @return The Projectile Model.
	 */
	public static ModelBase getProjectileModel() {
		/*if(projectileModels.containsKey(projectileInfo)) {
			return projectileModels.get(projectileInfo);
		}
		return null;*/
	}
	
	// ========== Obj Model ==========
	public static IModel getObjModel(String name) {
		name = name.toLowerCase();
		if(!objModels.containsKey(name))
			return null;
		return objModels.get(name);
	}
	public static IModel getObjModel(String name, String path) {
		name = name.toLowerCase();
		if(!objModels.containsKey(name))
			addObjModel(name, Main.MODID, path);
		return objModels.get(name);
	}

	// ========== Item Model ==========
	public static ModelItemBase getItemModel(String name) {
		name = name.toLowerCase();
		if(!itemModels.containsKey(name))
			return null;
		return itemModels.get(name);
	}


	// ==================================================
	//                  Register Models
	// ==================================================
	@SideOnly(Side.CLIENT)
	public static void registerModels() {
	}
}
