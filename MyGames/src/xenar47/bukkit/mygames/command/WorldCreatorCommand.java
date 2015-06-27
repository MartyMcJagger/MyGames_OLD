package xenar47.bukkit.mygames.command;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;

import xenar47.bukkit.mygames.MyGames;
import xenar47.bukkit.mygames.command.worldCreator.WorldCreateCommand;
import xenar47.bukkit.mygames.command.worldCreator.WorldDefaultsCommand;
import xenar47.bukkit.mygames.command.worldCreator.WorldEnvironmentCommand;
import xenar47.bukkit.mygames.command.worldCreator.WorldNameCommand;
import xenar47.bukkit.mygames.command.worldCreator.WorldReviewCommand;
import xenar47.bukkit.mygames.command.worldCreator.WorldSeedCommand;
import xenar47.bukkit.mygames.command.worldCreator.WorldStructuresCommand;
import xenar47.bukkit.mygames.command.worldCreator.WorldTypeCommand;

public class WorldCreatorCommand extends MyGamesCommand {

	HashMap<UUID, WorldCreator> creators = new HashMap<UUID, WorldCreator>();
	
	CommandMap map;
	
	public WorldCreatorCommand(MyGames mygames) {
		super(mygames, "worldCreator");
		
		this.setPermission("mygames.command.world.create");
		
		map = new CommandMap(mygames){

			@Override
			public void defaultCommand(CommandSender sender) {
				sender.sendMessage(ChatColor.GRAY + "/mygames worldCreator help");
			}
			
		};
		
		map.register("name", new WorldNameCommand(mygames, this));
		map.register("type", new WorldTypeCommand(mygames, this));
		map.register("environment", new WorldEnvironmentCommand(mygames, this));
		map.register("seed", new WorldSeedCommand(mygames, this));
		map.register("structures", new WorldStructuresCommand(mygames, this));

		map.register("defaults", new WorldDefaultsCommand(mygames, this));
		map.register("review", new WorldReviewCommand(mygames, this));
		map.register("create", new WorldCreateCommand(mygames, this));
	}

	@Override
	public boolean execute(CommandSender sender, String label,
			String[] args) {
		return map.onCommand(sender, this, label, args);
	}
	
	public WorldCreator getWorldCreator(UUID uuid) {
		if (creators.containsKey(uuid))
			return creators.get(uuid);
		
		return createNew(uuid);
	}
	
	public void setWorldCreator(UUID uuid, WorldCreator worldCreator) {
		if (worldCreator == null) {
			creators.remove(uuid);
			return;
		}
		creators.put(uuid, worldCreator);
	}
	
	public WorldCreator createNew(UUID uuid) {
		WorldCreator wc = new WorldCreator("");
		
		creators.put(uuid, wc);
		return wc;
	}

}
