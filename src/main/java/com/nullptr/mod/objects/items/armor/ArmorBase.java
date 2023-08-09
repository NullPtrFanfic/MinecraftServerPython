package com.nullptr.mod.objects.items.armor;
import com.nullptr.mod.Main;
import net.minecraft.item.ItemArmor;
import com.nullptr.mod.init.ItemInit;
import net.minecraft.inventory.EntityEquipmentSlot;
import com.nullptr.mod.util.interfaces.IHasModel;
public class ArmorBase extends ItemArmor implements IHasModel
{
   public ArmorBase(String name, ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipmentSlot)
   {
      super(material, renderIndex, equipmentSlot);
     // setUnlocalizedName(name);
      setRegistryName(name);
     // setCreativeTab(Main.MODTAB);

      ItemInit.ITEMS.add(this);
   }
   @Override
   public void registerModels()
   {
      Main.proxy.registerItemRenderer(this, 0, "inventory");
   }
}
