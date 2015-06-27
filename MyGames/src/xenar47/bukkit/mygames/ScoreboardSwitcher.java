package xenar47.bukkit.mygames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardSwitcher {
	
	@SuppressWarnings("unused")
	private ScoreboardSwitcher(){}
	
	private static HashMap<UUID, ArrayList<ScoreboardProvider>> scoreboards = new HashMap<UUID, ArrayList<ScoreboardProvider>>();
	private static int pulse = 0;
	
	public ScoreboardSwitcher(MyGames mygames) {
		Bukkit.getScheduler().runTaskTimer(mygames, switcher, 0, 20 * 5);
	}
	
	public static void addBoard(UUID uuid, ScoreboardProvider sp) {
		ArrayList<ScoreboardProvider> providers = scoreboards.get(uuid);
		
		if (providers == null)
			providers = new ArrayList<ScoreboardProvider>();
		
		providers.add(sp);
		
		scoreboards.put(uuid, providers);
	}
	
	public static Runnable switcher = new Runnable(){
		@Override
		public void run() 
		{
			for (UUID uuid : scoreboards.keySet()) {
				Player player = Bukkit.getPlayer(uuid);
				ArrayList<ScoreboardProvider> boards = scoreboards.get(uuid);
				
				if (boards.size() <= 0)
					continue;
				
				Scoreboard sb = boards.get(ScoreboardSwitcher.pulse % boards.size()).getScoreboard();
				//Objective old = getObjective(player);
				
				player.setScoreboard(sb);
				
				/*
				old.setDisplayName(obj.getDisplayName());
				
				for (String entry : old.getScoreboard().getEntries()) {
					old.getScoreboard().resetScores(entry);
				}
				//*/
			}
			
			pulse++;
		}
	};
	
	/*
	private static Objective getObjective(Player player) {
		Scoreboard sb = player.getScoreboard();
		if (sb == null) {
			sb = Bukkit.getScoreboardManager().getNewScoreboard();
			player.setScoreboard(sb);
		}
		
		Objective obj = sb.getObjective(DisplaySlot.SIDEBAR);
		if (obj == null) {
			obj = sb.registerNewObjective("Sidebar", "dummy");
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		}
		
		return obj;
	}//*/
	
}
