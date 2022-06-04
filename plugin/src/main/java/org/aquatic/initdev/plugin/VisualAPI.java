package org.aquatic.initdev.plugin;

import lombok.Getter;
import org.aquatic.initdev.plugin.actions.ActionHandler;
import org.aquatic.initdev.plugin.command.CommandManager;
import org.aquatic.initdev.plugin.command.main.ActionBarCommand;
import org.aquatic.initdev.plugin.command.main.DisconnectCommand;
import org.aquatic.initdev.plugin.command.main.TitleCommand;
import org.aquatic.initdev.plugin.nms.Handler;
import org.aquatic.initdev.plugin.utils.Log;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main Class API - Copyright © 2022.
 *
 * @since 02-06-2022
 */
public final class VisualAPI extends JavaPlugin {
	
	final PluginDescriptionFile descriptionFile = getDescription();
	
	@Getter private final PluginManager pluginManager = getServer().getPluginManager();
	@Getter private final String authorName = String.join("", descriptionFile.getAuthors());
	@Getter private final String currentVersion = descriptionFile.getVersion();
	@Getter private final boolean isSpigot =
			getServer().getVersion().split("-")[1].equals("Spigot");
	
	public boolean is1_17orAbove = false;
	
	@Getter private Handler handler;
	@Getter private CommandManager commandManager;
	@Getter private ActionHandler actionHandler;
	
	@Override
	public void onEnable() {
		// API Startup logic.
		// Checking if the server software is Spigot.
		if (isSpigot) Log.asWarn("It is recommended the use of PaperSpigot for servers.");
		// Initialize the Version Handler.
		handler = new Handler();
		// Register the commands.
		commandManager = new CommandManager();
		commandManager.register("title", new TitleCommand());
		commandManager.register("disconnect", new DisconnectCommand());
		commandManager.register("actionbar", new ActionBarCommand());
		// Initialize the Action Handler.
		actionHandler = new ActionHandler();
		// Start message.
		Log.asInfo("Started successful!");
		Log.asInfo("Developed by: " + authorName + " - " + currentVersion);
	}
	
	@Override
	public void onDisable() {
		// API Shutdown logic.
		// Unregister the commands.
		commandManager.unregister("title", "disconnect", "actionbar");
		// Disable message.
		Log.asInfo("VisualAPI disabled successful!");
		Log.asInfo("Developed by: " + authorName + " - " + currentVersion);
	}
}
