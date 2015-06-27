package xenar47.bukkit.mygames.impl.weapons;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import xenar47.bukkit.mygames.api.Game;
import xenar47.bukkit.mygames.api.GameModule;
import xenar47.bukkit.mygames.api.Weapon;
import xenar47.bukkit.mygames.classes.PlayerClassModule;
import xenar47.bukkit.mygames.inventorymenu.InventoryMenu;

public class PlayerClassChooser extends Weapon {

	@Override
	public String getName() {
		return "Class Chooser";
	}

	@Override
	public void registerNecessaryListeners() {
		
	}

	@Override
	public boolean primary(Game game, Player player) {
		return false;
	}

	@Override
	public boolean secondary(Game game, Player player) {
		
		for (GameModule gm : game.getGameModules()) {
			if (gm instanceof PlayerClassModule) {
				InventoryMenu.openMenu(player, ((PlayerClassModule)gm).menu);
			}
		}
		
		return true;
	}

	@Override
	public int melee(Game game, Player player, Player victim) {
		return 2;
	}

	@Override
	public boolean interact(Game game, Player player, Entity target) {
		return false;
	}

	@Override
	public void reload(Game game, Player player) {
		
	}

	@Override
	public ItemStack getBaseItem() {
		return new ItemStack(Material.BOOK_AND_QUILL);
	}

}
