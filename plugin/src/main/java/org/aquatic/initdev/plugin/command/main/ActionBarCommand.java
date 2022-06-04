/*
 * MIT License
 *
 * Copyright (c) 2022 InitDev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
 * @since 04-06-2022
 */
public class ActionBarCommand implements CommandExecutor {
	
	private final VisualAPI main;
	
	public ActionBarCommand() { this.main = JavaPlugin.getPlugin(VisualAPI.class); }
	
	@Override
	public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command,
													 @Nonnull String label, @Nonnull String[] args) {
		String prefix = "&a[VisualAPI]";
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (player.hasPermission(Permissions.ACTIONBAR_COMMAND.getPermission())) {
				if (args.length == 0) {
					player.sendMessage(Text.color(prefix + "&f Introduce the nick of player to " +
																				"send the actionbar."));
					return true;
				}
				
				if (args[0].equals("all")) {
					// Getting the message.
					String message = null;
					for (int i = 1 ; i < args.length ; i++) {
						String arg = args[i] + " ";
						message = message + Text.color(arg);
					}
					
					String finalMessage = message;
					main.getServer().getOnlinePlayers().forEach(player1 -> {
						try {
							// Send the actionbar.
							assert finalMessage != null;
							Utils.actionBar(player1, finalMessage);
						} catch (NoSuchMethodException e) {
							// An error happens.
							e.printStackTrace();
						}
					});
					return true;
				}
				
				// Getting the player objective.
				Player target = main.getServer().getPlayer(args[0]);
				// The player is null (Not Online)?
				if (target == null) {
					target.sendMessage(Text.color(prefix + "&f This player is not &econnected&f."));
					return true;
				}
				
				// Message for send.
				String message = Text.color(args[1]);
				try {
					// Send the actionbar.
					Utils.actionBar(target, message);
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
