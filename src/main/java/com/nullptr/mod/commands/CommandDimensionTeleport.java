package com.nullptr.mod.commands;

import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayerMP;
//import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import java.util.Random;
import com.nullptr.mod.Main;
import net.minecraft.server.MinecraftServer;

public class CommandDimensionTeleport extends CommandBase {
    private final List<String> aliases = Lists.newArrayList(Main.MODID, "tp", "tpdim", "tpdimension", "teleportdimension", "teleport");
    @Override
    public String getName() {
        return "rtp";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/rtp - Teleports the player to a random location within 500 blocks.";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) return;
        String s = args[0];
        int dimensionID;
        try {
            dimensionID = Integer.parseInt(s);
        } catch(NumberFormatException e) {
            sender.sendMessage(new TextComponentString("Dimension ID invalid");
        }
        if (sender instanceof EntityPlayerMP) {
            EntityPlayerMP player = getCommandSenderAsPlayer(sender);
            Random random = new Random();
            int range = 500;

            double offsetX = random.nextInt(range * 2) - range;
            double offsetZ = random.nextInt(range * 2) - range;

            BlockPos newPos = new BlockPos(player.posX + offsetX, 60, player.posZ + offsetZ);
            player.connection.setPlayerLocation(newPos.getX() + 0.5, newPos.getY(), newPos.getZ() + 0.5, player.rotationYaw, player.rotationPitch);

            player.sendMessage(new TextComponentString("You have been randomly teleported."));
        }
    }
}
