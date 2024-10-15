package de.castlestudios.castlelobby.listeners;

import de.castlestudios.castlelobby.CastleLobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = CastleLobby.getInstance().getConfig();

        String navigatorTitle = CastleLobby.getInstance().getConfig().getString("Navigator.title");
        navigatorTitle = navigatorTitle.replaceAll("&", "§");

        String inventoryNavigatorTitle = CastleLobby.getInstance().getConfig().getString("Inventory.navigator.title");
        inventoryNavigatorTitle = inventoryNavigatorTitle.replaceAll("&", "§");

        String inventoryNavigatorItem0 = CastleLobby.getInstance().getConfig().getString("Inventory.navigator.items.0.name");
        inventoryNavigatorItem0 = inventoryNavigatorItem0.replaceAll("&", "§");

        if (event.getItem() != null) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (event.getItem().getType() == Material.valueOf(String.valueOf(config.get("Navigator.item")))) {
                    if (event.getItem().getItemMeta().getDisplayName().equals(navigatorTitle)) {

                        Inventory inventory = Bukkit.createInventory(null, config.getInt("Inventory.navigator.size"), inventoryNavigatorTitle);

                        ItemStack item1 = new ItemStack(Material.valueOf(String.valueOf(config.get("Inventory.navigator.items.0.item"))));
                        ItemMeta item1Meta = item1.getItemMeta();
                        item1Meta.setDisplayName(inventoryNavigatorItem0);
                        item1.setItemMeta(item1Meta);

                        inventory.setItem(config.getInt("Inventory.navigator.items.0.slot"), item1);

                        player.openInventory(inventory);


                    }
                }
            }
        }
    }

}
