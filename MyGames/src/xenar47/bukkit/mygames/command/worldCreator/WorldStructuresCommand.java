package xenar47.bukkit.mygames.command.worldCreator;

import java.util.UUID;

import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import xenar47.bukkit.mygames.MyGames;
import xenar47.bukkit.mygames.command.MyGamesCommand;
import xenar47.bukkit.mygames.command.WorldCreatorCommand;

public class WorldStructuresCommand extends MyGamesCommand {

	WorldCreatorCommand wcc;
	
	String[] yes = new String[]{"true", "t", "yes", "y", "affirmative", "cheese"};
	String[] no = new String[]{"false", "f", "no", "n", "negatory", "oranges"};
	
	public WorldStructuresCommand(MyGames mygames, WorldCreatorCommand wcc) {
		super(mygames, "structures");
		
		this.setAliases(Lists.newArrayList("generateStructures", "generateStructs", "structs"));
		
		this.wcc = wcc;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		
		UUID uuid = (sender instanceof Player)?(((Player)sender).getUniqueId()):null;
		
		WorldCreator wc = wcc.getWorldCreator(uuid);
		
		if (args.length > 0) {
			
			Boolean generate = null;
			
			if (Lists.newArrayList(yes).contains(args[0].toLowerCase()))
				generate = true;
			else if (Lists.newArrayList(no).contains(args[0].toLowerCase()))
				generate = false;
			else {
				sender.sendMessage("Could not parse true/false from \""+args[0]+"\".");
				return true;
			}
			wcc.setWorldCreator(uuid, wc.generateStructures(generate));
			sender.sendMessage("Set Structure Generation to : " + generate);
		}
		else {
			sender.sendMessage("Generate Structures: " + wc.generateStructures());
		}
		
		return true;
	}

}
