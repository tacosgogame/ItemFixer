package ru.xtime_1_7_R4;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class XListener implements Listener{  
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
	public void onDamage(EntityDamageByEntityEvent event){
		if (event.getDamager().getType() == EntityType.PLAYER) {
			Player player2 = (Player) event.getDamager();
			if (!player2.hasPermission("itemfixer.bypass")) {
				event.setCancelled(Checks.checkAttributes(player2.getInventory().getItemInHand()));
				Checks.removeEnt(player2.getInventory().getItemInHand());
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
	public void onPickupItem(final PlayerPickupItemEvent e) {
		if (!e.getPlayer().hasPermission("itemfixer.bypass")) {
			e.setCancelled(Checks.checkAttributes(e.getItem().getItemStack()));
			Checks.removeEnt(e.getItem().getItemStack());
		}
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
	public void InventoryClick(final InventoryClickEvent e) {
		if (e.getCurrentItem() != null) {
			if (!e.getWhoClicked().hasPermission("itemfixer.bypass")) {
				e.setCancelled(Checks.checkAttributes(e.getCurrentItem()));
				Checks.removeEnt(e.getCurrentItem());
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
	public void onPlayerInteract(final PlayerInteractEvent e) {
		if (!e.getPlayer().hasPermission("itemfixer.bypass") && e.getItem() != null) {
			e.setCancelled(Checks.checkAttributes(e.getItem()));
			Checks.removeEnt(e.getItem());
		}
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
	public void onItemDrop(final PlayerDropItemEvent e) {
		if (!e.getPlayer().hasPermission("itemfixer.bypass")) {
			e.setCancelled(Checks.checkAttributes(e.getItemDrop().getItemStack()));
			Checks.removeEnt(e.getItemDrop().getItemStack());
		}
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
	public void OnUse(PlayerItemConsumeEvent e ){
		if (!e.getPlayer().hasPermission("itemfixer.bypass")) {
			if (e.getItem().getType() == Material.POTION) {
				e.setCancelled(Checks.checkAttributes(e.getItem()));
				Checks.removeEnt(e.getItem());
			}
		}
	}
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
	public void OnLaunch2 (BlockDispenseEvent e){
		e.setCancelled(Checks.checkAttributes(e.getItem()));
		Checks.removeEnt(e.getItem());
	}
}

