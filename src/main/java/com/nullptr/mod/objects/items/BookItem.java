package com.nullptr.mod.objects.items;

import com.nullptr.mod.Main;
import net.minecraft.util.ActionResult;
import com.nullptr.mod.util.interfaces.IHasModel;
import com.nullptr.mod.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
public class BookItem extends ItemBook implements IHasModel
{
   public BookItem(String name)
   {
      setUnlocalizedName(name);
      setRegistryName(name);
      setCreativeTab(Main.MODTAB);
      ItemInit.ITEMS.add(this);
   }
   @Override 
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entityPlayer, EnumHand handIn) 	
   {
    ItemStack item = entityPlayer.getHeldItem(handIn);
    //Minecraft.getMinecraft().player.sendMessage(new TextComponentString("DEBUG: onItemRightClick(временно)"));
    //public static void onPlayerInteract(PlayerInteractEvent.RightClickItem event) {
   // EntityPlayerSP player = Minecraft.getMinecraft().player;
    //World world = event.getWorld();
   // ItemStack itemStack = player.getHeldItemMainhand();
    if (!world.isRemote) {
        Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Открытие меню.."));
        Main.proxy.openMyGui();
    }
    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
   }
   @Override
   public void registerModels()
   {
      Main.proxy.registerItemRenderer(this, 0, "inventory");
   }
}
