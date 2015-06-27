package xenar47.bukkit.mygames.command.worldCreator;

import java.util.UUID;

import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xenar47.bukkit.mygames.MyGames;
import xenar47.bukkit.mygames.command.MyGamesCommand;
import xenar47.bukkit.mygames.command.WorldCreatorCommand;

public class WorldNameCommand extends MyGamesCommand {

	WorldCreatorCommand wcc;
	
	public WorldNameCommand(MyGames mygames, WorldCreatorCommand wcc) {
		super(mygames, "name");
		
		this.wcc = wcc;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		
		UUID uuid = (sender instanceof Player)?(((Player)sender).getUniqueId()):null;
		
		WorldCreator wc = wcc.getWorldCreator(uuid);
		
		if (args.length > 0) {
			WorldCreator wc2 = new WorldCreator(args[0]);
			
			wc2.generatorSettings(wc.generatorSettings());
			wc2.type(wc.type());
			wc2.environment(wc.environment());
			wc2.generateStructures(wc.generateStructures());
			wc2.seed(wc.seed());
			
			wcc.setWorldCreator(uuid, wc2);
			
			sender.sendMessage("Set World Name to : " + args[0]);
		}
		else {
			sender.sendMessage("World Name: "+wc.name());
		}
		
		return true;
	}

}
