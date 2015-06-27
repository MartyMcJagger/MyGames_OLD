package xenar47.bukkit.mygames.impl.classes;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import xenar47.bukkit.mygames.classes.SelectablePlayerClass;

public class Archer extends SelectablePlayerClass {

	@Override
	public Material menuIconMaterial() {
		return Material.BOW;
	}

	@Override
	public String shortDescription() {
		return "Launch arrows at enemies from afar.";
	}

	@Override
	public String getName() {
		return "Archer";
	}

	@Override
	public ItemStack[] getPlayerWeapons() {
		ItemStack bow = new ItemStack(Material.BOW);
		bow.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
		bow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
		
		return new ItemStack[]{
			bow,
			new ItemStack(Material.ARROW, 64),
			new ItemStack(Material.STONE_SWORD)
		};
	}
	
	@Override
	public ItemStack[] getArmor() {
		return new ItemStack[]{
				new ItemStack(Material.LEATHER_CHESTPLATE)
		};
	}

	@Override
	public HashMap<PotionEffectType, Integer> getLastingEffects() {
		// TODO Auto-generated method stub
		return null;
	}

}
