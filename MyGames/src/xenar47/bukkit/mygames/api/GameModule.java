package xenar47.bukkit.mygames.api;

import java.util.UUID;

public interface GameModule {
	
	public void onWarmup();
	public void onStart();
	public void onEnd();
	
	public void onPlayerJoin(UUID uuid);
	public void onPlayerLeave(UUID uuid);
	
	public void onPlayerDeath(UUID uuid);
	public void onPlayerScore(UUID uuid);

}
