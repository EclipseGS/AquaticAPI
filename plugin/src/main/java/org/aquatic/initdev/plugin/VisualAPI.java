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
