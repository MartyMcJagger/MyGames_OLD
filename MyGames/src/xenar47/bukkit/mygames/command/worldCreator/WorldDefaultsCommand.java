package xenar47.bukkit.mygames.command.worldCreator;

import java.util.UUID;

import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xenar47.bukkit.mygames.MyGames;
import xenar47.bukkit.mygames.command.MyGamesCommand;
import xenar47.bukkit.mygames.command.WorldCreatorCommand;

public class WorldDefaultsCommand extends MyGamesCommand {

	WorldCreatorCommand wcc;
	
	public WorldDefaultsCommand(MyGames mygames, WorldCreatorCommand wcc) {
		super(mygames, "defaults");
		
		this.wcc = wcc;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel,
			String[] args) {
		
		UUID uuid = (sender instanceof Player)?(((Player)sender).getUniqueId()):null;
		wcc.setWorldCreator(uuid, new WorldCreator(""));
		
		return true;
	}

}
