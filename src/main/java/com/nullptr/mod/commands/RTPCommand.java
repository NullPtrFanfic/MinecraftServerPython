package com.nullptr.mod.commands;

import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import java.util.Random;

public class RTPCommand extends CommandBase {
    @Override
    public String getName() {
        return "randomteleport";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/randomteleport - Teleports the player to a random location within 500 blocks.";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
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
