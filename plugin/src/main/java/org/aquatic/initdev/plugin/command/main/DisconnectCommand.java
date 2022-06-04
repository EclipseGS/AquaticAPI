package org.aquatic.initdev.plugin.command.main;

import org.aquatic.initdev.plugin.VisualAPI;
import org.aquatic.initdev.plugin.utils.Log;
import org.aquatic.initdev.plugin.utils.Permissions;
import org.aquatic.initdev.plugin.utils.Utils;
import org.aquatic.initdev.plugin.utils.color.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

/**
 * Copyright © 2022.
 *
 * @since 03-06-2022
 */
public class DisconnectCommand implements CommandExecutor {
	
	private final VisualAPI main;
	
	public DisconnectCommand() { this.main = JavaPlugin.getPlugin(VisualAPI.class); }
	
	@Override
	public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command,
													 @Nonnull String label, @Nonnull String[] args) {
		String prefix = "&a[VisualAPI]";
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (player.hasPermission(Permissions.DISCONNECT_COMMAND.getPermission())) {
				if (args.length == 0) {
					player.sendMessage(Text.color(prefix + "&f Introduce the nick of player to " +
																				"disconnect."));
					return true;
				}
				
				// Getting the player objective.
				Player target = main.getServer().getPlayer(args[0]);
				// The player is null (Not Online)?
				if (target == null) {
					target.sendMessage(Text.color(prefix + "&f This player is not &econnected&f."));
					return true;
				}
				
				// The player have the 'BYPASS' permission?
				if (target.hasPermission(Permissions.BYPASS.getPermission()) || target.isOp()) {
					target.sendMessage(Text.color(prefix + "&f This player &eavoid &fthe &6command&f."));
					return true;
				}
				
				// Check if the command have more arguments.
				if (args.length < 1) {
					player.sendMessage(Text.color(prefix + "&f Introduce a message for the disconnection."));
					return true;
				}
				
				// Getting the message.
				String message = null;
				for (int i = 1 ; i < args.length ; i++) {
					String arg = args[i] + " ";
					message = message + Text.color(arg);
				}
				
				try {
					// Disconnect the player.
					assert message != null;
					Utils.disconnect(target, message);
				} catch (NoSuchMethodException e) {
					// An error happens.
					e.printStackTrace();
				}
				return false;
			} else {
				// The player doesn't have the permission.
				player.sendMessage(Text.color(prefix + "&c You don't have permission for execute this " +
																		 "command!"));
			}
			return true;
		}
		
		// The executor is not a player.
		Log.asError("This command only is available on the game.");
		return false;
	}
}
