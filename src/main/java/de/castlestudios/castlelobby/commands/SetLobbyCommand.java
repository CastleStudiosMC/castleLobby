package de.castlestudios.castlelobby.commands;

import de.castlestudios.castlelobby.CastleLobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetLobbyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (sender instanceof Player player) {

            String prefix = CastleLobby.getInstance().getConfig().getString("Prefix");
            prefix = prefix.replaceAll("&", "§");

            String setmessage = CastleLobby.getInstance().getConfig().getString("SetLobby");
            setmessage = setmessage.replaceAll("&", "§");
            setmessage = setmessage.replaceAll("%prefix%", prefix);

            String noperms = CastleLobby.getInstance().getConfig().getString("NoPermission");
            noperms = noperms.replaceAll("&", "§");
            noperms = noperms.replaceAll("%prefix%", prefix);

            if (player.hasPermission("castlelobby.cmd.setlobby")) {
                FileConfiguration config = CastleLobby.getInstance().getConfig();
                config.set("Location.spawn.world", player.getWorld().getName());
                config.set("Location.spawn.x", player.getLocation().getX());
                config.set("Location.spawn.y", player.getLocation().getY());
                config.set("Location.spawn.z", player.getLocation().getZ());
                config.set("Location.spawn.yaw", player.getLocation().getYaw());
                config.set("Location.spawn.pitch", player.getLocation().getPitch());
                CastleLobby.getInstance().saveConfig();
                player.sendMessage(setmessage);
            }else {
                player.sendMessage(noperms);
            }
        }


        return false;
    }
}
