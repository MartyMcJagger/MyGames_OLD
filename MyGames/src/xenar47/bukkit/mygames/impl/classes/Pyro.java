package xenar47.bukkit.mygames.impl.classes;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import xenar47.bukkit.mygames.classes.SelectablePlayerClass;

public class Pyro extends SelectablePlayerClass {
	
	@Override
	public String getName() {
		return "Pyro";
	}
	
	@Override
	public ItemStack[] getPlayerWeapons() {
		return new ItemStack[]{
			new ItemStack(Material.FLINT_AND_STEEL),
			new ItemStack(Material.IRON_AXE)
		};
	}
	
	@Override
	public ItemStack[] getArmor() {
		return new ItemStack[]{
				
		};
	}

	@Override
	public HashMap<PotionEffectType, Integer> getLastingEffects() {
		HashMap<PotionEffectType, Integer> effects = new HashMap<PotionEffectType, Integer>();
		effects.put(PotionEffectType.FIRE_RESISTANCE, 3);
		return effects;
	}

	@Override
	public Material menuIconMaterial() {
		return Material.FLINT_AND_STEEL;
	}

	@Override
	public String shortDescription() {
		return "When you want to watch the world burn.";
	}	

}
