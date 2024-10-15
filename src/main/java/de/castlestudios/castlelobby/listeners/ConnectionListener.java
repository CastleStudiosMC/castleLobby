package de.castlestudios.castlelobby.listeners;

import de.castlestudios.castlelobby.CastleLobby;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = CastleLobby.getInstance().getConfig();

        String message = config.getString("Join-Message");
        message = message.replaceAll("&", "§");
        message = message.replace("%player%", player.getName());
        event.setJoinMessage(message);

        player.setFoodLevel(config.getInt("food-level"));
        player.setHealth(config.getInt("health"));
        player.setLevel(config.getInt("level"));
        player.setGameMode(GameMode.valueOf(String.valueOf(config.get("gamemode"))));

        String navigatorName = config.getString("Navigator.title");
        navigatorName = navigatorName.replaceAll("&", "§");

        ItemStack navigator = new ItemStack(Material.valueOf(String.valueOf(config.get("Navigator.item"))));
        ItemMeta navigatorMeta = navigator.getItemMeta();
        navigatorMeta.setDisplayName(navigatorName);
        navigator.setItemMeta(navigatorMeta);

        player.getInventory().setItem(config.getInt("Navigator.slot"), navigator);

        World world = Bukkit.getWorld(config.getString("Location.spawn.world"));
        double x = config.getDouble("Location.spawn.x");
        double y = config.getDouble("Location.spawn.y");
        double z = config.getDouble("Location.spawn.z");
        float yaw = (float) config.getDouble("Location.spawn.yaw");
        float pitch = (float) config.getDouble("Location.spawn.pitch");

        Location loc = new Location(world, x, y, z, yaw, pitch);
        player.teleport(loc);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = CastleLobby.getInstance().getConfig();

        String message = config.getString("Quit-Message");
        message = message.replaceAll("&", "§");
        message = message.replace("%player%", player.getName());
        event.setQuitMessage(message);

        player.getInventory().clear();
    }

}
