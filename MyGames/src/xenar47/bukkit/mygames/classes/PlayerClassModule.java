package xenar47.bukkit.mygames.classes;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import xenar47.bukkit.mygames.api.Game;
import xenar47.bukkit.mygames.api.GameModule;
import xenar47.bukkit.mygames.inventorymenu.InventoryMenu;
import xenar47.bukkit.mygames.inventorymenu.MenuItem;
import xenar47.bukkit.mygames.inventorymenu.MenuItemListener;

public class PlayerClassModule implements GameModule, MenuItemListener {

	private Game game;
	private SelectablePlayerClass[] classes;
	
	public InventoryMenu menu = null;
	
	public PlayerClassModule(Game game, SelectablePlayerClass[] classes) { 
		this.game = game;
		this.classes = classes;
		
		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
		
		for (SelectablePlayerClass playerClass : classes) {
			MenuItem mi = new MenuItem(playerClass.menuIcon(), this, true);
			mi.setListenerData(playerClass.getName());
			menuItems.add(mi);
		}
		
		menu = new InventoryMenu("Select a Class!", menuItems);
	}
	
	private void openMenu(UUID uuid) {
		Player player = Bukkit.getPlayer(uuid);
		if (player == null)
			return;
		
		System.out.println("Opened Menu For " + player.getDisplayName());
		InventoryMenu.openMenu(player, menu);
	}
	
	@Override
	public void onWarmup() {
		for (UUID uuid : game.getPlayers()) {
			openMenu(uuid);
		}
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnd() {
		
	}

	@Override
	public void onPlayerDeath(UUID uuid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPlayerScore(UUID uuid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(Player player, InventoryMenu menu, MenuItem item) {
		
		String data = item.getListenerData();
		
		for (SelectablePlayerClass playerClass : classes) {
			if (playerClass.getName().equals(data)) {
				PlayerClass.setClass(player, playerClass);
				break;
			}
		}
	}

	@Override
	public void onPlayerJoin(UUID uuid) {
		openMenu(uuid);
	}

	@Override
	public void onPlayerLeave(UUID uuid) {
		// TODO Auto-generated method stub
		
	}

}
