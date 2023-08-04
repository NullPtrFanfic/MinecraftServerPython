package com.nullptr.mod.util.handlers;

import com.nullptr.mod.Main;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler 
{
	public static SoundEvent TEST_AMBIENT;
	public static SoundEvent TEST_HURT;
	public static SoundEvent TEST_DEATH;
	public static SoundEvent ENTITY_CENTAUR_AMBIENT, ENTITY_CENTAUR_HURT, ENTITY_CENTAUR_DEATH;
	
	public static void registerSounds()
	{
		TEST_AMBIENT = registerSound("entity.test.test_ambient");
		TEST_HURT = registerSound("entity.test.test_hurt");
		TEST_DEATH = registerSound("entity.test.test_death");
		ENTITY_CENTAUR_AMBIENT = registerSound("entity.centaur.ambient");
		ENTITY_CENTAUR_HURT = registerSound("entity.centaur.hurt");
		ENTITY_CENTAUR_DEATH = registerSound("entity.centaur.death");
	}
	
	private static SoundEvent registerSound(String name)
	{
		ResourceLocation location = new ResourceLocation(Main.MODID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}
