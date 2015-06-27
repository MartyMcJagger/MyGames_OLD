package xenar47.bukkit.mygames.command.worldCreator;

import java.util.UUID;

import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xenar47.bukkit.mygames.MyGames;
import xenar47.bukkit.mygames.Utils;
import xenar47.bukkit.mygames.command.MyGamesCommand;
import xenar47.bukkit.mygames.command.WorldCreatorCommand;

import com.google.common.collect.Lists;

public class WorldEnvironmentCommand extends MyGamesCommand {

	WorldCreatorCommand wcc;
	
	public WorldEnvironmentCommand(MyGames mygames, WorldCreatorCommand wcc) {
		super(mygames, "environment");
		
		this.setAliases(Lists.newArrayList("env"));
		
		this.wcc = wcc;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		
		UUID uuid = (sender instanceof Player)?(((Player)sender).getUniqueId()):null;
		
		WorldCreator wc = wcc.getWorldCreator(uuid);
		
		if (args.length > 0) {
			try {
				wcc.setWorldCreator(uuid, wc.environment(Environment.valueOf(args[0])));
				sender.sendMessage("Set World Environment to : " + args[0]);
			} catch (Exception e) {
				sender.sendMessage("Could not parse World Environment \"" + args[0] + "\".");
				sender.sendMessage("Valid: " + Utils.list(Lists.newArrayList(Environment.values())));
			}
		}
		else {
			sender.sendMessage("World Environment: "+wc.environment());
		}
		
		return true;
	}

}
