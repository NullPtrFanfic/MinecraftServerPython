package com.nullptr.mod.events; 
//import net.minecraftforge.common.MinecraftForge;
import com.nullptr.mod.Main;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraft.world.World;
import net.minecraft.init.Blocks;
import java.util.Random;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
//import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TextComponentString;
import com.nullptr.mod.openai.ChatGPTBot;
//import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockDoor;
//import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import java.util.Random;
//import net.minecraft.client.renderer.RenderSystem;
//import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraft.enchantment.Enchantment;
//import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import com.nullptr.mod.util.SoundsUtil;
import com.nullptr.mod.chess.ChessView;
import com.nullptr.mod.util.handlers.SoundsHandler;
import com.nullptr.mod.init.ItemInit;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBook;
import net.minecraft.item.Item;
import com.nullptr.mod.commands.CommandDimensionTeleport;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
//import com.nullptr.mod.party.Shop;
//import com.nullptr.mod.party.Party;
@Mod.EventBusSubscriber(Side.CLIENT)
public class EventHandler {
    @Mod.EventHandler
    public static void serverInit(FMLServerStartingEvent event)
    {
		event.registerServerCommand(new CommandDimensionTeleport());
    }
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
    if (event.player instanceof EntityPlayerMP) {
        EntityPlayerMP p = (EntityPlayerMP) event.player;
        // Остальная часть вашего кода.
        p.inventory.addItemStackToInventory(new ItemStack(ItemInit.BOOK));
		// update credits from mysql
       /* if(Party.players_left.contains(p.getName())){
	   p.teleport(getLobby());
	   p.getInventory().setContents(Party.pinv.get(p.getName()));
	   p.updateInventory();
	   Party.players_left.remove(p.getName());
	}

	if(Party.players.contains(event.getPlayer().getName())){
	   p.sendMessage(TextFormatting.RED.toString() + TextFormatting.BOLD.toString() + new TextComponentString("Вы уже в игре!"));
	   return;
	}
	Party.players.add(p.getName());
	event.setJoinMessage(TextFormatting.GREEN.toString() + TextFormatting.BOLD.toString() + p.getName() + new TextComponentString(" присоеденился к игре!"));


	if(Party.players.size() < min_players + 1){
	   Party.pinv.put(p.getName(), p.getInventory().getContents());
	   Party.startNew();
	   return;
	}
				
	try {
	   pinv.put(p.getName(), p.getInventory().getContents());
	   if (currentmg > -1) {
		minigames.get(currentmg).join(p);
		p.teleport(minigames.get(currentmg).spawn);
	}
		} catch (Exception ex) {
		     p.sendMessage(TextFormatting.RED.toString() + "Внутренняя ошибка.");
				}
    */
    }
    }
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) { // Use the correct event parameter
        EntityPlayer player = event.player;
        if (player.inventory.armorItemInSlot(0) != null) { // Use `player.inventory.armorItemInSlot()` instead of `getCurrentArmor()`
            ItemStack boots = player.inventory.armorItemInSlot(0);

            if (boots.getItem() == Items.DIAMOND_BOOTS) { // Use `Items.DIAMOND_BOOTS` instead of `Items.diamond_boots`
                World world = player.world;
                int i = MathHelper.floor(player.posX);
               // int j = MathHelper.floor(player.boundingBox.minY - 1);
		int j = MathHelper.floor(player.getEntityBoundingBox().minY - 1); // Use `getEntityBoundingBox()` to get the bounding box 
                int k = MathHelper.floor(player.posZ);
                Material m = world.getBlockState(new BlockPos(i, j, k)).getMaterial(); // Use `getBlockState()` instead of `getBlock()`
                boolean flag = (m == Material.WATER); // Use `Material.WATER` instead of `Material.water`

                if (flag && player.motionY < 0.0D) {
                    player.posY += -player.motionY;
                    player.motionY = 0.0D;
                    player.fallDistance = 0.0F;
                }
            }
        }
    }
    /*@SubscribeEvent
    public void doTheOtherThing(RenderGameOverlayEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledRes = new ScaledResolution(mc);
        mc.getTextureManager().bindTexture(new ResourceLocation("mod", "textures/items/obsidian_ingot.png"));
        mc.ingameGUI.drawTexturedModalRect(scaledRes.getScaledWidth() / 2, scaledRes.getScaledHeight() / 2, 0, 0, 59, 8);
	//mc.getTextureManager().bindTexture(Gui.ICONS);
	Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 9, 9, 9, 9);
    }*/
    @SubscribeEvent
    public void doTheOtherThing(RenderGameOverlayEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledRes = new ScaledResolution(mc);
	// Установка прозрачности текстуры
	/*GlStateManager.glBegin(GL_QUADS);
	GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();*/
       // RenderSystem.defaultBlendFunc();
       // RenderSystem.defaultAlphaFunc();
        mc.getTextureManager().bindTexture(new ResourceLocation(Main.MODID, "textures/items/ico.png"));
       // mc.ingameGUI.drawTexturedModalRect(scaledRes.getScaledWidth() / 2 - 29, scaledRes.getScaledHeight() / 2 - 4, 0, 0, 59, 8);
	//mc.getTextureManager().bindTexture(Gui.ICONS);
        Gui.drawRect(scaledRes.getScaledWidth() / 2 - 4, scaledRes.getScaledHeight() / 2 - 4, scaledRes.getScaledWidth() / 2 + 4, scaledRes.getScaledHeight() / 2 + 4, 0xFF0000FF);
	int textureWidth = 56; // Ширина текстуры в пикселях
        int textureHeight = 56; // Высота текстуры в пикселях
        int xPos = scaledRes.getScaledWidth() - textureWidth - 10; // Координата X для отображения текстуры
        int yPos = 10; // Координата Y для отображения текстуры
        mc.ingameGUI.drawTexturedModalRect(xPos, yPos, 0, 0, textureWidth, textureHeight);
	/*GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
	GlStateManager.popMatrix();
	GlStateManager.glEnd();*/
    }
    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        String message = event.getMessage();
        if (message.contains("!tree")) {
	    Minecraft.getMinecraft().player.sendChatMessage("Генерация дерева..");
	    EntityPlayerSP playerSP = Minecraft.getMinecraft().player;
            BlockPos playerPos = playerSP.getPosition();
            World world = playerSP.getEntityWorld();
            generateTree(world, playerPos);
        }
	else if (message.contains("!house")) {
	    Minecraft.getMinecraft().player.sendChatMessage("Генерация дома..");
	    EntityPlayerSP playerSP = Minecraft.getMinecraft().player;
            BlockPos playerPos = playerSP.getPosition();
            World world = playerSP.getEntityWorld();
            generateHouse(world, playerPos);
	}
	else if (message.startsWith("!sound")) {
	    SoundsUtil.playSound(SoundsUtil.SOUND_1);
	}
	else if (message.startsWith("!chess")) {
	    ChessView.boardActive = !ChessView.boardActive;
	}
	/*else if (message.startsWith("!mp help")) {
	    EntityPlayerSP p = Minecraft.getMinecraft().player;
	    p.sendMessage(TextFormatting.GREEN.toString() + new TextComponentString("-- ") + TextFormatting.GOLD.toString() + new TextComponentString("MinigamesParty Help") + TextFormatting.GREEN.toString() + new TextComponentString(" --"));
            p.sendMessage(TextFormatting.DARK_AQUA.toString() + new TextComponentString("/mp setlobby");
            p.sendMessage(TextFormatting.DARK_RED.toString() + new TextComponentString("/mp setup");
            p.sendMessage(TextFormatting.DARK_BLUE.toString() + new TextComponentString("/mp disable/enable [minigame]");
            p.sendMessage(TextFormatting.DARK_PURPLE.toString() + new TextComponentString("/mp stats [player]");
            p.sendMessage(TextFormatting.YELLOW.toString() + new TextComponentString("/mp list");
            p.sendMessage(TextFormatting.BLUE.toString() + new TextComponentString("/mp leaderboards [wins|credits]");
            p.sendMessage(TextFormatting.GREEN.toString() + new TextComponentString("/mp leave");
            p.sendMessage(TextFormatting.LIGHT_PURPLE.toString() + new TextComponentString("/mp setcomponent [minigame] [component]");
            p.sendMessage(TextFormatting.GOLD.toString() + new TextComponentString("To set up the game, do the following:");
            p.sendMessage(TextFormatting.DARK_AQUA.toString() + new TextComponentString("1. Build the main lobby");
            p.sendMessage(TextFormatting.DARK_AQUA.toString() + new TextComponentString("2. /mp setlobby");
            p.sendMessage(TextFormatting.DARK_AQUA.toString() + new TextComponentString("3. Go far away");
	    p.sendMessage(TextFormatting.DARK_AQUA.toString() + new TextComponentString("4. /mp setup"));
	}
	else if (message.startsWith("!mp join")) {
	    EntityPlayerSP p = Minecraft.getMinecraft().player;
	    if(Party.players.size() > 12 - 1){
		p.sendMessage(TextFormatting.RED.toString() + new TextComponentString("You can't join because the minigames party is full!"));
	    }
	    Party.players.add(p.getName());
						// if its the first player to join, start the whole minigame
	    if(Party.players.size() < 1 + 1){
	       Party.pinv.put(p.getName(), p.getInventory().getContents());
	       Party.startNew();
	       if(1 > 1){
		  p.sendMessage(TextFormatting.GREEN.toString() + new TextComponentString("You joined the queue. There are ") + TextFormattting.GOLD.toString() + new TextComponentInteger.toString(min_players) + ChatColor.GREEN + " players needed to start.");
	       }
	    }else{ // else: just join the minigame
		try{
		Party.pinv.put(p.getName(), p.getInventory().getContents());
		if(Party.ingame_started){
		    Party.minigames.get(Party.currentmg).lost.add(p);
		    Party.minigames.get(Party.currentmg).spectate(p);
		}else{
		Party.minigames.get(Party.currentmg).join(p);
		}
		}catch(Exception e){}
		p.sendMessage(TextFormatting.GREEN.toString() + new TextComponentString("You joined the queue. There are ") + TextFormatting.GOLD.toString() + new TextComponentString("1") + TextFormatting.GREEN.toString() + " игрок нужен для старта.");
		Party.updateScoreboardOUTGAME(p.getName());
		p.getInventory().clear();
	        p.updateInventory();
	    }
	}
	else if (message.startsWith("!mp list")) {
	    EntityPlayerSP p = Minecraft.getMinecraft().player;
	    p.sendMessage(TextFormatting.DARK_AQUA.toString() + new TextComponentString("-- ") + TextFormatting.GOLD.toString() + new TextComponentString("Мини игры: ") + TextFormatting.DARK_AQUA.toString() + new TextComponentString("--"));
	    for(Minigame m : minigames){
		if(m.isEnabled()){
		   p.sendMessage(TextFormatting.GREEN.toString() + new TextComponentString(m.name));
		}else{
		   p.sendMessage(TextFormatting.RED.toString() + new TextComponentString(m.name));
		}
	    }
	}
	else if (message.startsWith("!mp setup")) {
	    EntityPlayerSP p = Minecraft.getMinecraft().player;
	    Party.setupAll(p.getLocation());
	}
	else if (message.startsWith("!mp stats")) {
	    EntityPlayerSP p = Minecraft.getMinecraft().player;
	    p.sendMessage(TextFormatting.GREEN.toString() + new TextComponentString("Вы имеете " + Integer.toString((Party.getPlayerStats(p, "credits")))) + new TextComponentString(" Кредитов."));
	}
	else if (message.startsWith("!mp lb")) {
	    EntityPlayerSP p = Minecraft.getMinecraft().player;
	    outputLeaderboardsByWins(p);
	    outputLeaderboardsByCredits(p);
	}*/
	/*else if (message.startsWith("!chess")) {
	   String count = args[1];
						currentmg += Integer.parseInt(count) - 1;
						minigames.get(currentmg).join(p);
      if(currentmg > -1){
						c_ += seconds-c;
						c = seconds;
					}
     this.saveComponentForMinigame(args[1], args[2], p.getLocation());
							p.sendMessage(ChatColor.GREEN + "Сохранено!");this.disableMinigame(sender, args[1]);	this.enableMinigame(sender, args[1]);p.teleport(getLobby());
    */
	else if (message.startsWith("!gpt")) {
                String response = Main.proxy.getResponse(message);
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString(response));
	}
    }
    private void generateTree(World world, BlockPos pos) {
        Random rand = new Random();
        int height = 4 + rand.nextInt(3); // Высота дерева от 4 до 6 блоков
        int trunkHeight = height - 1; // Высота ствола = высота дерева - 1

        // Генерация ствола дерева
        for (int y = 0; y < trunkHeight; y++) {
            world.setBlockState(pos.up(y), Blocks.LOG.getDefaultState());
        }

        // Генерация верхушки дерева
        for (int y = trunkHeight; y <= height + 1; y++) {
            for (int xOffset = -2; xOffset <= 2; xOffset++) {
                for (int zOffset = -2; zOffset <= 2; zOffset++) {
                    if (Math.abs(xOffset) != 2 || Math.abs(zOffset) != 2) {
                        world.setBlockState(pos.add(xOffset, y, zOffset), Blocks.LEAVES.getDefaultState());
                    }
                }
            }
        }

        // Генерация дополнительных листьев на верхушке дерева
        for (int yOffset = height - 3; yOffset <= height + 1; yOffset++) {
            for (int xOffset = -1; xOffset <= 1; xOffset++) {
                for (int zOffset = -1; zOffset <= 1; zOffset++) {
                     if (Math.abs(xOffset) != 1 || Math.abs(zOffset) != 1) {
                        world.setBlockState(pos.add(xOffset, yOffset, zOffset), Blocks.LEAVES.getDefaultState());
                     }
                }
            }
        }
    }
    private void generateHouse(World world, BlockPos pos) {
        int width = 5;
        int height = 6;
        int depth = 6;

        // Create a hollow shell made of bricks.
        for (int x = pos.getX(); x < pos.getX() + width; x++) {
            for (int y = pos.getY(); y < pos.getY() + height; y++) {
                for (int z = pos.getZ() + 3; z < pos.getZ() + 3 + depth; z++) {
                    if (y == pos.getY() || y == pos.getY() + height - 1 || x == pos.getX() || x == pos.getX() + width - 1 || z == pos.getZ() + 3 || z == pos.getZ() + 3 + depth - 1) {
                        world.setBlockState(new BlockPos(x, y, z), Blocks.BRICK_BLOCK.getDefaultState());
                    }
                }
            }
        }

        // Set the floor.
        for (int x = pos.getX() - 1; x <= pos.getX() + width; x++) {
            for (int z = pos.getZ() + 2; z <= pos.getZ() + 4 + depth; z++) {
                world.setBlockState(new BlockPos(x, pos.getY() - 1, z), Blocks.COBBLESTONE.getDefaultState());
            }
        }

        // Add a Door.
        world.setBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 3), Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER));
	world.setBlockState(new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ() + 3), Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER));

        // Add Windows.
        world.setBlockState(new BlockPos(pos.getX() + 3, pos.getY() + 1, pos.getZ() + 3), Blocks.GLASS.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX() + 4, pos.getY() + 1, pos.getZ() + 3), Blocks.GLASS.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX() + 2, pos.getY() + 1, pos.getZ() + 3 + depth), Blocks.GLASS.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX() + 3, pos.getY() + 1, pos.getZ() + 3 + depth), Blocks.GLASS.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() + 5), Blocks.GLASS.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() + 6), Blocks.GLASS.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() + 7), Blocks.GLASS.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX() + width, pos.getY() + 1, pos.getZ() + 5), Blocks.GLASS.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX() + width, pos.getY() + 1, pos.getZ() + 6), Blocks.GLASS.getDefaultState());
        world.setBlockState(new BlockPos(pos.getX() + width, pos.getY() + 1, pos.getZ() + 7), Blocks.GLASS.getDefaultState());

        // Add a Roof.
        for (int i = 0; i <= width / 2; i++) {
            for (int j = 0; j <= height / 2; j++) {
                    world.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + height + j, pos.getZ() + 3), Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
                    //world.setBlockState(new BlockPos(pos.getX() + i, pos.getY()));
                    world.setBlockState(new BlockPos(pos.getX() + width - i, pos.getY() + height + j, pos.getZ() + 3), Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
                    world.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + height + j, pos.getZ() + 3 + depth), Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
                    world.setBlockState(new BlockPos(pos.getX() + width - i, pos.getY() + height + j, pos.getZ() + 3 + depth), Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
               // }
            }
        }

        // Gable ends.
        for (int i = 1; i < width / 2; i++) {
            world.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + height + i, pos.getZ() + 3), Blocks.BRICK_BLOCK.getDefaultState());
            world.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + height + i, pos.getZ() + 3 + depth), Blocks.BRICK_BLOCK.getDefaultState());
            world.setBlockState(new BlockPos(pos.getX() + width - i, pos.getY() + height + i, pos.getZ() + 3), Blocks.BRICK_BLOCK.getDefaultState());
            world.setBlockState(new BlockPos(pos.getX() + width - i, pos.getY() + height + i, pos.getZ() + 3 + depth), Blocks.BRICK_BLOCK.getDefaultState());
        }
    }
    
  /*  @SubscribeEvent
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bm = (BookMeta) book.getItemMeta();
        bm.setDisplayName(TextFormatting.GREEN.toString() + "" + TextFormatting.BOLD.toString() + "Приветствуем на нашем сервере!");
        bm.addPage("Введение. О сервере - ...");
        bm.addPage("Приятной игры на нашем проекте! Конец.");
        bm.addPage("English text");
        bm.setAuthor("NullPtr");
        bm.setLore(Arrays.asList("This book contains " + bm.getPageCount() + " pages."));
        book.setItemMeta(bm);
        player.getInventory().addItem(book);

        ItemStack bow = new ItemStack(Material.BOW, 1);
        bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);
        ItemMeta itemMeta = bow.getItemMeta();
        itemMeta.setDisplayName(TextFormatting.BLUE.toString() + "Teleport Bow");
        itemMeta.setUnbreakable(true);
        bow.setItemMeta(itemMeta);
        player.getInventory().addItem(bow);
    }

    @SubscribeEvent
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity().hasMetadata("teleport_bow")) {
            Player shooter = (Player) event.getEntity().getShooter();
            Vector direction = shooter.getLocation().getDirection();
            Location location = event.getEntity().getLocation();
            location.setDirection(direction);
            playerteleport(shooter, location);
        }
    }

    @SubscribeEvent
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.getEntityType() == EntityType.ARROW) {
            if (event.getEntity().getShooter() instanceof Player) {
                Player shooter = (Player) event.getEntity().getShooter();
                ItemStack bowItem = shooter.getInventory().getItemInMainHand();
                if (bowItem.getItemMeta().getDisplayName().equals(TextFormatting.BLUE.toString() + "Teleport Bow")) {
                    event.getEntity().setMetadata("teleport_bow", new FixedMetadataValue(this, true));
                }
            }
        }
    }*/
    private static void generateColumn(World world, BlockPos pos) {
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            world.setBlockState(pos, Blocks.LOG.getDefaultState());
            int randomX = rand.nextInt(3) - 1;
            int randomZ = rand.nextInt(3) - 1;
            pos = pos.add(randomX, 1, randomZ);
        }
    }
    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent.Text event) {
        // Проверяем условие полета
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player.capabilities.isFlying) {
            // Устанавливаем позицию для текста
            int x = 5;
            int y = event.getResolution().getScaledHeight() - mc.fontRenderer.FONT_HEIGHT - 5;

            // Отображаем текст
            String text = TextFormatting.GREEN.toString() + TextFormatting.BOLD.toString() + new TextComponentString("Вы взлетели!").getFormattedText();
            FontRenderer fontRenderer = mc.fontRenderer;
            fontRenderer.drawString(text, x, y, 0);
        }
    }
}
