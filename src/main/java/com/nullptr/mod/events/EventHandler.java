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

@Mod.EventBusSubscriber(Side.CLIENT)
public class EventHandler {
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
	GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
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
	GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
	GlStateManager.popMatrix();
    }
    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        String message = event.getMessage();
        if (message.contains("tree")) {
	    Minecraft.getMinecraft().player.sendChatMessage("Генерация дерева..");
	    EntityPlayerSP playerSP = Minecraft.getMinecraft().player;
            BlockPos playerPos = playerSP.getPosition();
            World world = playerSP.getEntityWorld();
            generateTree(world, playerPos);
        }
	if (message.contains("house")) {
	    Minecraft.getMinecraft().player.sendChatMessage("Генерация дома..");
	    EntityPlayerSP playerSP = Minecraft.getMinecraft().player;
            BlockPos playerPos = playerSP.getPosition();
            World world = playerSP.getEntityWorld();
            generateHouse(world, playerPos);
	}
	if (true) {
            /*if (message.length() > 2000 && message.startsWith("!gpt")) {
                Main.proxy.sendLongMessage(message);
            } else */
	    if (message.startsWith("!gpt")) {
                String response = Main.proxy.getResponse(message);
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString(response));
            }
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
        int height = 3;
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
                    world.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + height + j, pos.getZ() + 3), Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, BlockStairs.EnumFacing.NORTH));
                    world.setBlockState(new BlockPos(pos.getX() + i, pos.getY()));
                    world.setBlockState(new BlockPos(pos.getX() + width - i, pos.getY() + height + j, pos.getZ() + 3), Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, BlockStairs.EnumFacing.NORTH));
                    world.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + height + j, pos.getZ() + 3 + depth), Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, BlockStairs.EnumFacing.SOUTH));
                    world.setBlockState(new BlockPos(pos.getX() + width - i, pos.getY() + height + j, pos.getZ() + 3 + depth), Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, BlockStairs.EnumFacing.SOUTH_FLIPPED));
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
    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (block == Blocks.STONE) {
            generateColumn(world, pos);
        }
    }

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
