package xenar47.bukkit.mygames.impl.games;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.material.Wool;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import xenar47.bukkit.mygames.api.Game;
import xenar47.bukkit.mygames.api.GameScore;
import xenar47.bukkit.mygames.world.location.WorldLocation;

public class TagGame extends Game {

	private final HashMap<UUID, Integer> scores;
	
	private UUID it = null;

	@Override
	public String getName() {
		return "Tag";
	}
	
	@Override
	public double getSecsPerTick() {
		return 1;
	}

	/**
	 * @param mygames
	 */
	public TagGame() {
		scores = new HashMap<UUID, Integer>();
	}

	@Override
	public void preparePlayer(Player player) {
		
		player.getInventory().clear();

		Wool w = new Wool();
		w.setColor(DyeColor.BLUE);
		
		player.getInventory().setHelmet(w.toItemStack());
	}

	public void prepareIt(Player player) {
		player.getInventory().clear();

		Wool w = new Wool();
		w.setColor(DyeColor.RED);

		player.getInventory().setHelmet(w.toItemStack());
	}

	@Override
	public void prepareGame() {
		
		Random r = new Random();
		int index = r.nextInt(getPlayers().size());
		Player p = Bukkit.getPlayer(getPlayers().get(index));
		setIt(p);
	}
	
	@Override
	public void warmupTick() {
		
		Player p = Bukkit.getPlayer(it);
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 255));
		p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 400, 255));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 255));
	}

	@Override
	public void tick() {
		
		int score = 0;
		if (scores.containsKey(it))
			score = scores.get(it);
		scores.put(it, (int)(score + getSecsPerTick()));
	
		Player p = Bukkit.getPlayer(it);
		
		p.removePotionEffect(PotionEffectType.BLINDNESS);
		p.removePotionEffect(PotionEffectType.CONFUSION);
		p.removePotionEffect(PotionEffectType.SLOW);
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 400, 3), true);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 1), true);
	}
	
	public void setIt(Player player) {

		if (it != null) {
			Player oldIt = Bukkit.getPlayer(it);
			preparePlayer(oldIt);
		}

		it = player.getUniqueId();
		prepareIt(player);
		
		for (UUID uuid : getPlayers()) {
			Player p = Bukkit.getPlayer(uuid);
			p.sendMessage((uuid == it)?
					(ChatColor.RED + "YOU" + ChatColor.DARK_AQUA + " are now it!" ):
					(ChatColor.RED + player.getName() + ChatColor.DARK_AQUA + " is now it!"));
		}
		
	}

	@Override
	public boolean playerDamagePlayer(Player attacker, Player victim) {

		if (it == attacker.getUniqueId() && !this.isWarmup()) {
			setIt(victim);
		}

		return false;
	}

	@Override
	public void playerKilled(Player player) {
		
		setIt(player);
	}
	
	/*@Override
	public boolean shouldEnd() {
		return false;
	}*/

	@Override
	public ArrayList<String> getWinners(){
		return lowestScorers(scores);
	}
/*	
	@Override
	public ArrayList<GameScore> getSideScores() {
		return orderLowest(getPlayerScores());
	}

	@Override
	public ArrayList<GameScore> getPlayerScores() {
		ArrayList<GameScore> s = new ArrayList<GameScore>();
		for (UUID uuid : this.scores.keySet()) {
			s.add(new GameScore(Bukkit.getPlayer(uuid).getName(), scores.get(uuid)));
		}
		return s;
	}*/

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public DyeColor[] getSpawnColors() {
		return null;
	}

	@Override
	public WorldLocation[] getLocationTypes() {
		return null;
	}

	@Override
	public boolean doFallDamage() {
		return false;
	}

	@Override
	public boolean doDrownDamage() {
		return false;
	}

	@Override
	public int getMinPlayers() {
		return 4;
	}

	@Override
	public int getMaxPlayers() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public boolean allowJoinInProgress() {
		return true;
	}

	@Override
	public ArrayList<GameScore> getSideScores() {
		return orderLowest(this.getPlayerScores());
	}
}
