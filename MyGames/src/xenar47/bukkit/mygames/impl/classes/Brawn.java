package xenar47.bukkit.mygames.impl.classes;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import xenar47.bukkit.mygames.classes.SelectablePlayerClass;

public class Brawn extends SelectablePlayerClass {

	@Override
	public Material menuIconMaterial() {
		return Material.DIAMOND_AXE;
	}

	@Override
	public String shortDescription() {
		return "Smash skulls with your axe!";
	}

	@Override
	public String getName() {
		return "Brawn";
	}
	
	@Override
	public ItemStack[] getPlayerWeapons() {
		ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
		axe.addEnchantment(Enchantment.DAMAGE_ALL, 2);
		
		return new ItemStack[]{
				axe
		};
	}
	
	@Override
	public ItemStack[] getArmor() {
		return new ItemStack[]{
				new ItemStack(Material.DIAMOND_CHESTPLATE)
		};
	}

	@Override
	public HashMap<PotionEffectType, Integer> getLastingEffects() {
		
		HashMap<PotionEffectType, Integer> effects = new HashMap<PotionEffectType, Integer>();
		
		effects.put(PotionEffectType.DAMAGE_RESISTANCE, 2);
		effects.put(PotionEffectType.HEALTH_BOOST, 3);
		
		return effects;
	}

}
