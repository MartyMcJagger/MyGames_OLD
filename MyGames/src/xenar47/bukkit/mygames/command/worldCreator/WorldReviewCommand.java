package xenar47.bukkit.mygames.command.worldCreator;

import java.util.UUID;

import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xenar47.bukkit.mygames.MyGames;
import xenar47.bukkit.mygames.command.MyGamesCommand;
import xenar47.bukkit.mygames.command.WorldCreatorCommand;

public class WorldReviewCommand extends MyGamesCommand {

	WorldCreatorCommand wcc;
	
	public WorldReviewCommand(MyGames mygames, WorldCreatorCommand wcc) {
		super(mygames, "review");
		
		this.wcc = wcc;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel,
			String[] args) {
		
		UUID uuid = (sender instanceof Player)?(((Player)sender).getUniqueId()):null;
		WorldCreator wc = wcc.getWorldCreator(uuid);
		
		sender.sendMessage("World Name: " + wc.name());
		sender.sendMessage("World Type: " + wc.type());
		sender.sendMessage("World Environment: " + wc.environment());
		sender.sendMessage("World Seed: " + wc.seed());
		sender.sendMessage("Generate Structures: " + wc.generateStructures());
		
		return true;
	}

}
