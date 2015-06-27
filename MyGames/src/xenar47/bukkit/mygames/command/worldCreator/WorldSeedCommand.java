package xenar47.bukkit.mygames.command.worldCreator;

import java.util.UUID;

import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xenar47.bukkit.mygames.MyGames;
import xenar47.bukkit.mygames.command.MyGamesCommand;
import xenar47.bukkit.mygames.command.WorldCreatorCommand;

public class WorldSeedCommand extends MyGamesCommand {

	WorldCreatorCommand wcc;
	
	public WorldSeedCommand(MyGames mygames, WorldCreatorCommand wcc) {
		super(mygames, "seed");
		
		this.wcc = wcc;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		
		UUID uuid = (sender instanceof Player)?(((Player)sender).getUniqueId()):null;
		
		WorldCreator wc = wcc.getWorldCreator(uuid);
		
		if (args.length > 0) {
			try {
				wcc.setWorldCreator(uuid, wc.seed(args[0].hashCode()));
				sender.sendMessage("Set World Seed to : " + args[0].hashCode());
			} catch (Exception e) {
				sender.sendMessage("Could not parse valid seed from \"" + args[0].hashCode() + "\".");
			}
		}
		else {
			sender.sendMessage("World Seed: "+wc.seed());
		}
		
		return true;
	}

}
