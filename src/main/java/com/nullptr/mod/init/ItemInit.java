package com.nullptr.mod.init;

import java.util.ArrayList;
import java.util.List;

import com.nullptr.mod.objects.items.ItemBase;
import net.minecraft.item.Item;
import com.nullptr.mod.Main;
import net.minecraft.inventory.EntityEquipmentSlot;
import com.nullptr.mod.objects.items.armor.ArmorBase;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import com.nullptr.mod.objects.items.staffs.LargeFireballStaff;
import com.nullptr.mod.objects.items.staffs.LightningStaff;

public class ItemInit
{
    public static final List<Item> ITEMS = new ArrayList<Item>();
    public static final Item OBSIDIAN_INGOT = new ItemBase("obsidian_ingot");
    public static final ArmorMaterial ARMOR_RUBY = EnumHelper.addArmorMaterial("armor_ruby", Main.MODID + ":ruby", 1500, new int[] {4, 7, 9, 5}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);
    public static final Item HELMET_RUBY =  new ArmorBase("helmet_ruby", ARMOR_RUBY, 1, EntityEquipmentSlot.HEAD);
    public static final Item CHESTPLATE_RUBY =  new ArmorBase("chestplate_ruby", ARMOR_RUBY, 1, EntityEquipmentSlot.CHEST);
    public static final Item LEGGINGS_RUBY =  new ArmorBase("leggings_ruby", ARMOR_RUBY, 2, EntityEquipmentSlot.LEGS);
    public static final Item BOOTS_RUBY =  new ArmorBase("boots_ruby", ARMOR_RUBY, 1, EntityEquipmentSlot.FEET);
    public static final Item LARGE_FIREBALL_STAFF = new LargeFireballStaff("large_fireball_staff", 50);
    public static final Item LIGHTNING_STAFF = new LightningStaff("lightning_staff");
}
