package de.castlestudios.castlelobby;

import de.castlestudios.castlelobby.commands.SetLobbyCommand;
import de.castlestudios.castlelobby.listeners.ConnectionListener;
import de.castlestudios.castlelobby.listeners.InteractListener;
import de.castlestudios.castlelobby.listeners.ProtectionListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class CastleLobby extends JavaPlugin {

    public static CastleLobby instance;

    private String Prefix;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadconfig();
        instance = this;
        getLogger().info("Castle Lobby has been enabled!");
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ConnectionListener(), this);
        getServer().getPluginManager().registerEvents(new ProtectionListener(), this);
        getServer().getPluginManager().registerEvents(new InteractListener(), this);

        getCommand("setlobby").setExecutor(new SetLobbyCommand());

    }
    public void loadconfig() {
        // Prefix
        Prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Prefix"));
        Prefix = getConfig().getString("Prefix");


    }

    public static CastleLobby getInstance() {
        return instance;
    }

    public String getPrefix() {
        return Prefix;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Castle Lobby has been disabled!");
    }
}
