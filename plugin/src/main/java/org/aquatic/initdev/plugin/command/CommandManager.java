package org.aquatic.initdev.plugin.command;

import com.google.common.collect.Maps;
import org.aquatic.initdev.plugin.VisualAPI;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * Copyright © 2022.
 *
 * @since 03-06-2022
 */
public final class CommandManager {
	
	private final VisualAPI main;
	private final Map<String, CommandExecutor> commandsMap;
	
	public CommandManager() {
		this.main = JavaPlugin.getPlugin(VisualAPI.class);
		this.commandsMap = Maps.newHashMapWithExpectedSize(3);
	}
	
	/**
	 * Register the command to map.
	 *
	 * @param command Command Name to register.
	 * @param executor CommandExecutor class to register.
	 */
	public void register(@Nonnull String command, @Nonnull CommandExecutor executor) {
		// Put the command to Map.
		commandsMap.put(command, executor);
		// Register the command.
		PluginCommand pluginCommand = main.getServer().getPluginCommand(command);
		pluginCommand.setExecutor(executor);
	}
	
	/**
	 * Unregister the command from map.
	 *
	 * @param commands Commands to unregister.
	 */
	public void unregister(@Nonnull String... commands) {
		// String for every command from array.
		for (String command : commands) commandsMap.remove(command);
	}
}
