package com.nullptr.mod.init;

import java.util.ArrayList;
import java.util.List;

import com.nullptr.mod.objects.items.ItemBase;
import net.minecraft.item.Item;
import com.nullptr.mod.Main;

public class ItemInit
{
    public static final List<Item> ITEMS = new ArrayList<Item>();
    public static final Item OBSIDIAN_INGOT = new ItemBase("obsidian_ingot");
    public static final ArmorMaterial ARMOR_RUBY = EnumHelper.addArmorMaterial("armor_ruby", Main.MOD_ID, + ":ruby", 1500, new int[] {4, 7, 9, 5}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);
    public static final Item HELMET_RUBY =  new ArmorBase("helmet_ruby", ARMOR_RUBY, 1, EntityEquipmentSlot.HEAD);
    public static final Item CHESTPLATE_RUBY =  new ArmorBase("chestplate_ruby", ARMOR_RUBY, 1, EntityEquipmentSlot.CHEST);
    public static final Item LEGGINGS_RUBY =  new ArmorBase("leggings_ruby", ARMOR_RUBY, 2, EntityEquipmentSlot.LEGS);
    public static final Item BOOTS_RUBY =  new ArmorBase("boots_ruby", ARMOR_RUBY, 1, EntityEquipmentSlot.FEET);
}
