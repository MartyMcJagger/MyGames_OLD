package xenar47.bukkit.mygames.command.world;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xenar47.bukkit.mygames.MyGames;
import xenar47.bukkit.mygames.command.MyGamesCommand;

public class SaveWorldCommand extends MyGamesCommand {

	public SaveWorldCommand(MyGames mygames, String name) {
		super(mygames, "save");
		
		this.setPermission("mygames.command.world.save");
		this.setUsage("world save (world name)");
	}
	
	@Override
	public boolean execute(CommandSender sender, String commandLabel,
			String[] args) {
		
		String world = ""; 
		if (args.length >= 1) {
			world = args[0];
		} else {
			if (sender instanceof Player) {
				world = ((Player)sender).getWorld().getName();
			} else {
				sender.sendMessage(ChatColor.RED + "If you are not a player, you must declare the world."
						+ " /mygames world save [world name]");
			}
			return true;
		}
		
		try {
			Bukkit.getWorld(world).save();
			mygames.getWorldMgr().saveWorldToReferences(world);
			sender.sendMessage(ChatColor.GRAY + "Saved.");
		} catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "Error occured... Check the world name?");
		}
		
		return true;
	}

}
