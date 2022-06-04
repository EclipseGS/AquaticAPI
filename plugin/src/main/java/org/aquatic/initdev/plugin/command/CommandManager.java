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
