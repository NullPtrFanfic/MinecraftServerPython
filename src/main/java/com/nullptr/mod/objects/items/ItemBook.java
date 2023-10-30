package com.nullptr.mod.objects.items;

import com.nullptr.mod.Main;
import com.nullptr.mod.util.interfaces.IHasModel;
import com.nullptr.mod.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBook;

public class ItemBook extends ItemBook implements IHasModel
{
   public ItemBook(String name)
   {
      setUnlocalizedName(name);
      setRegistryName(name);
      setCreativeTab(Main.MODTAB);
      ItemInit.ITEMS.add(this);
   }
   @Override 
   public ActionResult<ItemStack> onItemRightClick(ItemStack item, World world, EntityPlayer entityPlayer) 	{
    Minecraft.getMinecraft().player.sendMessage(new TextComponentString("DEBUG: onItemRightClick(временно)"));
    //public static void onPlayerInteract(PlayerInteractEvent.RightClickItem event) {
   // EntityPlayerSP player = Minecraft.getMinecraft().player;
    //World world = event.getWorld();
   // ItemStack itemStack = player.getHeldItemMainhand();
    if (book != null) {
    if (item.getItem() == book.getItem()) {
        if (!world.isRemote) {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Открытие меню.."));
            Main.proxy.openMyGui();
        }
    }
    }
    return item;
   }
   @Override
   public void registerModels()
   {
      Main.proxy.registerItemRenderer(this, 0, "inventory");
   }
}
