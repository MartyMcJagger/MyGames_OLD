package xenar47.bukkit.mygames.command.worldCreator;

import java.util.UUID;

import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import xenar47.bukkit.mygames.MyGames;
import xenar47.bukkit.mygames.Utils;
import xenar47.bukkit.mygames.command.MyGamesCommand;
import xenar47.bukkit.mygames.command.WorldCreatorCommand;

public class WorldTypeCommand extends MyGamesCommand {

	WorldCreatorCommand wcc;
	
	public WorldTypeCommand(MyGames mygames, WorldCreatorCommand wcc) {
		super(mygames, "type");
		
		this.wcc = wcc;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		
		UUID uuid = (sender instanceof Player)?(((Player)sender).getUniqueId()):null;
		
		WorldCreator wc = wcc.getWorldCreator(uuid);
		
		if (args.length > 0) {
			try {
				wcc.setWorldCreator(uuid, wc.type(WorldType.valueOf(args[0])));
				sender.sendMessage("Set World Type to : " + args[0]);
			} catch (Exception e) {
				sender.sendMessage("Could not parse World Type \"" + args[0] + "\".");
				sender.sendMessage("Valid: " + Utils.list(Lists.newArrayList(WorldType.values())));
			}
		}
		else {
			sender.sendMessage("World Type: "+wc.type());
		}
		
		return true;
	}

}
