package de.castlestudios.castlelobby.listeners;

import de.castlestudios.castlelobby.CastleLobby;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class ProtectionListener implements Listener {

    @EventHandler
    public void onBlock(BlockBreakEvent event) {
        if (CastleLobby.getInstance().getConfig().getBoolean("break-blocks") == false) {
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (CastleLobby.getInstance().getConfig().getBoolean("place-blocks") == false) {
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (CastleLobby.getInstance().getConfig().getBoolean("lobby-damage") == false) {
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }
    }
    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent event) {
        if (CastleLobby.getInstance().getConfig().getBoolean("swap-hands") == false) {
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }
    }
    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        if (CastleLobby.getInstance().getConfig().getBoolean("pickup-items") == false) {
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (CastleLobby.getInstance().getConfig().getBoolean("drop-items") == false) {
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }
    }
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getCurrentItem() != null) {
            if (CastleLobby.getInstance().getConfig().getBoolean("move-items") == false) {
                event.setCancelled(true);
            } else {
                event.setCancelled(false);
            }
        }
    }
    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        if (CastleLobby.getInstance().getConfig().getBoolean("enable-food") == false) {
                event.setFoodLevel(20);
                event.setCancelled(true);
            } else {
                event.setCancelled(false);
            }
    }

}
